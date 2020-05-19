package com.oldBookSell.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.SellOrderRequest;
import com.oldBookSell.repository.SellOrderRequestRepository;
import com.oldBookSell.service.SellOrderRequestService;

/**
 * This is SellOrderRequestServiceImpl implements an application that
 * simply calls SellOrderRequestService interface methods
 * @author Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

@Service
public class SellOrderRequestServiceImpl implements SellOrderRequestService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(SellOrderRequestServiceImpl.class);

	@Autowired
	private SellOrderRequestRepository sellOrderRequest;
	
	/**
	 * This method is used to save sell request order
	 * @param sellOrderRequestDTO this is the parameter of bookRequest method
	 * @return SellOrderRequestDTO this returns Book Information
	 */
	
	@Override
	public SellOrderRequestDTO bookRequest(SellOrderRequestDTO sellOrderRequestDTO) {
		LOGGER.info("SellOrderRequestService bookRequest method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		SellOrderRequest sellOrderRequestObj=new SellOrderRequest();
		
		sellOrderRequestObj.setBookName(sellOrderRequestDTO.getBook_name());
		sellOrderRequestObj.setAuthors(sellOrderRequestDTO.getAuthors());
		sellOrderRequestObj.setDescription(sellOrderRequestDTO.getDescription());
		sellOrderRequestObj.setPublisher(sellOrderRequestDTO.getPublisher());
		sellOrderRequestObj.setPublishedDate(sellOrderRequestDTO.getPublishedDate());
		sellOrderRequestObj.setCategories(sellOrderRequestDTO.getCategories());
		sellOrderRequestObj.setIsbnType10(sellOrderRequestDTO.getIsbn_type_10());
		sellOrderRequestObj.setIsbnNo1(sellOrderRequestDTO.getIsbnNo1());
		sellOrderRequestObj.setIsbnType13(sellOrderRequestDTO.getIsbn_type_13());
		sellOrderRequestObj.setIsbnNo2(sellOrderRequestDTO.getIsbnNo2());
		sellOrderRequestObj.setSmallThumbnail(sellOrderRequestDTO.getSmallThumbnail());
		sellOrderRequestObj.setThumbnail(sellOrderRequestDTO.getThumbnail());
		sellOrderRequestObj.setAmount(sellOrderRequestDTO.getAmount());
		sellOrderRequestObj.setCurrencyCode(sellOrderRequestDTO.getCurrencyCode());
		sellOrderRequestObj.setQuantity(sellOrderRequestDTO.getQuantity());
		sellOrderRequestObj.setCheckStatus(sellOrderRequestDTO.getCheck_status());
		sellOrderRequestObj.setUserId(authentication.getName());
		sellOrderRequestObj.setAddressId(sellOrderRequestDTO.getAddressId());
		sellOrderRequestObj.setDileveryPersonId(sellOrderRequestDTO.getDileveryPersonId());
		sellOrderRequest.save(sellOrderRequestObj);
		LOGGER.info("Sell Book Request save to sell_order_request Table ");
		return sellOrderRequestDTO;
		
	}
	
	/**
	 * This method is used to get sell order dellivery request
	 * @return Iterable<Object> this returns book user information
	 */

	@Override
	public Iterable<Object> deliveryRequest(int deliveryPerson_id) {
		LOGGER.info("SellOrderRequestService deliveryRequest method is caling....");
		Iterable<Object>result= sellOrderRequest.deliveryPersonRequest(deliveryPerson_id);
		LOGGER.info("In SellOrderRequestService Delivery Person id="+deliveryPerson_id);
		return result;
	}

	/**
	 * This method is used to update book sell request book status
	 * @param sellOrderRequestDTO this is the parameter of updateBookStatus method
	 */
	@Override
	public void updateBookStatus(SellOrderRequestDTO sellOrderRequestDTO) {
		LOGGER.info("SellOrderRequestService updateBookStatus method is calling....");
		sellOrderRequest.updateBookStatus(sellOrderRequestDTO.getCheck_status(),sellOrderRequestDTO.getFeedBack(),sellOrderRequestDTO.getSellOrderRequestId());
	}
	
	/**
	 * This method is used to get a particular book from sell request
	 * @param bookId this is the parameter of findBookById method
	 * @return Optional<SellOrderRequest> this returns a particular book information
	 */
	@Override
	public Optional<SellOrderRequest> findBookById(int bookId) {
		LOGGER.info("SellOrderRequestService findBookById method is calling....");
		return sellOrderRequest.findById(bookId);
	}
	
	/**
	 * This method is used to get all requested and sold book
	 * @return List<SellOrderRequest> this returns list of book
	 */
	
	@Override
	public List<SellOrderRequest> findSellHistory(){
		LOGGER.info("SellOrderRequestService findSellHistory method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return sellOrderRequest.findSellHistory(authentication.getName());
		
	}
	
	/**
	 * This method is used to get all delivery request to admin
	 * @return Iterable<Object> this returns Iterable<Object>
	 */
	
	@Override
	public Iterable<Object> deliveryRequestAdmin() {
		LOGGER.info("SellOrderRequestService deliveryRequestAdmin method is calling.....");
		Iterable<Object>result= sellOrderRequest.deliveryPersonRequestAdmin();
		return result;
	}
	
}
