import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouteReuseStrategy } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";
import { NotificationService } from './share/notification.service';

// registration details 
export class UserInfo {
  public firstName: string;
  public lastName: string;
  public email: string;
  public mobileNumber: string;
  public password: string;
  public confirmPassword: string;
  public address: string;
  public address2: string;
  public district: string;
  public location: string;
  public pinCode: string;
  public state: string;
}

// login details
export class UserLogin {
  public userName: string;
  public userPassword: string;
}

//UserUpdate details
export class UserDetail {
  public userId: number;
  public firstName: string;
  public lastName: string;
  public email: string;
  public mobileNumber: string;
  public role: string;
}

// book details
export class BookDetails {
  public book_name: string;
  public authors: string;
  public description: string;
  public publisher: string;
  public publishedDate: string;
  public categories: string;
  public contentVersion: string;
  public isbn_type_10: string;
  public isbnNo1: number;
  public isbn_type_13: string;
  public isbnNo2: number;
  public smallThumbnail: string;
  public thumbnail: string;
  public amount: number = 0.0;
  public currencyCode: string;
  public checkPrice: boolean;
  public addressId: number;
}

// payment details
export class PaymentDetails {
  public paymentId: any;
  public transactionId: any;
  public status: string;
  public amount: number;
  public created: number;
  public addressId: number;
  public bookId: number;
}

@Injectable({
  providedIn: 'root'
})
export class JavaServiceService {

  bookObj = new BookDetails();
  payment = new PaymentDetails();

  //some variables are use in the project 
  bookISBN: string = "";
  bookId: any;
  bookList: any;
  notification: any;
  checkCart: boolean = false;
  addressNo: number;
  deliveryForBuy: boolean = false;
  userId: number;
  totalPrice: number = 0;
  oldBookStatus: boolean = false;
  paymentData: any;

  private urls: string;

  constructor(private http: HttpClient, private router: Router, public spinner: NgxSpinnerService, public zone: NgZone, public notificationService: NotificationService) {
  }

  public url = "http://localhost:8080/";

  // this method is use to registration 
  register(data: UserInfo) {
    this.urls = this.url + "register";
    return this.http.post(this.urls, data);
  }

  // this method is use to getting the user role and set the role in session storage
  getRole() {
    this.urls = this.url + "getRole";
    this.http.get(this.urls, { responseType: 'text' }).subscribe(
      data => {
        sessionStorage.setItem('userRole', data);
      }
    );
  }

  // this method is use to forget password
  forgetPassword(userName: string) {
    this.urls = this.url + "forgetPassword";
    this.http.post(this.urls, userName).subscribe(
      value => {
      }
    );
  }

  //this method is use to save the book details of user  
  requestBookDetails(bookObj: BookDetails) {
    this.urls = this.url + "bookDetailsRequest";
    this.http.post(this.urls, bookObj).subscribe(
      data => {
      }
    );
  }

  //this method is use to find the book by author
  findBookByAuthor(author: string) {
    this.urls = this.url + "fetchAuthor";
    return this.http.post(this.urls, author);
  }

  //this method is use to find the book by publisher
  getBookByPublisher(publisher: string) {
    this.urls = this.url + "fetchPublisher";
    return this.http.post(this.urls, publisher);
  }

  //this method is use to save address 
  addAddress(data: UserInfo) {
    this.urls = this.url + "addAddress";
    return this.http.post(this.urls, data);
  }

  //this method is use to get address
  getAddress() {
    this.urls = this.url + "getAddress";
    return this.http.get(this.urls);
  }

  // this method is use to return role of delivery person 
  hasRole() {
    if (sessionStorage.getItem('userRole') === "deliveryPerson") {
      return true;
    }
    return false;
  }

  // this method is use to return role of admin
  hasAdminRole() {
    if (sessionStorage.getItem('userRole') === "admin") {
      return true;
    }
    return false;
  }

  // this method is use to getting all the user
  userList() {
    this.urls = this.url + "listUser";
    return this.http.get(this.urls);
  }

