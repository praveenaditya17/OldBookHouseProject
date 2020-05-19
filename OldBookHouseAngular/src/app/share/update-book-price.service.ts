import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UpdateBookPriceService {

  constructor() { }
  form: FormGroup = new FormGroup({
    amount: new FormControl('', Validators.required),
  });
  
  initializeFormGroup() {
    this.form.setValue({
      amount: ''
    });
  }
}
