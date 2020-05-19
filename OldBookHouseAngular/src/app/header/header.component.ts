import { Component, OnInit } from '@angular/core';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { LoginServeiceService } from '../share/login-serveice.service';
import { RegistrationService } from '../share/registration.service';
import { RegistrationComponent } from '../registration/registration.component';
import { JavaServiceService } from '../java-service.service';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { ProfileComponent } from '../profile/profile.component';
import { NgxSpinnerService } from "ngx-spinner";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  cartshow:boolean=false;
  bookList:any;
  nav_toggel:boolean=false;
  constructor(public dialog: MatDialog,public loginService:LoginServeiceService,public registrationService:RegistrationService,public javaCallObj:JavaServiceService,
    private hasLogin:AuthenticationService,private router:Router,public spinner:NgxSpinnerService) { }
  fun(){
    this.cartshow=true;
  }
  ngOnInit() {
  }

  // thid method use to send the home page
  home(){
    this.javaCallObj.getSpinner();
    this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
      this.router.navigateByUrl('mainslider');
    });
  }

// this method is use to search book by value
searchBook(searchValue:any){
  this.javaCallObj.searchBook(searchValue.value);
}

// this method is use to calculate the total price
getPrice(){
  this.javaCallObj.totalPrice=0;
  for (let index = 0; index < this.bookList.length; index++) {
    this.javaCallObj.totalPrice=this.javaCallObj.totalPrice+this.bookList[index].amount*this.bookList[index].quantity;  
  }
}

// this method is use to getNotification of book
getNotificationBook(){
  if(this.hasLogin.isUserLoggedIn()){
    this.javaCallObj.getBuyBook().subscribe(
      book=>{
        this.bookList=book;
        this.getPrice();
      }
    );
  }
}

// this method is use to delete the boook fron cart  
deleteBookRequest(requestId:number){
  this.javaCallObj.delteBookRequest(requestId).subscribe(
    data=>{
        this.javaCallObj.getBookNotification();
    }
  )
}

// this method is use to serch book by catagory
searchCatogory(bookCatagory:any){
  this.javaCallObj.searchCatagory(bookCatagory);
}

//this method used for geeting all book request for deliver 
deliverySellRequest(){
  this.javaCallObj.getSpinner();
  this.router.navigate(["/deliverBuyRequest"]);
}

// this method used for getting all book request for buy (get delivery)
deliveryRequestFun(){
  this.javaCallObj.getSpinner();
  this.router.navigate(["/deliveryRequest"]);
}

  // this method is use to login the user
  login(){
    this.loginService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(LoginComponent,dialogConfig);
  }

  // this method is use for registration 
  registration(){
    this.registrationService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(RegistrationComponent,dialogConfig);
  }

  // this method is use to view user profile
  viewProfile(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(ProfileComponent,dialogConfig);
  }

  // this method use to logout
  logOut(){
    this.hasLogin.logOut();
    sessionStorage.removeItem('userRole');
    sessionStorage.removeItem('notification1');
    sessionStorage.setItem('notification1','0');
    this.router.navigate(["/mainslider"]);

  }

  // this method is use to navigate the buyHistory component
  buyOrder(){
    this.javaCallObj.getSpinner();
    this.router.navigate(["/buyHistory"]);
  }

  // this method is use to navigate the sellHistory component
  sellOrder(){
    this.javaCallObj.getSpinner();
    this.router.navigate(["/sellHistory"]);
  }

  // this method is use to navigate userList
  listUserFun(){
    this.javaCallObj.getSpinner();
    this.router.navigate(["/userList"]);
  }

  // this method is use to navigate deliverBuyRequest
  deliverySellRequestAdmin(){
    this.javaCallObj.getSpinner();
    this.router.navigate(["/deliverBuyRequest"]);
  }

  // this method is use to navigate deliveryRequest
  deliveryRequestFunAdmin(){
    this.javaCallObj.getSpinner();
    this.router.navigate(["/deliveryRequest"]);
  }

  // this method is use to navigate updateBookPrice
  updateBookPrice(){
    this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
      this.javaCallObj.oldBookStatus=false;
      this.router.navigate(['/updateBookPrice']);
    });
  }
  
  // this method is use to navigate updateBookPrice
  getOldBook(){
    this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
    this.javaCallObj.oldBookStatus=true;
    this.router.navigate(['/updateBookPrice']);
    });
  }

  // this method is use to navigate contact
  contact(){
    this.router.navigate(['/contact']);
    this.javaCallObj.getSpinner();
  }

}
