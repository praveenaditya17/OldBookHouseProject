import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../share/registration.service';
import { LoginServeiceService } from '../share/login-serveice.service';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { NotificationService } from '../share/notification.service';
import { UserInfo, JavaServiceService } from '../java-service.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  userInfo=new UserInfo();
  constructor(public registrationService:RegistrationService,
    public loginService:LoginServeiceService,
    public notificationService:NotificationService,
    public dialogRef:MatDialogRef<RegistrationComponent>,
    public dialog: MatDialog,
    public javaServiceObj:JavaServiceService  ) {    
  }

onClear() {
this.registrationService.form.reset();
this.registrationService.initializeFormGroup();
  this.notificationService.warn(':: Clear successfully');
}

// this method is use to register the user
onSubmit() {
  if (this.registrationService.form.valid) {

    this.userInfo=this.registrationService.form.value;
    this.javaServiceObj.register(this.userInfo).subscribe(
      data=>{
        this.registrationService.form.reset();
        this.registrationService.initializeFormGroup();
        this.notificationService.success(':: Submitted successfully');
        this.onClose();
      },error=>{
        this.registrationService.form.reset();
        this.registrationService.initializeFormGroup();
        this.notificationService.success(':: Registation Failed');
        this.onClose();
        }
    );
  }
}

onClose() {
this.registrationService.form.reset();
this.registrationService.initializeFormGroup();
this.dialogRef.close();
}
ngOnInit() {  }
}
