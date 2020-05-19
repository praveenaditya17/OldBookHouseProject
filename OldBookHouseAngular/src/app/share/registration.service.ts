import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor() { }
    form: FormGroup = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName:new FormControl('',Validators.required),
    email: new FormControl('', [Validators.required,Validators.email]),
    mobileNumber: new FormControl('', [Validators.required, Validators.minLength(10)]),
    password: new FormControl('',Validators.required),
    confirmPassword:new FormControl(''),
    address: new FormControl('',Validators.required),
    address2: new FormControl('',Validators.required),
    location: new FormControl('',Validators.required),
    district: new FormControl('',Validators.required),
    pinCode: new FormControl('',Validators.required),
    state: new FormControl('',Validators.required),
  });

  initializeFormGroup() {
    this.form.setValue({
      firstName: '',
      lastName:'',
      email: '',
      mobileNumber: '',
      password:'',
      confirmPassword:'',
      address:'',
      address2:'',
      location:'',
      pinCode:'',
      district:'',
      state:''
    });
  }  
}
