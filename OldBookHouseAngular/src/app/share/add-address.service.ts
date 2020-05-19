import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class AddAddressService {

  constructor() { }
  form: FormGroup = new FormGroup({
  
    address: new FormControl('',Validators.required),
    address2: new FormControl(''),
    location: new FormControl('',Validators.required),
    district: new FormControl('',Validators.required),
    pinCode: new FormControl('',Validators.required),
    state: new FormControl('',Validators.required),
  });

  initializeFormGroup() {
    this.form.setValue({
      address:'',
      address2:'',
      location:'',
      pinCode:'',
      district:'',
      state:''
    });
  }  
}
