import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { BookDeliverAddressComponent } from '../book-deliver-address/book-deliver-address.component';
import { AddAddressService } from '../share/add-address.service';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../share/notification.service';

@Component({
  selector: 'app-buy-book',
  templateUrl: './buy-book.component.html',
  styleUrls: ['./buy-book.component.css']
})
export class BuyBookComponent implements OnInit {
  bookDetail:any;
  constructor(private javaServiceObj:JavaServiceService,
    public addreqService:AddAddressService,
    public dialog:MatDialog,
    private hasLogin:AuthenticationService,
    private notificationService:NotificationService
  ) { }

  ngOnInit() {
    this.javaServiceObj.getBookById(this.javaServiceObj.bookId).subscribe((book) => {
      this.bookDetail=book;
    });
  }

  // this method is use to add the book in cart
  addToCart(bookId:number){
    if(this.hasLogin.isUserLoggedIn()){

      this.javaServiceObj.getQuantity(bookId).subscribe(
        data=>{
          let bookQuantity=data;
          if(0<bookQuantity){
            this.javaServiceObj.getCartBookQuantity(bookId).subscribe(
              data=>{
                if(data<bookQuantity){
                  this.javaServiceObj.addSellOrderRequest(bookId);
                  this.notificationService.success("Added to Cart Successfully.");
                }else{
                  this.notificationService.warn("No more quantity is available.");
                }
              }
            );
          }else{
            this.notificationService.warn("Book is out of stock");
          }
        }
      );
    }else{
      this.notificationService.warn("please Login first.");
    }
  }
  
  // this mehod is use to purchase the book 
  buyNow(bookId:number,bookPrice:number){
    console.log(bookId);
    if(this.hasLogin.isUserLoggedIn()){
      this.javaServiceObj.bookId=bookId;
      this.javaServiceObj.totalPrice=bookPrice;
      this.javaServiceObj.checkCart=false;
      this.addreqService.initializeFormGroup();
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "50%";
      this.dialog.open(BookDeliverAddressComponent,dialogConfig);
    }else{
      this.notificationService.warn("please Login first.");
    }
  }
}
