import { Component, OnInit } from '@angular/core';
import { UserInfo, JavaServiceService } from '../java-service.service';
import { LoginServeiceService } from '../share/login-serveice.service';
import { NotificationService } from '../share/notification.service';
import { RegistrationComponent } from '../registration/registration.component';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { AddAddressService } from '../share/add-address.service';

@Component({
  selector: 'app-bookreq-address',
  templateUrl: './bookreq-address.component.html',
  styleUrls: ['./bookreq-address.component.css']
})
export class BookreqAddressComponent implements OnInit {

  userInfo=new UserInfo();
  isShowAddress:boolean=false;
  address:any;

  constructor(public addaddressservice:AddAddressService,
    public notificationService:NotificationService,
    public dialogRef:MatDialogRef<RegistrationComponent>,
    public dialog: MatDialog,
    public javaServiceObj:JavaServiceService ) { 
      
    }

// htis mehtod is use to call javaServiceMethod method of requestBookDetails 
sendId(id:number){
  this.javaServiceObj.bookObj.addressId=id;
  this.javaServiceObj.requestBookDetails(this.javaServiceObj.bookObj);
  this.onClose();
}

// this mehtod is use to call the javaServiceMethod of addAddress
onSubmit() {
  if (this.addaddressservice.form.valid) {
    this.userInfo=this.addaddressservice.form.value;
    this.javaServiceObj.addAddress(this.userInfo).subscribe(
      data=>{
        this.address=data;
        this.javaServiceObj.bookObj.addressId=this.address.address[this.address.address.length-1].id;
        this.sendId(this.javaServiceObj.bookObj.addressId);
      }
    );
  this.addaddressservice.form.reset();
  this.addaddressservice.initializeFormGroup();
  this.notificationService.success(':: Submitted successfully');
  this.onClose();
  }
}

// this mehtod is use to close the modol
onClose() {
this.addaddressservice.form.reset();
this.addaddressservice.initializeFormGroup();
this.dialogRef.close();
}

// this mehtod is use to clear the form value
onClear() {
  this.addaddressservice.form.reset();
  this.addaddressservice.initializeFormGroup();
    this.notificationService.warn(':: Clear successfully');
}

addAddress(){
  this.isShowAddress=true;
}

// this method use to get the user address
ngOnInit() {
  this.javaServiceObj.getAddress().subscribe(
    (data)=>{
      this.address=data;
      this.address=this.address.address;
  });
}
}
