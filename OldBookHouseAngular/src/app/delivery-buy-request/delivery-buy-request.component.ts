import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { UpdateBookStatusService } from '../share/update-book-status.service';
import { UpdateBookStatusComponent } from '../update-book-status/update-book-status.component';
import * as Chart from 'chart.js'

@Component({
  selector: 'app-delivery-buy-request',
  templateUrl: './delivery-buy-request.component.html',
  styleUrls: ['./delivery-buy-request.component.css']
})
export class DeliveryBuyRequestComponent implements OnInit {

  array:any;
  pendingNo:number=0;
  sucessNo:number=0;
  rejectNo:number=0;
  canvas: any;
  ctx: any;
  constructor(public  javaServiceObj:JavaServiceService,
    public dialog: MatDialog,
    public updateService:UpdateBookStatusService) { }

  ngOnInit() {
    if(this.javaServiceObj.hasAdminRole()){
      this.javaServiceObj.getDeliverySellRequestAdmin().subscribe(
        data=>{
          this.array=data;
          for (let index = 0; index < this.array.length; index++) {
              if(this.array[index][4]==='ProcessingOrder'){
                this.pendingNo++;
              }else if(this.array[index][4]==='CanceledOrder'){
                this.rejectNo++;
              }else{
                this.sucessNo++;
              }
          }
          this.drawChart();
        }
      );
    }else{
      this.javaServiceObj.getDeliverySellRequest().subscribe(
        data=>{
          this.array=data;
        }
      );
    }   
  }

  // this method is use to update the book status
  updateStatus(bookId:number){
    this.javaServiceObj.deliveryForBuy=true;
    this.javaServiceObj.bookId=this.array[bookId][0];
    this.updateService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(UpdateBookStatusComponent,dialogConfig);
  }

// this method is use to draw the chart 
  drawChart() {
    this.canvas = document.getElementById('myChart');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'bar',
      data: {
          labels: ["Canceled Order", "Processing Order", "Shipped Order"],
          datasets: [{
              label: '# of Votes',
              data: [this.rejectNo,this.pendingNo,this.sucessNo],
              backgroundColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(0, 153, 76, 1)',
              ],
              borderWidth: 1
          }]
      },
      options: {
        responsive: false,
        display:true,
        scales: {
          yAxes: [{
              ticks: {
                  max: this.array.length,
                  min: 0,
                  stepSize: 2
              }
          }]
      }
      }
    });
  }

}
