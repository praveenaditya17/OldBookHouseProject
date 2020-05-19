import { Component, OnInit, NgZone } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { MatDialogRef } from '@angular/material/dialog';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router } from '@angular/router';
import { NotificationService } from '../share/notification.service';
import { JavaServiceService } from '../java-service.service';
import { PaymentService } from '../share/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  hide = true;
  paymentInfo: any;
  parsedJson:any;
  data:any;
  constructor(public paymentService: PaymentService,
    public javaService: JavaServiceService,
    public notificationService: NotificationService,
    public router: Router,
    public http: HttpClient,
    public zone: NgZone,
    public spinner: NgxSpinnerService,
    public dialogRef: MatDialogRef<PaymentComponent>) { }

  ngOnInit() {
  }
  onClear() {
    this.paymentService.form.reset();
    this.paymentService.initializeFormGroup();
  }

  onClose() {
    this.paymentService.form.reset();
    this.paymentService.initializeFormGroup();
    this.dialogRef.close();
  }

  //this method is use to get all information of card
  chargeCreditCard(cc_name, cardNumber, expMonth, expYear, cvc, amount) {
    let form = document.getElementsByTagName("form")[0];
    (<any>window).Stripe.card.createToken({
      name: cc_name,
      number: cardNumber,
      exp_month: expMonth,
      exp_year: expYear,
      cvc: cvc
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.chargeCard(token, amount);
      } else {
        console.log(response.error.message);
      }
    });
  }

  // this method is use to payment of book 
  chargeCard(token: string, amount1) {
    let amount: number = +amount1;
    const headers = new HttpHeaders({ 'token': token });
    this.http.post('http://localhost:8080/charge', amount, { headers: headers, responseType: 'text' })
      .subscribe(resp => {
        this.paymentInfo = resp;
        this.parsedJson = JSON.parse(this.paymentInfo); 
        this.javaService.payment.paymentId=this.parsedJson.id;
        this.javaService.payment.transactionId=this.parsedJson.balanceTransaction;
        this.javaService.payment.status=this.parsedJson.status;
        this.javaService.payment.amount=this.parsedJson.amount;
        this.javaService.payment.created=this.parsedJson.created;
        if(this.javaService.checkCart){
          this.javaService.savePaymentDetailsMutipleBook(this.javaService.payment);
        }else{
          this.javaService.savePaymentDetails(this.javaService.payment);
        }
      });
      this.dialogRef.close();
  }
}
