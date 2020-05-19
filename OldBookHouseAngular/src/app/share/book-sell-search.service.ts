import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class BookSellSearchService {

  constructor() { }
  form: FormGroup = new FormGroup({
    bookISBN: new FormControl('', Validators.required)
  });
  
  initializeFormGroup() {
    this.form.setValue({
      bookISBN:''
    });
  }
}
