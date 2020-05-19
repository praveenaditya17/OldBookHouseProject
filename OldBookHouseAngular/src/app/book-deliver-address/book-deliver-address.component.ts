import { Component, OnInit } from '@angular/core';
import { UserInfo, JavaServiceService } from '../java-service.service';
import { AddAddressService } from '../share/add-address.service';
import { NotificationService } from '../share/notification.service';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PaymentComponent } from '../payment/payment.component';

@Component({
  selector: 'app-book-deliver-address',
  templateUrl: './book-deliver-address.component.html',
  styleUrls: ['./book-deliver-address.component.css']
})
export class BookDeliverAddressComponent implements OnInit {

  userInfo=new UserInfo();
  isShowAddress:boolean=false;
  address:any;
  lastAddress:any;
  addressId:number;

  constructor(
    public addaddressservice:AddAddressService,
    public notificationService:NotificationService,
    public dialogRef:MatDialogRef<BookDeliverAddressComponent>,
    public dialog: MatDialog,
    public javaServiceObj:JavaServiceService) { }

  ngOnInit(){
    // this method is use to get the address of user
    this.javaServiceObj.getAddress().subscribe(
      (data)=>{
        this.address=data;
        this.address=this.address.address;
      });
  }

  // this method call to PyamentCamponent
  sendId(addressId:number){
    this.javaServiceObj.bookObj.addressId=addressId;
    if(this.javaServiceObj.checkCart){
      this.onClose();
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "40%";
      this.dialog.open(PaymentComponent,dialogConfig);
    }else{
      this.onClose();
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "40%";
      this.dialog.open(PaymentComponent,dialogConfig);
    } 
  }

  // this method use to save the address
  onSubmit() {
    if (this.addaddressservice.form.valid) {
      this.userInfo=this.addaddressservice.form.value;
      this.javaServiceObj.addAddress(this.userInfo).subscribe(
      data=>{
        this.lastAddress=data;
        this.addressId=this.lastAddress.address[this.lastAddress.address.length-1].id;
        this.sendId(this.addressId);
      });
      this.addaddressservice.form.reset();
      this.addaddressservice.initializeFormGroup();
      this.notificationService.success(':: Submitted successfully');
      this.onClose();
    }
  }
    
  // this method use to close the model
  onClose() {
    this.addaddressservice.form.reset();
    this.addaddressservice.initializeFormGroup();
    this.dialogRef.close();
  }

  // this method use to clear the form value
  onClear() {
    this.addaddressservice.form.reset();
    this.addaddressservice.initializeFormGroup();
    this.notificationService.warn(':: Clear successfully');
  }
  
  // this method use to set boolean value
  addAddress(){
    this.isShowAddress=true;
  }
}
