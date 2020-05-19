import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class EditUSerService {

  constructor() { }
  form: FormGroup = new FormGroup({ 
    userId:new FormControl(), 
    firstName: new FormControl('',Validators.required),
    lastName: new FormControl(''),
    email: new FormControl('',Validators.required),
    mobileNumber: new FormControl('',Validators.required),
    role: new FormControl('',Validators.required),
  });
  initializeFormGroup() {
    this.form.setValue({
      userId:'',
      firstName:'',
      lastName:'',
      email:'',
      mobileNumber:'',
      role:'',
    });
  }
  setFormValue(data:any){
    this.form.setValue({
      userId:data.userId,
      firstName:data.firstName,
      lastName:data.lastName,
      email:data.email,
      mobileNumber:data.mobileNumber,
      role:data.role,
    });
  }
}
