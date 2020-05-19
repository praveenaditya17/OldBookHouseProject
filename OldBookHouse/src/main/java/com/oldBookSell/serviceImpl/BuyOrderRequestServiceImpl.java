package com.oldBookSell.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.BuyOrderRequestDTO;
import com.oldBookSell.model.BuyOrderRequest;
import com.oldBookSell.repository.BuyOrderRequestRepository;
import com.oldBookSell.service.BuyOrderRequestService;

/**
 * This is BuyOrderRequestServiceImpl implements an application that
 * simply calls BuyOrderRequestService interface methods
 * @author Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
 */

@Service
public class BuyOrderRequestServiceImpl implements BuyOrderRequestService{

	private static final Logger LOGGER=LoggerFactory.getLogger(BuyOrderRequestServiceImpl.class);
	
	@Autowired
	private BuyOrderRequestRepository buyOrderRequestRepository;
	
	/**
	 * This mehod is used to purchase a particular book information
	 * @param buyOrderRequestDTO this is the parameter of saveRequest method
	 * @return int this returns book id
	 */
	
	@Override
	public int saveRequest(BuyOrderRequestDTO buyOrderRequestDto) {
		
		LOGGER.info("BuyOrderRequestService saveRequest method is calling....");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		BuyOrderRequest buyOrderRequestObj=new BuyOrderRequest();
		
		buyOrderRequestObj.setBookName(buyOrderRequestDto.getBookName());
		buyOrderRequestObj.setAuthors(buyOrderRequestDto.getAuthors());
		buyOrderRequestObj.setSmallThumbnail(buyOrderRequestDto.getSmallThumbnail());
		buyOrderRequestObj.setAmount(buyOrderRequestDto.getAmount());
		buyOrderRequestObj.setQuantity(buyOrderRequestDto.getQuantity());
		buyOrderRequestObj.setCheckStatus(buyOrderRequestDto.getCheckStatus());
		buyOrderRequestObj.setBookId(buyOrderRequestDto.getBookId());
		buyOrderRequestObj.setUserId(authentication.getName());
		buyOrderRequestObj.setAddressId(buyOrderRequestDto.getAddressId());
		buyOrderRequestObj.setDileveryPersonId(buyOrderRequestDto.getDileveryPersonId());
		buyOrderRequestObj.setStatus(buyOrderRequestDto.getStatus());
		buyOrderRequestObj.setTransactionId(buyOrderRequestDto.getTransactionId());
		
		BuyOrderRequest result=buyOrderRequestRepository.checkBook(authentication.getName(),buyOrderRequestDto.getBookId(),"user");
		if(result!=null) {
			addQuantity(result.getBuyOrderRequestId());
			LOGGER.info("In BuyOrderRequestService BuyOrderRequestId is: "+result.getBuyOrderRequestId());
		}else {
			LOGGER.info("Buy Book Request save to buy_order_request Table ");
			buyOrderRequestRepository.save(buyOrderRequestObj);
		}	
		return buyOrderRequestRepository.countOrderRequest(authentication.getName(),"user");		
	}
	
	/**
	 * This method s used to get notification 
	 * @return int this returns total number of notification 
	 */

