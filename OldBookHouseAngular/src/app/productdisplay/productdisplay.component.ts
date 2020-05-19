import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { LoginServeiceService } from '../share/login-serveice.service';
import { BookSellSearchComponent } from '../book-sell-search/book-sell-search.component';
import { BookSellSearchService } from '../share/book-sell-search.service';
import { JavaServiceService } from '../java-service.service';
import { NgxSpinnerService } from "ngx-spinner";
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../share/notification.service';


@Component({
  selector: 'app-productdisplay',
  templateUrl: './productdisplay.component.html',
  styleUrls: ['./productdisplay.component.css']
})
export class ProductdisplayComponent implements OnInit {
  bookList:any;
  newList:any;
  notEmptyPost = true;
  notscrolly = true;
  cartDisplay=false;
  constructor(private router:Router,public loginService:BookSellSearchService,
    public dialog:MatDialog,public javaService:JavaServiceService,
    public spinner:NgxSpinnerService, public hasLogin:AuthenticationService,
    public notificationService:NotificationService) { }

  ngOnInit() {
    this.javaService.findBooks(0,3).subscribe((books: any[]) => {
      this.bookList = books;
   });
  }

  // this method is open the BookSellSearchComponent
  booksell(){
    this.loginService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(BookSellSearchComponent,dialogConfig);

  }

  // this mehod is use to scroll 
  onScroll() {
    if (this.notscrolly && this.notEmptyPost){
      this.notscrolly = false;
      this.loadNextPost();
    }
    
  }

  // this is use to get next book on the basic of  scroll
  loadNextPost() {
    this.javaService.findBooks(this.bookList.length,this.bookList.length+3).subscribe((newBookList: any[]) => {
      if (newBookList.length === 0 ) {
        this.notEmptyPost =  false;
      }
      this.bookList = this.bookList.concat(newBookList);
      this.notscrolly = true;
    });
  }

  // this method is use to navigate the buyBook Component 
  buyBook(sellOrderRequestId:number){
    this.javaService.bookId=sellOrderRequestId;
    this.javaService.getSpinner();
    this.router.navigate(['/buybook']);
  }

  // this method is use to addToCart book
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
      console.log("not loging....")
    } 
  }

  // this mehod is use to purchage the book 
  purchaseBook(){
    if(this.hasLogin.isUserLoggedIn()){
      this.javaService.getSpinner();
      this.router.navigate(['/checkout']);
    }else{
      this.notificationService.warn("please Login first.")
    }
  }

  // this mehod is use to find the book by author
  findBookByAuthor(author:string){
    this.notEmptyPost=false;
    this.javaService.findBookByAuthor(author).subscribe(data=>{
      this.bookList = data;
      this.javaService.getSpinner();
    });
  }

  // this mehod is use to find the book by publisher
  findBookByPublisher(publisher:string){
    this.javaService.getBookByPublisher(publisher).subscribe(data=>{
      this.bookList = data;
      this.javaService.getSpinner();
    });
  }

  // this mehod is use to find the book by catagory
  searchCatogory(category:string){
    this.cartDisplay=true;
    this.javaService.getBookByCategory(category).subscribe(data=>{
      this.bookList = data;
      this.javaService.getSpinner();
    });
  }
}
