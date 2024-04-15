import { Injectable } from '@angular/core';
import {FormBuilder} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class FormBuilderService {

  constructor(private formBuilder: FormBuilder) { }

  buildBookSettingsForm() {
    return this.formBuilder.group({
      public: "",
      adultContent: "",
      commentsDeactivated: "",
      finished: "",
    });
  }
}
