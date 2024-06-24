import { Injectable } from '@angular/core';
import {FormBuilder} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})

/**
 * Service for form building
 */
export class FormBuilderService {

  constructor(private formBuilder: FormBuilder) { }

  /**
   * Build the form for the book settings
   */
  buildBookSettingsForm() {
    return this.formBuilder.group({
      public: "",
      adultContent: "",
      commentsDeactivated: "",
      finished: "",
    });
  }
}
