import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UpdateBookStatusService {

  constructor() { }
  form: FormGroup = new FormGroup({
    sellOrderRequestId: new FormControl(''),
    check_status: new FormControl("",Validators.required),
    feedBack:new FormControl('',Validators.required)
  });
  
  initializeFormGroup() {
    this.form.setValue({
      sellOrderRequestId: '',
      check_status:'',
      feedBack:''
    });
  }
}
