package com.oldBookSell.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldBookSell.dto.BookDTO;
import com.oldBookSell.dto.BuyOrderRequestDTO;
import com.oldBookSell.dto.OldBookSellDTO;
import com.oldBookSell.dto.PaymentDTO;
import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.Book;
import com.oldBookSell.model.BuyOrderRequest;
import com.oldBookSell.model.Payment;
import com.oldBookSell.model.SellOrderRequest;
import com.oldBookSell.model.UserDetails;
import com.oldBookSell.service.BookService;
import com.oldBookSell.service.BuyOrderRequestService;
import com.oldBookSell.service.OldBookSellServices;
import com.oldBookSell.service.PaymentService;
import com.oldBookSell.service.SellOrderRequestService;
import com.stripe.model.Charge;


/**
 	* This is OldBookSellController implements an application that
 	* simply calls com.oldbooksell.service package interface methods.
	* @author  Kundan,Praveen
	* @version 1.0
	* @since 2020-05-18
*/

@RestController
@RequestMapping
@CrossOrigin
public class OldBookSellController {
	
		private static final Logger LOGGER=LoggerFactory.getLogger(OldBookSellController.class);
	
		@Autowired
		private OldBookSellServices oldBookSellServices;
		
		@Autowired
		private SellOrderRequestService sellOrderRequestService;
		
		@Autowired
		private BuyOrderRequestService buyOrderRequestService;
		
		@Autowired
		private BookService bookService;
		
		@Autowired
		private PaymentService paymentService;
		
		/* Controller for OldBookSellService */
		
		/**
		   * This method is used to register user. 
		   * @param userDetail This is the paramter to createUser method
		   * @return OldBookSellDTO This returns user details.
		 */
		
		@RequestMapping("/register")
		public OldBookSellDTO createUser(@RequestBody OldBookSellDTO userDetail) {
			LOGGER.info("Controller createUser method is caiing....");
			return  oldBookSellServices.createUser(userDetail);
		}
		
		/**
		   * This method is used to forget password. 
		   * @param userName This is the paramter of forgetPassword method
		 */
		
		@RequestMapping("/forgetPassword")
		public void forgetPassword(@RequestBody String userName) {
			LOGGER.info("Controller forgetPassword method is caiing....");
			oldBookSellServices.changePassword(userName);
		}
		
		/**
		   * This method is used to add exgisting user address. 
		   * @param userDetail This is the paramter of addAddress method
		   * @return UserDetails This returns user details.
		*/
		
		@RequestMapping("/addAddress")
		public UserDetails addAddress(@RequestBody OldBookSellDTO userDetail) {
			LOGGER.info("Controller addAddress method is caiing....");
			return  oldBookSellServices.addAddress(userDetail);
		}
		
		/**
		 * This method is used to get exgisting user address. 
		 * @return UserDetails This returns user details.
		 */
		
		@GetMapping("/getAddress")
		public UserDetails getAddress() {
			LOGGER.info("Controller userDetails method is caiing....");
			return oldBookSellServices.getAddress();
		}
		
		/**
		 *  This method is used to get List of exgisting user.
		 * @return Iterable<UserDetails> this returns user details
		 */
		
		@GetMapping("/listUser")
		public Iterable<UserDetails> userList(){
			LOGGER.info("Controller userList method is caiing....");
			return oldBookSellServices.userList();
		}
		
		/**
		 * This method is used to get a exgisting user details.
		 * @param id This is the paramter of getUserById method
		 * @return UserDetails This returns user details
		 */
		
		@RequestMapping("/fetchUser")
		public UserDetails getUserById(@RequestBody int id) {
			LOGGER.info("Controller getUserById method is caiing....");
			return oldBookSellServices.findById(id).get();
		}
		
		/**
		 * This method is used to update exgisting user
		 * @param user This is the parameter of updateUser method
		 * @return UserDetails This returns user details
		 */
		
		@RequestMapping("/updateUser")
		public UserDetails updateUser(@RequestBody UserDetails user){
			LOGGER.info("Controller updateUser method is caiing....");
			return oldBookSellServices.updateUser(user).get();
		}
		