	@Override
	public int getNotification() {
		LOGGER.info("BuyOrderRequestService getNotification method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.countOrderRequest(authentication.getName(),"user");
	}
	
	/**
	 * This method is used to show all purschase book
	 * @return List<BuyOrderRequest> this returns purchase book information
	 */
	
	@Override
	public List<BuyOrderRequest> findBuyHistory(){
		LOGGER.info("BuyOrderRequestService findBuyHistory method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.findBuyHistory(authentication.getName());
	}

	/**
	 * This method is used to get purchase book information
	 * @return List<BuyOrderRequest> this returns list of book information
	 */
	
	@Override
	public List<BuyOrderRequest> getOrderRequest() {
		LOGGER.info("BuyOrderRequestService getOrderRequest method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.getOrderRequest(authentication.getName(),"user");
	}
	
	/**
	 * This method is used to delete the request of purchase book
	 * @param requestBookId this is the parameter of deleteBookRequest method
	 * @return int this returns zero 
	 */
	
	@Override
	public void deleteBookRequest(int requestBookId) {
		LOGGER.info("BuyOrderRequestService deleteBookRequest method is calling....");
		buyOrderRequestRepository.deleteById(requestBookId);
	}
	
	/**
	 * This method is used to request to  a delivery person to purchase a book
	 * @return Iterable<Object> this returns book information
	 */

	@Override
	public Iterable<Object> deliverySellRequest(int deliveryId) {
		LOGGER.info("BuyOrderRequestService deliverySellRequest method is calling....");
		Iterable<Object>result= buyOrderRequestRepository.deliveryPersonRequest(deliveryId);
		return result;
	}
	
	/**
	 * This method is used to update purchase book status
	 * @param buyOrderRequestDTO this is the first parameter of updateBuyBookStatus method 
	 * @param checkStatus this is the second parameter of updateBuyBookStatus method
	 */

	@Override
	public void updateBuyBookStatus(int buyOrderRequestId, String checkStatus) {
		LOGGER.info("BuyOrderRequestService updateBuyBookStatus method is calling....");
		buyOrderRequestRepository.updateBuyBookStatus(buyOrderRequestId,checkStatus);
	}
	
	/**
	 * This method is used to show all the buy request to admin  
	 * @return Iterable<Object> ths returns list of book information
	 */
	
	@Override
	public Iterable<Object> deliverySellRequestAdmin() {
		LOGGER.info("BuyOrderRequestService deliverySellRequestAdmin method is calling....");
		return buyOrderRequestRepository.deliveryGetAdmin();
	}
	
	/**
	 * This method is used to increse the quantity of book by one
	 * @param requestBookId this is the parameter of addQuantity method
	 * @return BuyOrderRequest this returns a book information
	 */
	
	@Override
	public BuyOrderRequest addQuantity(int requestBookId) {
		LOGGER.info("BuyOrderRequestService addQuantity method is calling....");
		Optional<BuyOrderRequest> buyOrderRequest =buyOrderRequestRepository.findById(requestBookId);
		BuyOrderRequest buyOrderRequestObj=new BuyOrderRequest();
		buyOrderRequestObj=buyOrderRequest.get();
		buyOrderRequestObj.setQuantity(buyOrderRequestObj.getQuantity()+1);
		LOGGER.info("In BuyOrderRequestService quantity"+buyOrderRequestObj.getQuantity());
		return buyOrderRequestRepository.save(buyOrderRequestObj);
		
	}
	
	/**
	 * This method is used to decreses the quantity of book by one
	 * @param requestBookId this is the parameter of minusQuantity method
	 * @return BuyOrderRequest this returns a book information
	 */

	@Override
	public BuyOrderRequest minusQuantity(int requestBookId) {
		LOGGER.info("BuyOrderRequestService minusQuantity method is calling....");
		Optional<BuyOrderRequest> buyOrderRequest =buyOrderRequestRepository.findById(requestBookId);
		BuyOrderRequest buyOrderRequestObj=new BuyOrderRequest();
		buyOrderRequestObj=buyOrderRequest.get();
		buyOrderRequestObj.setQuantity(buyOrderRequestObj.getQuantity()-1);
		LOGGER.info("In BuyOrderRequestService quantity"+buyOrderRequestObj.getQuantity());
		return buyOrderRequestRepository.save(buyOrderRequestObj);
	}
	
	/**
	 * This method is used to add delivery address
	 * @param addressId this is the first parameter of addDeliveryAddress method
	 * @param deliveryPersonId this is the second parameter of adddeliveryAddress method
	 * @param status this is the third parameter of adddeliveryAddress method
	 * @param transactionId this is the fourth parameter of adddeliveryAddress method
	 * @return List<BuyOrderRequest> this returns list of addresses
	 */
	
	@Override
	public List<BuyOrderRequest> addDeliverAddress(int addressId, int deliveryPersonId, String status, String transactionId) {
		LOGGER.info("BuyOrderRequestService addDeliverAddress method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<BuyOrderRequest> result= buyOrderRequestRepository.getBuyRequest(authentication.getName(),"user");
		buyOrderRequestRepository.addDeliverAddress("ProcessingOrder",addressId,deliveryPersonId,status,transactionId,authentication.getName(),"user");
		return result;
	}
	
	/**
	 * This method is used to get total number of book added in cart
	 * @param bookId this is the parameter of getQuantity method 
	 * @return int this returns number of book added in cart
	 */

	@Override
	public int getQuantity(int bookId) {
		LOGGER.info("BuyOrderRequestService getQuantity method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		BuyOrderRequest result=buyOrderRequestRepository.checkBook(authentication.getName(),bookId,"user");
		if(result!=null)
			return result.getQuantity();
		else
			return 0;
	}

}
