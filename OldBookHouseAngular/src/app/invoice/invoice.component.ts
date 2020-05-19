import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import * as jspdf from 'jspdf';
import html2canvas from 'html2canvas';

@Component({  
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  invoiceData:any;
  total=0;
  constructor(private javaServiceObj:JavaServiceService) { }

  //this method is use to calculate total price
  getTotal(){
    for (let index = 0; index < this.invoiceData.length; index++) {
      this.total += this.invoiceData[index][10]*this.invoiceData[index][11];
    }
  }
  ngOnInit() {
    console.log(this.javaServiceObj.paymentData);
    this.javaServiceObj.getInvoice(this.javaServiceObj.paymentData.transactionId).subscribe(
      data=>{
        this.invoiceData=data;
        this.getTotal();
      }
    )
  }

  // this method is use to generate pdf
  public convetToPDF()
  {
    var data = document.getElementById('contentToConvert');
    html2canvas(data).then(canvas => {
        var imgWidth = 200;
        var pageHeight = 295;
        var imgHeight = 50;
        var heightLeft = imgHeight;
        const contentDataURL = canvas.toDataURL('image/png')
        let pdf = new jspdf('p', 'mm', 'a4');
        var position = 0;
        pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
        pdf.save('new-file.pdf'); 
    });
  }

}