		/**
		 * This method is used to delete exgisting user
		 * @param userId This is the parameter of deleteUser method
		 * @return int This returns 0
		 */
		
		@RequestMapping("/deleteUser")
		public int deleteUser(@RequestBody int userId ){
			LOGGER.info("Controller deleteUser method is caiing....");
			return oldBookSellServices.deleteUser(userId);
		}
		
		/**
		 * This method is used to get the role of exgisting user
		 * @return String this returns user role
		 */
		
		@GetMapping("/getRole")
		public String getRole() {
			LOGGER.info("Controller getRole method is caiing....");
			return oldBookSellServices.getRole();
		}
		
		/* Controller for sellOrderRequestService */
		
		/**
		 * This method is used to save sell request order
		 * @param sellOrderRequestDTO this is the parameter of addBookDetails
		 * @return SellOrderRequestDTO this returns Book Information
		 */
		
		@RequestMapping("/bookDetailsRequest")
		public SellOrderRequestDTO addBookDetails(@RequestBody SellOrderRequestDTO sellOrderRequestDTO) {
			LOGGER.info("Controller addBookDetails method is caiing....");
			int deliveryId=oldBookSellServices.getDeliveryPerson();
			LOGGER.info("In Controller addBookDetails method Delivery Id=" +deliveryId);
			sellOrderRequestDTO.setDileveryPersonId(deliveryId);
			return sellOrderRequestService.bookRequest(sellOrderRequestDTO);
		}
		
		/**
		 * This method is used to get sell order dellivery request
		 * @return Iterable<Object> this returns book user information
		 */
		
		@GetMapping("/getRequest")
		public Iterable<Object> getDeliveryRequest() {
			LOGGER.info("Controller getDeliveryRequest method is caiing....");
			int deliveryId=oldBookSellServices.getDeliveryPersonId();
			LOGGER.info("In Controller getDeliveryRequest method Delivery Id=" +deliveryId);
			return sellOrderRequestService.deliveryRequest(deliveryId);
		}
		
		/**
		 * This method is used to get all delivery request to admin
		 * @return Iterable<Object> this returns Iterable<Object>
		 */
		
		@GetMapping("/getRequestAdmin")
		public Iterable<Object> getDeliveryRequestAdmin() {
			LOGGER.info("Controller getDeliveryReuestAdmin method is caiing....");
			return sellOrderRequestService.deliveryRequestAdmin();
		}
		
		/**
		 * This method is used to get all requested and sold book
		 * @return List<SellOrderRequest> this returns list of book
		 */
		
		@RequestMapping("/sellHistory")
		public List<SellOrderRequest> findSellHistory(){
			LOGGER.info("Controller findSellHistory method is caiing....");
			return sellOrderRequestService.findSellHistory();
		}
		
		/* Controller for BookService */
		
		/**
		 * This method is used to update Book status
		 * @param sellOrderRequestDTO this is the parameter updateBookStats
		 */
		
