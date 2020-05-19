import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { UpdateBookStatusComponent } from '../update-book-status/update-book-status.component';
import { UpdateBookStatusService } from '../share/update-book-status.service';
import * as Chart from 'chart.js'


@Component({
  selector: 'app-delivery-request',
  templateUrl: './delivery-request.component.html',
  styleUrls: ['./delivery-request.component.css']
})
export class DeliveryRequestComponent implements OnInit {

  array:any;
  sucess:boolean=true;
  pendingNo:number=0;
  sucessNo:number=0;
  rejectNo:number=0;
  canvas: any;
  ctx: any;

  constructor(public dialog: MatDialog,
    public  javaServiceObj:JavaServiceService,
    public updateService:UpdateBookStatusService) { }

  ngOnInit() {
    if(this.javaServiceObj.hasAdminRole()){
      this.javaServiceObj.getDeliveryRequestAdmin().subscribe(
        data=>{
          this.array=data;
          for (let index = 0; index < this.array.length; index++) {
            if(this.array[index][3]==='ProcessingOrder'){
              this.pendingNo++;
            }else if(this.array[index][3]==='CanceledOrder'){
              this.rejectNo++;
            }else{
              this.sucessNo++;
            }
        }
        this.drawChart();
        }
      );
    }else{
      this.javaServiceObj.getDeliveryRequest().subscribe(
        data=>{
          this.array=data;
        }
      );
    }
    
  }

  //this method is use to update book status
  updateStatus(bookId:number){
    this.javaServiceObj.bookId=this.array[bookId][0];
    this.updateService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(UpdateBookStatusComponent,dialogConfig);
  }

 // this mehod is use to draw chart
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
