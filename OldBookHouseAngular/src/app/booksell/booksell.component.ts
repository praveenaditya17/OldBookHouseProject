import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { JavaServiceService } from '../java-service.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { BookreqAddressComponent } from '../bookreq-address/bookreq-address.component';
import { AddAddressService } from '../share/add-address.service';
import { NgxSpinnerService } from "ngx-spinner";
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../share/notification.service';


@Component({
  selector: 'app-booksell',
  templateUrl: './booksell.component.html',
  styleUrls: ['./booksell.component.css']
})
export class BooksellComponent implements OnInit {

  book:any;
  name:any;
  array:any;
  isBook:boolean=false;

  constructor(public dialog: MatDialog,
    public addressService:AddAddressService,
    public httpClient:HttpClient,
    public javaService:JavaServiceService,
    public spinner:NgxSpinnerService,
    public hasLogin:AuthenticationService,
    public notificationService:NotificationService) { }

  // this method use to call to google book api
  sendData(){
    this.name=this.book.value;
    this.httpClient.get('https://www.googleapis.com/books/v1/volumes?q='+this.name.isbnNo).subscribe(
      data=>{
      this.javaService.getSpinner();
      this.array=data;
      this.isBook=true;
      }
    );
  }

  ngOnInit(): void {
    this.book = new FormGroup({
      isbnNo: new FormControl('')
    });
    if(this.javaService.bookISBN.length>0){
      this.name=this.javaService.bookISBN;
      this.httpClient.get('https://www.googleapis.com/books/v1/volumes?q='+this.name).subscribe(
        data=>{   
          this.array=data;
          this.isBook=true;
        }
      );
    }
  }

  // this mehod is use to save the book details
  sellBook(bookNumber:number){
    console.log(this.array.items[bookNumber]);
      this.javaService.bookObj.book_name=this.array.items[bookNumber].volumeInfo.title;
      this.javaService.bookObj.authors= this.array.items[bookNumber].volumeInfo.authors[0];
      try {
      this.javaService.bookObj.description=this.array.items[bookNumber].volumeInfo.description.substring(0,255);
        
      } catch (error) {
      this.javaService.bookObj.description='No Description';
        
      }
      this.javaService.bookObj.publisher=this.array.items[bookNumber].volumeInfo.publisher;
      this.javaService.bookObj.publishedDate=this.array.items[bookNumber].volumeInfo.publishedDate;
      try {
      this.javaService.bookObj.categories=this.array.items[bookNumber].volumeInfo.categories[0];
        
      } catch (error) {
      this.javaService.bookObj.categories='Miscellaneous';     
      }
      this.javaService.bookObj.contentVersion=this.array.items[bookNumber].volumeInfo.contentVersion;
      this.javaService.bookObj.isbn_type_10=this.array.items[bookNumber].volumeInfo.industryIdentifiers[0].type;
      this.javaService.bookObj.isbnNo1=this.array.items[bookNumber].volumeInfo.industryIdentifiers[0].identifier;
      this.javaService.bookObj.isbn_type_13=this.array.items[bookNumber].volumeInfo.industryIdentifiers[1].type;
      this.javaService.bookObj.isbnNo2=this.array.items[bookNumber].volumeInfo.industryIdentifiers[1].identifier;
      try{
      this.javaService.bookObj.smallThumbnail=this.array.items[bookNumber].volumeInfo.imageLinks.smallThumbnail;
      this.javaService.bookObj.thumbnail=this.array.items[bookNumber].volumeInfo.imageLinks.thumbnail;
      }catch(error){
      this.javaService.bookObj.smallThumbnail="";
      this.javaService.bookObj.thumbnail="";
      }
      this.javaService.bookObj.checkPrice=this.array.items[bookNumber].saleInfo.isEbook;
      if(this.javaService.bookObj.checkPrice){
          this.javaService.bookObj.amount=0;
          this.javaService.bookObj.currencyCode=this.array.items[bookNumber].saleInfo.listPrice.currencyCode;
      }
      if(this.hasLogin.isUserLoggedIn()){
        this.addressService.initializeFormGroup();
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.width = "50%";
        this.dialog.open(BookreqAddressComponent,dialogConfig);
      }else{
        this.notificationService.warn("please Login first.");
      }    
  }
}
