import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MainsliderComponent } from './mainslider/mainslider.component';
import { ProductdisplayComponent } from './productdisplay/productdisplay.component';
import { FooterComponent } from './footer/footer.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatSliderModule }  from '@angular/material/slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule} from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';

//  import { FlexLayoutModule } from '@angular/flex-layout';

import { MatCardModule } from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';

import { LoginServeiceService } from './share/login-serveice.service';
import { RegistrationComponent } from './registration/registration.component';
import { RegistrationService } from './share/registration.service';
import { NotificationService } from './share/notification.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BasicAuthHttpInterceptorService } from './service/basic-auth-http-interceptor.service';

import { AuthGardService } from './service/auth-gard.service';
import {} from './java-service.service';
import { BooksellComponent } from './booksell/booksell.component';
import { BookreqAddressComponent } from './bookreq-address/bookreq-address.component';
import { BookSellSearchComponent } from './book-sell-search/book-sell-search.component';
import { DeliveryRequestComponent } from './delivery-request/delivery-request.component';
import { UpdateBookStatusComponent } from './update-book-status/update-book-status.component';

import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { ProfileComponent } from './profile/profile.component';
import { ShowBookComponent } from './show-book/show-book.component';
import { BuyBookComponent } from './buy-book/buy-book.component';
import { BookDeliverAddressComponent } from './book-deliver-address/book-deliver-address.component';
import { DeliveryBuyRequestComponent } from './delivery-buy-request/delivery-buy-request.component';
import { UserListComponent } from './user-list/user-list.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { RefreshComponent } from './refresh/refresh.component';
import { BuyHistoryComponent } from './buy-history/buy-history.component';
import { SellHistoryComponent } from './sell-history/sell-history.component';
import { NgxSpinnerModule } from "ngx-spinner";
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { UpdateBookPriceComponent } from './update-book-price/update-book-price.component';
import { UpdatePriceComponent } from './update-price/update-price.component';
import { PaymentComponent } from './payment/payment.component';
import { ContactComponent } from './contact/contact.component';
import { InvoiceComponent } from './invoice/invoice.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainsliderComponent,
    ProductdisplayComponent,
    FooterComponent,
    CheckoutComponent,
    LoginComponent,
    RegistrationComponent,
    BooksellComponent,
    BookreqAddressComponent,
    BookSellSearchComponent,
    DeliveryRequestComponent,
    UpdateBookStatusComponent,
    ProfileComponent,
    ShowBookComponent,
    BuyBookComponent,
    BookDeliverAddressComponent,
    DeliveryBuyRequestComponent,
    UserListComponent,
    EditUserComponent,
    RefreshComponent,
    BuyHistoryComponent,
    SellHistoryComponent,
    ForgetPasswordComponent,
    UpdateBookPriceComponent,
    UpdatePriceComponent,
    PaymentComponent,
    ContactComponent,
    InvoiceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    MatGridListModule,
    MatSliderModule,
    MatInputModule,
    MatFormFieldModule,
    MatRadioModule,
    MatSelectModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatCheckboxModule,
    MatNativeDateModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule,
    MatSnackBarModule,
    MatCardModule,
    MatListModule,
    MatSidenavModule,
    HttpClientModule,
    InfiniteScrollModule,
    NgxSpinnerModule
  ],
  providers: [LoginServeiceService,RegistrationService,NotificationService,AuthGardService,
    {    
      provide:HTTP_INTERCEPTORS,useClass:BasicAuthHttpInterceptorService,multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
