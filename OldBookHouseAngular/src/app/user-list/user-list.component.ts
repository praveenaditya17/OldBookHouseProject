import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { Router } from '@angular/router';
import * as Chart from 'chart.js'


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  userList: any = [];
  adminNo:number=0;
  userNo:number=0;
  deliveryPersonNo:number=0;
  canvas: any;
  ctx: any; 

  constructor(public javaService: JavaServiceService,
    public dialog: MatDialog,private router:Router) { }

  ngOnInit() {
    this.javaService.userList().subscribe((user: any[]) => {
      this.userList = user;
      for (let index = 0; index < this.userList.length; index++) {
          if(this.userList[index].role==='admin'){
            this.adminNo++;
          }else if(this.userList[index].role==='user'){
            this.userNo++;
          }else{
            this.deliveryPersonNo++;
          }
      }
      console.log(this.adminNo);
      this.chartGraph();
    });
  }

 // this method is use to open the EditUserComponent
  updateUser(userId: number) {
    console.log("update user called");
    this.javaService.userId = userId;
    const editDialog = new MatDialogConfig();
    editDialog.disableClose = true;
    editDialog.autoFocus = true;
    editDialog.width = "50%";
    this.dialog.open(EditUserComponent, editDialog);
  }

 // this method is use to delete the user  
  deleteUser(userId: number) {
    this.javaService.deleteUser(userId).subscribe(data=>{
      this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
        this.router.navigateByUrl('/userList');
       }
      );
    });
  }
 // this method is use to draw the chart 
  chartGraph(){
    this.canvas = document.getElementById('myChart');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'pie',
      data: {
          labels: ["Admin", "DeliveryPerson", "User"],
          datasets: [{
              label: '# of Votes',
              data: [this.adminNo,this.deliveryPersonNo,this.userNo],
              backgroundColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)'
              ],
              borderWidth: 1
          }]
      },
      options: {
        responsive: false,
        display:true
      }
    });

  }

}
