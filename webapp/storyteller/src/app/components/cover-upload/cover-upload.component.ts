import { Component, Input, OnInit, inject } from '@angular/core';
import { CoverService } from '../../service/cover.service';

@Component({
  selector: 'app-cover-upload',
  templateUrl: './cover-upload.component.html',
  styleUrls: ['./cover-upload.component.css']
})
export class CoverUploadComponent implements OnInit {

  @Input() public bookId : string = '';

  public file : File | null = null;
  public previewImageUrl : string | null = null;
  previewLoaded : boolean = false;
  public error : boolean = false;
  
  protected coverService : CoverService = inject(CoverService);

  constructor() { }

  ngOnInit() {
  }

  public onFileSelected(event: any) {
    this.file = event.target.files[0];
    if(this.file == null) return;
    this.previewLoaded = false;
    this.coverService.previewCover(this.file).subscribe(
      result => {
        this.previewImageUrl = result.smImageUri;
        console.debug("result, preview cover", result)
      },
      error => {
        this.error = true;
      },
    )
  }

  public previewLoadedEvent() {
    this.previewLoaded = true;
  }

  public closeDialog() {
    this.file = null;
    this.coverService.hide();
  }

  public commitImage() {
    if (this.file != null) {
      this.coverService.commitImage(this.file, this.bookId).subscribe(
        result => {
          this.closeDialog();
          console.debug("result, committing cover", result)
        },
        error => {
          this.error = true;
        },
      );
    }
  }
}