  //this method is use to fetch user by Id
  getUserById(id: number) {
    this.urls = this.url + "fetchUser";
    return this.http.post(this.urls, id);
  }

  //this method is use to update the user details
  updateUserDetail(value: UserDetail) {
    this.urls = this.url + "updateUser";
    return this.http.post(this.urls, value);
  }

  // this mehod is use to delete the user 
  deleteUser(userId: number) {
    this.urls = this.url + "deleteUser";
    return this.http.post(this.urls, userId);
  }

  // this mehtod is use to get the notificaton for pickedup book 
  getDeliveryRequest() {
    this.urls = this.url + "getRequest";
    return this.http.get(this.urls);
  }

  // this method is use to update the book status 
  updateBookStatus(status: any) {
    this.urls = this.url + "bookStatus";
    this.http.post(this.urls, status).subscribe(
      data => {
        this.getSpinner();
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.router.navigateByUrl('deliveryRequest');
        });
      }
    );
  }

  // this method is use to get the notificaton for book delivered 
  getDeliverySellRequest() {
    this.urls = this.url + "getSellRequest";
    return this.http.get(this.urls);
  }

  //this method is use to get the book for show on home page
  findBooks(min: number, max: number) {
    this.urls = this.url + "findBooks/" + `${min}` + "/" + `${max}`;
    return this.http.get(this.urls);
  }

  // this metod is use to search the book by title or author name
  searchBook(searchValue: string) {
    this.urls = this.url + "searchBook";
    this.http.post(this.urls, searchValue).subscribe((books: any[]) => {
      this.bookList = books;
      this.getSpinner();
      this.router.navigate(['/showbook']);
    });
  }

  //this mehtod is use to fetch the book by bookcatagory
  searchCatagory(bookCatgory: any) {
    this.urls = this.url + "fetchCategory";
    this.http.post(this.urls, bookCatgory).subscribe((books: any[]) => {
      this.bookList = books;
      this.getSpinner();
      this.router.navigate(['/showbook']);
    });
  }

  //this mehtod is use to fetch all book for sell
  getAllBookForShop() {
    this.urls = this.url + "getAllBookForSell";
    this.http.get(this.urls).subscribe((books: any[]) => {
      this.bookList = books;
      this.getSpinner();
      this.router.navigate(['/showbook']);
    });
  }

  //this mehtod is use to fetch book by id
  getBookById(id: number) {
    this.urls = this.url + "fetch";
    return this.http.post(this.urls, id);
  }

  //this mehtod is use to add the book in cart 
  addSellOrderRequest(bookId: number) {
    this.urls = this.url + "sellBookRequest";
    this.http.post(this.urls, bookId).subscribe(
      totalRequest => {
        this.notification = totalRequest;
        sessionStorage.setItem('notification1', this.notification);
      }
    );
  }

  //this mehtod is use to return the notification form the session
  getNotification() {
    return sessionStorage.getItem('notification1');
  }

  //this mehtod is use to fetch the book notification 
  getBookNotification() {
    this.urls = this.url + "getNotification";
    this.http.get(this.urls).subscribe(
      totalRequest => {
        this.notification = totalRequest;
        sessionStorage.setItem('notification1', this.notification);
      }
    );
  }

  //this mehtod is use to store buy book request 
  getBuyBook() {
    this.urls = this.url + "getBuyBook";
    return this.http.get(this.urls);
  }

  //this mehtod is use to add the book quantity
  plusQuantity(requestId: number) {
    this.urls = this.url + "plusQuantity";
    this.http.post(this.urls, requestId).subscribe(
      data => {
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.router.navigateByUrl('checkout');
        });
      }
    )
  }

  //this mehtod is use to minus the book quantity
  minusQuantity(requestId: number) {
    this.urls = this.url + "minusQuantity";
    this.http.post(this.urls, requestId).subscribe(
      data => {
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.router.navigateByUrl('checkout');
        });
      }
    )
  }

  //this mehtod is use to get the book quantity
  getQuantity(bookId: number) {
    this.urls = this.url + "getQuantity";
    return this.http.post(this.urls, bookId);
  }

  //this method is use to get cart book quatity
  getCartBookQuantity(bookId: number) {
    this.urls = this.url + "getCartBookQuantity";
    return this.http.post(this.urls, bookId);
  }

  //this mehtod is use to delete book request 
  delteBookRequest(requestId: number) {
    this.urls = this.url + "deleteBookRequest";
    return this.http.post(this.urls, requestId);
  }

  //this method is use to add the address for mutiple book 
  bookDeliverAddressMultipleBook(addressId: number) {
    this.urls = this.url + "addDeliverAddress";
    this.http.post(this.urls, addressId).subscribe(
      totalRequest => {
        this.notification = totalRequest;
        sessionStorage.setItem('notification1', this.notification);
        this.router.navigateByUrl('mainslider');
      }
    );
  }