		@RequestMapping("/bookStatus")
		public void updateBookStatus(@RequestBody SellOrderRequestDTO sellOrderRequestDTO) {
			LOGGER.info("Controller updateBookStatus method is caiing....");
			LOGGER.info(" In Controller updateBookStatus method status is "+sellOrderRequestDTO.getCheck_status());
			LOGGER.info(" In Controller updateBookStatus method feedback is "+sellOrderRequestDTO.getFeedBack());
			sellOrderRequestService.updateBookStatus(sellOrderRequestDTO);
			
			if(sellOrderRequestDTO.getCheck_status().equals("PickUpOrder")) {
				Optional<SellOrderRequest> sellOrderRequest=sellOrderRequestService.findBookById(sellOrderRequestDTO.getSellOrderRequestId());

				BookDTO bookDTOObj = new BookDTO();
				bookDTOObj.setAmount(sellOrderRequest.get().getAmount());
				bookDTOObj.setAuthors(sellOrderRequest.get().getAuthors());
				bookDTOObj.setBookName(sellOrderRequest.get().getBookName());
				bookDTOObj.setBookStatus("sell");
				bookDTOObj.setCategories(sellOrderRequest.get().getCategories());
				bookDTOObj.setCurrencyCode(sellOrderRequest.get().getCurrencyCode());
				bookDTOObj.setDescription(sellOrderRequest.get().getDescription());
				bookDTOObj.setIsbnNo1(sellOrderRequest.get().getIsbnNo1());
				bookDTOObj.setIsbnNo2(sellOrderRequest.get().getIsbnNo2());
				bookDTOObj.setIsbnType10(sellOrderRequest.get().getIsbnType10());
				bookDTOObj.setIsbnType13(sellOrderRequest.get().getIsbnType13());
				bookDTOObj.setPublishedDate(sellOrderRequest.get().getPublishedDate());
				bookDTOObj.setPublisher(sellOrderRequest.get().getPublisher());
				bookDTOObj.setQuantity(sellOrderRequest.get().getQuantity());
				bookDTOObj.setSmallThumbnail(sellOrderRequest.get().getSmallThumbnail());
				bookDTOObj.setThumbnail(sellOrderRequest.get().getThumbnail());
				LOGGER.info("Book information save in book table");
				bookService.saveBook(bookDTOObj);
			}
		}

		/**
		 * This method is used to get the the list of book after scroll
		 * @param min this is the first parameter of getBook method
		 * @param max this is the second parameter of getBook method
		 * @return List<Book> ths returns list of book
		 */
		
		@GetMapping("/findBooks/{min}/{max}")
		public List<Book> getBook(@PathVariable(value = "min")int min,@PathVariable(value = "max")int max){
			LOGGER.info("Controller getBook method is caiing....");
			LOGGER.info("In Controller getBook method Min=" +min);
			LOGGER.info("In Controller getBook method Max=" +max);
			return bookService.findBooks(min,max);
		}
			
		/**
		 * This method is used to get a particular Book details
		 * @param bookId this is the parameter of fetchBook method
		 * @return Optional<Book> this returns a particular book information
		 */
		
		@RequestMapping("/fetch")
		public Optional<Book> fetchBook(@RequestBody int bookId) {
			LOGGER.info("Controller fetchBook method is caiing....");
			LOGGER.info("In Controller fetchBook method bookId=" +bookId);
			return bookService.findBookById(bookId);
		}
		
		/**
		 * This method is used to get book by author name
		 * @param author this is the parameter of findBookByAutho methodr
		 * @return List<Book> this returns list of book
		 */
		
		@RequestMapping("/fetchAuthor")
		public List<Book> findBookByAuthor(@RequestBody String author){
			LOGGER.info("Controller findBookByAuthor method is caiing....");
			LOGGER.info("In Controller findBookByAuthor method Author Name=" +author);
			return bookService.findBookByAuthor(author);
		}
		
		/**
		 * This method is used to get book by publisher name
		 * @param publisher this is the paramter of findBookByPublisher method
		 * @return List<Book> this returns list of book
		 */
		
		@RequestMapping("/fetchPublisher")
		public List<Book> findBookByPublisher(@RequestBody String publisher){
			LOGGER.info("Controller findBookByPublisher method is caiing....");
			LOGGER.info("In Controller findBookByPublisher method Publisher Name=" +publisher);
			return bookService.findBookByPublisher(publisher);
		}
		
		/**
		 * This method is used to get book by category
		 * @param category this is the parameter of findBookByCategory method
		 * @return List<Book> this returns list of book
		 */
		
		@RequestMapping("/fetchCategory")
		public List<Book> findBookByCategory(@RequestBody String category){
			LOGGER.info("Controller findBookByCategory method is caiing....");
			LOGGER.info("In Controller findBookByCategory method Category=" +category);
			return bookService.findBookByCategory(category);
		}
		
		/**
		 * This method is used to get total number of a particular book
		 * @param bookId this is the parameter of getQuantity method
		 * @return int this returns number of a particular book
		 */
		
