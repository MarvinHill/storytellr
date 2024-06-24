import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { CoverService } from '../../service/cover.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { CoverURI } from '../../model/cover';

@Component({
  selector: 'app-cover-upload',
  templateUrl: './cover-upload.component.html',
  styleUrls: ['./cover-upload.component.css']
})

/**
 * Component to upload a cover image for a book
 */
export class CoverUploadComponent implements OnInit {

  @Input() public bookId : string = '';
  @Output() public urlChanged : EventEmitter<CoverURI> = new EventEmitter();
  protected file : BehaviorSubject<File | null> = new BehaviorSubject<File | null>(null);
  protected url : BehaviorSubject<CoverURI | null> = new BehaviorSubject<CoverURI | null>(null);
  protected previewLoaded : boolean = false;

  protected isError : boolean = false;
  protected fileIsToBig : boolean = false;
  protected imageIsToSmall : boolean = false;


  protected isDropFileState : boolean = false;
  private fileDragOverTimeout: any = null;
  protected coverService : CoverService = inject(CoverService);

  constructor() {
    this.file.subscribe(file => {
      this.updateURL(file);
    });
  }
  ngOnInit(): void {
  }

  /**
   * Updates the URL subject with the preview of the file.
   * @param file The file to be previewed.
   * @private
   */
  private updateURL(file : File | null) {
    if(file == null || file == undefined) {
      console.log("file is", file);
      return;
    }
    this.coverService.previewCover(file).subscribe( {
      next: (result) => {
        this.url.next(result);
      },
      error: (error) => {
        this.isError = true;
      },
    }
    )
  }

  /**
   * Handles the selection of a file, updates the file subject, and triggers preview generation.
   *
   * @param {any} event - The event object containing the selected file information.
   */
  public onFileSelected(event: any) {

    this.isError = false;
    this.fileIsToBig = false;
    this.imageIsToSmall = false;

    // Prevent Browser from opening the file
    event.stopPropagation();    
    event.preventDefault();

    if(event.target?.files != null){
      console.log("first selected file", event.target.files[0]);
      this.file.next(event.target?.files[0]);
      this.checkImageSize(event.target.files[0]);
      this.checkFileIsToBig(event.target.files[0]);
      
    }
    else if (event.dataTransfer?.files != null){
      console.log("second selected file", event.dataTransfer?.files[0]);
      this.file.next(event.dataTransfer?.files[0]);
      this.checkImageSize(event.dataTransfer?.files[0]);
      this.checkFileIsToBig(event.dataTransfer?.files[0]);
    }
    this.previewLoaded = false;
  }

  /**
   * Checks if the image is too small and sets the imageIsToSmall flag accordingly.
   * @param file The file to be checked.
   */
  async checkImageSize(file : File) : Promise<boolean> {
    let img = new Image();

    let promise = new Promise<boolean>((resolve, reject) => {
      img.onload = (i : any) => {
        if(img.width < 500 || img.height < 800) {
          this.imageIsToSmall = true;
          this.file.next(null);
        }
        resolve(this.imageIsToSmall)
      }
    })
    
    img.src = URL.createObjectURL(file)
    return promise;
  }

  /**
   * Checks if the file is too big and sets the fileIsToBig flag accordingly.
   * @param file The file to be checked.
   */
  async checkFileIsToBig(file : File) : Promise<boolean>{
    file.size > 10_000_000 ? this.fileIsToBig = true : this.fileIsToBig = false;
    if(this.fileIsToBig) this.file.next(null);
    return this.fileIsToBig;
  }
  
  /**
   * Handles the drag event and updates the state accordingly.
   *
   * @param {DragEvent} event - The drag event object.
   */
  public onDrag(event: DragEvent) {
    // Prevent Browser from opening the file
    event.stopPropagation();    
    event.preventDefault(); 

    clearTimeout(this.fileDragOverTimeout);
    this.fileDragOverTimeout = setTimeout(() => {
      this.isDropFileState = false;
    }, 500);

    if(event.dataTransfer?.files != null) {
      this.isDropFileState = true;
    }
    else {
      clearTimeout(this.fileDragOverTimeout);
    }
  }



  /**
   * Sets the previewLoaded flag to true.
   *
   * @param {void} None
   * @return {void} 
   */
  public previewLoadedEvent() {
    this.previewLoaded = true;
  }

  /**
   * Closes the dialog by setting the file subject to null and hiding the cover service.
   *
   * @return {void}
   */
  public closeDialog() {
    this.file.next(null);
    this.coverService.hide();
  }

  /**
   * Commits the image by calling the commitImage method of the coverService with the file and bookId.
   * If the commit is successful, it closes the dialog and sets isError to false.
   * If there is an error, it sets isError to true.
   *
   * @return {void}
   */
  public commitImage() {
    let commitFile : File | null = this.file.getValue();
    if (commitFile != null) {
      this.coverService.commitImage(commitFile, this.bookId).subscribe( {
        next: (result) => {
          this.closeDialog();
          const currentUrl : CoverURI | null = this.url.getValue();
          if(currentUrl != null){
            this.urlChanged.emit(currentUrl);
          } 
          console.debug("result, committing cover", result)
          this.isError = false;
        },
        error: (error) => {
          this.isError = true;
        }
      }
      );
    }
  }
}