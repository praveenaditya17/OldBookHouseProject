import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../share/notification.service';

@Component({
  selector: 'app-show-book',
  templateUrl: './show-book.component.html',
  styleUrls: ['./show-book.component.css']
})
export class ShowBookComponent implements OnInit {
  bookList:any;
  constructor(public javaService:JavaServiceService,public router:Router,
    public hasLogin:AuthenticationService,public notificationService:NotificationService) { 
    this.bookList=this.javaService.bookList;
  }

  ngOnInit() {
  }

  // this method is use to navigate the buyBook component
  viewBook(sellOrderRequestId:number){
    this.javaService.bookId=sellOrderRequestId;
    this.router.navigate(['/buybook']);
  }

  // this method is use to book added in cart
  addToCart(bookId:number){
    if(this.hasLogin.isUserLoggedIn()){
      this.javaService.getQuantity(bookId).subscribe(
        data=>{
          let bookQuantity=data;
          if(0<bookQuantity){
            this.javaService.getCartBookQuantity(bookId).subscribe(
              data=>{
                if(data<bookQuantity){
                  this.javaService.addSellOrderRequest(bookId);
                  this.notificationService.success("Added to Cart Successfully.");
                }else{
                  this.notificationService.warn("No more quantity is available.");
                }
              }
            );
          }else{
            this.notificationService.warn("Book is out of stock.");
          }
        }
      );
    }else{
      this.notificationService.warn("please Login first.");
    } 
  }

}