		@RequestMapping("/getQuantity")
		public int getQuantity(@RequestBody int bookId){
			LOGGER.info("Controller getQuantity method is caiing....");
			return bookService.getQuantity(bookId);
		}
		
		/**
		 * This method is used to find all distinct available  book catogory
		 * @return Iterable<Object> this returns list of book by catogory
		 */
		
		@GetMapping("/allCatogory")
		public Iterable<Object> findAllCatogory(){
			LOGGER.info("Controller findAllCatogory method is caiing....");
			return bookService.findAllCatogory();
		}
		
		/**
		 * This method is used to search book by title or author name or publisher or isbn number
		 * @param bookName this is the parameter of searchBook method
		 * @return List<Book> this returns list of books
		 */
		
		@RequestMapping("/searchBook")
		public List<Book> searchBook(@RequestBody String bookName) {
			LOGGER.info("Controller searchBook method is caiing....");
			return bookService.findBookByNameAuthorAndIsbn(bookName);
			
		}
		
		/**
		 * This method is used to find all book
		 * @return List<Book> this returns list of books
		 */
		
		@GetMapping("/getBook")
		public List<Book> findAllBook(){
			LOGGER.info("Controller findAllBook method is caiing....");
			return bookService.getAllBook();
		}
		
		/**
		 * This method is used to fin all book for updated
		 * @return List<Book> this returns list of books
		 */
		
		@GetMapping("/getAllBook")
		public List<Book> findAllBookForUpdate(){
			LOGGER.info("Controller findAllBookForUpdate method is caiing....");
			return bookService.getAllBookForUpdate();
		}
		
		/**
		 * This method is used to upadte the price of book
		 * @param arr this is the parameter of updateBookPrice method
		 * @return Book this returns book information
		 */
		
		@PostMapping("/updateBookPrice")
		public Book updateBookPrice(@RequestBody int arr[]){
			LOGGER.info("Controller updateBookPrice method is caiing....");
			LOGGER.info("In Controller updateBookPrice method arr[0]="+arr[0]);
			LOGGER.info("In Controller updateBookPrice method arr[1]="+arr[1]);
			return bookService.updateBookPrice(arr[0],arr[1]);
		}
		
		/**
		 * This method is used to find all book for sell
		 * @return List<Book> this returns list of books
		 */
		
		@GetMapping("/getAllBookForSell")
		public List<Book> findAllBookForSell(){
			LOGGER.info("Controller findAllBookForSell method is caiing....");
			return bookService.findAllBookForSell();
		}
		
		/* Controller for BuyorderRequestService */
		
		/**
		 * This mehod is used to purchase a particular book information
		 * @param bookId this is the parameter of addBuyOrderRequest method
		 * @return int this returns book id
		 */
		
		@RequestMapping("/sellBookRequest")
		public int addBuyOrderRequest(@RequestBody int bookId) {
			LOGGER.info("Controller addBuyOrderRequest method is caiing....");
			Optional<Book> sellOrderRequest=bookService.findBookById(bookId);
			LOGGER.info(" In Controller sellOrderRequest="+sellOrderRequest.get().getBookName());
			BuyOrderRequestDTO buyOrderRequestDTO =new BuyOrderRequestDTO();
			
			buyOrderRequestDTO.setBookName(sellOrderRequest.get().getBookName());
			buyOrderRequestDTO.setAuthors(sellOrderRequest.get().getAuthors());
			buyOrderRequestDTO.setSmallThumbnail(sellOrderRequest.get().getSmallThumbnail());
			buyOrderRequestDTO.setAmount(sellOrderRequest.get().getAmount());
			buyOrderRequestDTO.setQuantity(1);
			buyOrderRequestDTO.setCheckStatus("user");
			buyOrderRequestDTO.setBookId(sellOrderRequest.get().getBookId());		
			return buyOrderRequestService.saveRequest(buyOrderRequestDTO);
		}
		
		/**
		 * This method s used to get notification 
		 * @return int this returns total number of notification 
		 */
		
		@RequestMapping("/getNotification")
		public int getNotification() {
			LOGGER.info("Controller getNotification method is caiing....");
			return buyOrderRequestService.getNotification();
		}
		
