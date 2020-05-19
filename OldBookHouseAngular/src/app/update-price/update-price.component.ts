import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { UpdateBookPriceService } from '../share/update-book-price.service';
import { JavaServiceService } from '../java-service.service';

@Component({
  selector: 'app-update-price',
  templateUrl: './update-price.component.html',
  styleUrls: ['./update-price.component.css']
})
export class UpdatePriceComponent implements OnInit {

  book:any;
  constructor(public updatePriceService:UpdateBookPriceService, 
    public dialogRef: MatDialogRef<UpdatePriceComponent>,
    public javaServiceObj:JavaServiceService
    ) { }
  ngOnInit() {
  }
  onSubmit(){
    if (this.updatePriceService.form.valid) {
      this.book=this.updatePriceService.form.value;
      this.javaServiceObj.updateBookPrice(this.book.amount,this.javaServiceObj.bookId);
    }
    this.updatePriceService.form.reset();
    this.updatePriceService.initializeFormGroup();
    this.onClose();
  }
  onClose() {
    this.updatePriceService.form.reset();
    this.updatePriceService.initializeFormGroup();
    this.dialogRef.close();
  }

}
