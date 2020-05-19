import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BookSellSearchService } from '../share/book-sell-search.service';
import { NgxSpinnerService } from "ngx-spinner";

@Component({
  selector: 'app-book-sell-search',
  templateUrl: './book-sell-search.component.html',
  styleUrls: ['./book-sell-search.component.css']
})
export class BookSellSearchComponent implements OnInit {

  constructor(public loginService: BookSellSearchService,
    public dialogRef: MatDialogRef<BookSellSearchComponent>,
    public dialog: MatDialog,
    private javaService:JavaServiceService,
    private router:Router,
    public spinner:NgxSpinnerService) {
  }

  onSubmit() {
    // this method use to navigate the booksell component 
    if (this.loginService.form.valid) {
      this.javaService.bookISBN=this.loginService.form.value.bookISBN;
      this.loginService.form.reset();
      this.loginService.initializeFormGroup();
      this.onClose();
      this.javaService.getSpinner();
      this.router.navigate(['/booksell']);
    }
  }

  //this method is use to close the model
  onClose() {
    this.loginService.form.reset();
    this.loginService.initializeFormGroup();
    this.dialogRef.close();
  }

  ngOnInit() {
  }

}
