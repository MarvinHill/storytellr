import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { CoverService } from '../../service/cover.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-cover-upload',
  templateUrl: './cover-upload.component.html',
  styleUrls: ['./cover-upload.component.css']
})
export class CoverUploadComponent implements OnInit {

  @Input() public bookId : string = '';
  @Output() public urlChanged : EventEmitter<File | null> = new EventEmitter();
  public file : BehaviorSubject<File | null> = new BehaviorSubject<File | null>(null);
  protected previewImageUrl : string | null = null;
  protected previewLoaded : boolean = false;
  protected isError : boolean = false;
  protected isDropFileState : boolean = false;
  private fileDragOverTimeout: any = null;
  protected coverService : CoverService = inject(CoverService);

  constructor() { 
    this.file.subscribe(file => {
      if(file != null) {
        this.urlChanged.emit(file);
      }
    })
  }

  ngOnInit() {
  }

  /**
   * Handles the selection of a file, updates the file subject, and triggers preview generation.
   *
   * @param {any} event - The event object containing the selected file information.
   */
  public onFileSelected(event: any) {
    if(event.target.files[0] != null){
      this.file.next(event.target.files[0]);
    }
    else if (event.dataTransfer?.files != null){
      this.file.next(event.dataTransfer?.files[0]);
    }
    let selectedFile = this.file.getValue();
    if( selectedFile == null) return;
    this.previewLoaded = false;
    this.coverService.previewCover(selectedFile).subscribe( {
      next: (result) => {
        this.previewImageUrl = result.smImageUri;
        console.debug("result, preview cover", result)
      },
      error: (error) => {
        this.isError = true;
      },
    }
    )
  }
  /**
   * Handles the drag event and updates the state accordingly.
   *
   * @param {DragEvent} event - The drag event object.
   */
  public onDrag(event: DragEvent) {
    if(this.fileDragOverTimeout != null) clearTimeout(this.fileDragOverTimeout);

    this.fileDragOverTimeout = setTimeout(() => {
      this.isDropFileState = false;
      console.log("timeout")
    ,500});

    if(event.dataTransfer?.files != null) {
      console.log("containes files: ", event.dataTransfer?.files)
      this.isDropFileState = true;

      console.log("timeout state: ", this.fileDragOverTimeout)
      console.log("drag file state: ", this.isDropFileState)
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