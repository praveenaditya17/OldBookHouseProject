import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JavaServiceService } from '../java-service.service';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { UpdateBookPriceService } from '../share/update-book-price.service';
import { UpdatePriceComponent } from '../update-price/update-price.component';

@Component({
  selector: 'app-update-book-price',
  templateUrl: './update-book-price.component.html',
  styleUrls: ['./update-book-price.component.css']
})
export class UpdateBookPriceComponent implements OnInit {
  bookList:any;
  constructor(private router:Router,
    public javaServiceObj:JavaServiceService,
    public updatePriceService:UpdateBookPriceService, 
    public dialog:MatDialog
  ) { }

  ngOnInit() {
    if(this.javaServiceObj.oldBookStatus){
      this.javaServiceObj.getAllBook().subscribe(
        data=>{
          this.bookList=data;
        }
      );
    }else{
      this.javaServiceObj.getBook().subscribe(
        data=>{
          this.bookList=data;
        }
      );
    }
  }

  // this method is use to open the UpdatePriceComponent
  updatePrice(bookid:number){
      this.javaServiceObj.bookId=bookid;
      this.updatePriceService.initializeFormGroup();
      const priceDialog = new MatDialogConfig();
      priceDialog.disableClose = true;
      priceDialog.autoFocus = true;
      priceDialog.width = "40%";
      this.dialog.open(UpdatePriceComponent, priceDialog);
  }
}