// this method is use to update buy book status
  updateBuyBookStatus(status: any) {
    this.urls = this.url + "updateBuyBookStatus";
    this.http.post(this.urls, status).subscribe(
      data => {
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
        this.router.navigateByUrl('deliverBuyRequest');
        });
      }
    );
  }

  // this method is use to fetch book by catagory
  getBookByCategory(category: string) {
    this.urls = this.url + "fetchCategory";
    return this.http.post(this.urls, category);
  }

  // this mehod is use to fetch book sell history
  getSellHistory() {
    this.urls = this.url + "sellHistory";
    return this.http.get(this.urls);
  }

  // this mehod is use to fetch book buy history
  getBuyHistory() {
    this.urls = this.url + "buyHistory";
    return this.http.get(this.urls);
  }

  // this mehod is use to fetch all sell book request 
  getDeliverySellRequestAdmin() {
    this.urls = this.url + "getSellRequestAdmin";
    return this.http.get(this.urls);
  }

  // this method is use to fetch all buy book request
  getDeliveryRequestAdmin() {
    this.urls = this.url + "getRequestAdmin";
    return this.http.get(this.urls);
  }

  // this method is use to get the book which price is zero 
  getBook() {
    this.urls = this.url + "getBook";
    return this.http.get(this.urls);
  }

  // this method is use to get the book which price is not xero 
  getAllBook() {
    this.urls = this.url + "getAllBook";
    return this.http.get(this.urls);
  }

  // this method is use to update the book price
  updateBookPrice(price: number, bookId: number) {
    var array = new Array(2);
    array[0] = bookId;
    array[1] = price;
    this.urls = this.url + "updateBookPrice";
    return this.http.post(this.urls, array).subscribe(
      data => {
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.router.navigateByUrl('updateBookPrice');
        });
      }
    );
  }

  // this mehtod is use to payment of single book 
  savePaymentDetails(payment1: PaymentDetails) {
    payment1.bookId = this.bookId;
    payment1.addressId = this.bookObj.addressId;
    this.urls = this.url + "savePayment";
    this.http.post(this.urls, payment1).subscribe(
      data => {
      });
  }

  //this mehod is use to paymet of multiple book 
  savePaymentDetailsMutipleBook(payment1: PaymentDetails) {
    payment1.addressId = this.bookObj.addressId;
    this.urls = this.url + "saveMultipleBookPayment";
    this.http.post(this.urls, payment1).subscribe(
      data => {
          this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.notification = 0;
          sessionStorage.setItem('notification1', this.notification);
          this.paymentData = data;
          this.notificationService.success(':: Payment successfully');
          this.getSpinner();
          this.zone.run(() => this.router.navigate(['invoice']));
        });
      });
  }

  // this method is use to get book invoice 
  getInvoice(transactionId: any) {
    this.urls = this.url + "getInvoice";
    return this.http.post(this.urls, transactionId);
  }

  // this method is use to spinner in project
  getSpinner() {
    this.spinner.show();
    setTimeout(() => {
      this.spinner.hide();
    }, 1000);
  }
}