		/**
		 * This method is used to get purchase book information
		 * @return List<BuyOrderRequest> this returns list of book information
		 */
		
		@RequestMapping("/getBuyBook")
		public List<BuyOrderRequest> getOderRequest(){
			LOGGER.info("Controller getOderRequest method is caiing....");
			return buyOrderRequestService.getOrderRequest();
		}
		
		/**
		 * This method is used to increse the quantity of book by one
		 * @param requestBookId this is the parameter of addQuantity method
		 * @return BuyOrderRequest this returns a book information
		 */
		
		@RequestMapping("/plusQuantity")
		public BuyOrderRequest addQuantity(@RequestBody int requestBookId){
			LOGGER.info("Controller addQuantity method is caiing....");
			return buyOrderRequestService.addQuantity(requestBookId);
		}
		
		/**
		 * This method is used to decreses the quantity of book by one
		 * @param requestBookId this is the parameter of substractQuantity method
		 * @return BuyOrderRequest this returns a book information
		 */
		
		@RequestMapping("/minusQuantity")
		public BuyOrderRequest substractQuantity(@RequestBody int requestBookId){
			LOGGER.info("Controller substractQuantity method is caiing....");
			return buyOrderRequestService.minusQuantity(requestBookId);
		}
		
		/**
		 * This method is used to get total number of book added in cart
		 * @param bookId this is the parameter of getCartBookQuantity method 
		 * @return int this returns number of book added in cart
		 */
		
		@RequestMapping("/getCartBookQuantity")
		public int getCartBookQuantity(@RequestBody int bookId){
			return buyOrderRequestService.getQuantity(bookId);
		}
		
		/**
		 * This method is used to delete the request of purchase book
		 * @param requestBookId this is the parameter of deleteBookRequest method
		 * @return int this returns zero 
		 */
		
		@RequestMapping("/deleteBookRequest")
		public int deleteBookRequest(@RequestBody int requestBookId){
			LOGGER.info("Controller deleteBookRequest method is caiing....");
			buyOrderRequestService.deleteBookRequest(requestBookId);
			return 0;
		}
		
		/**
		 * This method is used to request to  a delivery person to purchase a book
		 * @return Iterable<Object> this returns book information
		 */
		
		@GetMapping("/getSellRequest")
		public Iterable<Object> getDeliverySellRequest() {
			LOGGER.info("Controller getDeliverySellRequest method is calling....");
			int deliveryId=oldBookSellServices.getDeliveryPersonId();
			LOGGER.info("In Controller getDeliverySellRequest method Delivery ID=" +deliveryId);
			return buyOrderRequestService.deliverySellRequest(deliveryId);
		}
		
		/**
		 * This method is used to update purchase book status
		 * @param sellOrderRequestDTO this is the parameter of updateBuyBookStatus method 
		 */
		
		@RequestMapping("/updateBuyBookStatus")
		public void updateBuyBookStatus(@RequestBody SellOrderRequestDTO sellOrderRequestDTO) {
			LOGGER.info("Controller updateBuyBookStatus method is calling....");
			LOGGER.info("In Controller updateBuyBookStatus method SellOrderRequestId = "+sellOrderRequestDTO.getSellOrderRequestId());
			LOGGER.info("In Controller updateBuyBookStatus method sellOrderRequestDTO.getFeedBack = "+sellOrderRequestDTO.getFeedBack());
			buyOrderRequestService.updateBuyBookStatus(sellOrderRequestDTO.getSellOrderRequestId(),sellOrderRequestDTO.getCheck_status());
		}
		
		/**
		 * This method is used to show all the buy request to admin  
		 * @return Iterable<Object> ths returns list of book information
		 */
		
		@GetMapping("/getSellRequestAdmin")
		public Iterable<Object> getDeliverySellRequestAdmin() {
			LOGGER.info("Controller getDeliverySellRequestAdmin method is caiing....");
			return buyOrderRequestService.deliverySellRequestAdmin();
		}
		
