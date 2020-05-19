import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from "ngx-spinner";
import { JavaServiceService } from '../java-service.service';

@Component({
  selector: 'app-mainslider',
  templateUrl: './mainslider.component.html',
  styleUrls: ['./mainslider.component.css']
})
export class MainsliderComponent implements OnInit {

  constructor(public spinner:NgxSpinnerService,private javaService:JavaServiceService) { }

  ngOnInit() {

  }

  // this method is use to getAllBook 
  getAllBook(){
    this.javaService.getAllBookForShop();
  }

}
