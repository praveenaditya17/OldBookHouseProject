import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class LoginServeiceService {

  constructor() { }

  form: FormGroup = new FormGroup({
  userName: new FormControl('', Validators.required),
  userPassword:new FormControl('',Validators.required)
});

initializeFormGroup() {
  this.form.setValue({
    userName: '',
    userPassword:''
  });
}
}