		/**
		 * This method is used to show all purschase book
		 * @return List<BuyOrderRequest> this returns purchase book information
		 */
		
		@RequestMapping("/buyHistory")
		public List<BuyOrderRequest> findBuyHistory(){
			LOGGER.info("Controller findBuyHistory method is calling....");
			return buyOrderRequestService.findBuyHistory();
		}
		
		/* Controller for PaymentService */
		
		/**
		 * This method is used to payment
		 * @param grandTotal this is the first parameter of chargeCard method
		 * @param request this is the second parameter of chargeCard method
		 * @return Charge this returns payment information
		 * @throws Exception if something wrong then throw exception
		 */
		
		@PostMapping("/charge")
	    public Charge chargeCard(@RequestBody int grandTotal, HttpServletRequest request) throws Exception {
			LOGGER.info("Controller chargeCard method is caiing....");
	        String token = request.getHeader("token");
	        int amount = grandTotal;
	        LOGGER.info("In Controller Token" +token);
	        LOGGER.info("In Controller Amount" +amount);
	        return paymentService.chargeCreditCard(token, amount);
	    }
		
		/**
		 * This method is used to save single book payment information  
		 * @param payment this is the parameter of savePayment method
		 * @return Payment this return single book payment information
		 */
		
		@PostMapping("/savePayment")
		public Payment savePayment(@RequestBody PaymentDTO payment) {
			LOGGER.info("Controller savePayment method is caiing....");
			Optional<Book> sellOrderRequest=bookService.findBookById(payment.getBookId());
			BuyOrderRequestDTO buyOrderRequestDTO =new BuyOrderRequestDTO();
			
			buyOrderRequestDTO.setBookName(sellOrderRequest.get().getBookName());
			buyOrderRequestDTO.setAuthors(sellOrderRequest.get().getAuthors());
			buyOrderRequestDTO.setSmallThumbnail(sellOrderRequest.get().getSmallThumbnail());
			buyOrderRequestDTO.setAmount(sellOrderRequest.get().getAmount());
			buyOrderRequestDTO.setQuantity(sellOrderRequest.get().getQuantity());
			buyOrderRequestDTO.setCheckStatus("PendingOrder");
			buyOrderRequestDTO.setAddressId(payment.getAddressId()+"");
			buyOrderRequestDTO.setBookId(sellOrderRequest.get().getBookId());
			buyOrderRequestDTO.setStatus(payment.getStatus());
			buyOrderRequestDTO.setTransactionId(payment.getTransactionId());

			int deliveryId=oldBookSellServices.getDeliveryPerson();
			buyOrderRequestDTO.setDileveryPersonId(deliveryId);

			buyOrderRequestService.saveRequest(buyOrderRequestDTO);
			bookService.minusQuantity(payment.getBookId(),1);
	
			return paymentService.savePayment(payment);

		}
		
		/**
		 * This method is used to save multiple book payment information
		 * @param payment this is the parameter od 
		 * @return Payment this returns payments information
		 */
		
		@PostMapping("/saveMultipleBookPayment")
		public Payment saveMultipleBookPayment(@RequestBody PaymentDTO payment) {
			LOGGER.info("Controller saveMultipleBookPayment method is caiing....");
			
			int deliveryId=oldBookSellServices.getDeliveryPerson();
			List<BuyOrderRequest> buyOrderObject=buyOrderRequestService.addDeliverAddress(payment.getAddressId(),deliveryId,payment.getStatus(),payment.getTransactionId());
			for(int i=0;i<buyOrderObject.size();i++) {
				bookService.minusQuantity(buyOrderObject.get(i).getBookId(),buyOrderObject.get(i).getQuantity());
			}
			return paymentService.savePayment(payment);
		
		}
		
		/**
		 * This method is used to genrate invoice
		 * @param transatctionId
		 * @return Iterable<Object> this returns invoice
		 */
		
		@PostMapping("/getInvoice")
		public Iterable<Object> getInvoice(@RequestBody String transatctionId) {
			LOGGER.info("Controller getInvoice method is calling...");
			return paymentService.getInvoice(transatctionId);
			
		}
}
