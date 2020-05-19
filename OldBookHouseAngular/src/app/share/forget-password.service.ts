import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ForgetPasswordService {

  constructor() { }
  form: FormGroup = new FormGroup({
    userName: new FormControl('', Validators.required),
  });
  
  initializeFormGroup() {
    this.form.setValue({
      userName: ''
    });
  }
}
