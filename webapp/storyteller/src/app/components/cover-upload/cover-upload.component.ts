import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-cover-upload',
  templateUrl: './cover-upload.component.html',
  styleUrls: ['./cover-upload.component.css']
})
export class CoverUploadComponent implements OnInit {

  @Input() public postUrl : string = '';
  @Input() public bookId : string = '';

  public file : File | null = null;
  public previewImageUrl : string | null = null;

  constructor() { }

  ngOnInit() {
  }

  public onFileSelected(event: any) {
    this.file = event.target.files[0];
  }

}
