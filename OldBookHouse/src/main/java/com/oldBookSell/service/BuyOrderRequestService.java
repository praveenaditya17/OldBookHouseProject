package com.oldBookSell.service;

import java.util.List;

import com.oldBookSell.dto.BuyOrderRequestDTO;
import com.oldBookSell.model.BuyOrderRequest;

public interface BuyOrderRequestService {
	
	public int saveRequest(BuyOrderRequestDTO bookDetail);

	public int getNotification();

	public List<BuyOrderRequest> getOrderRequest();
	
	public void deleteBookRequest(int requestBookId);
	
	public List<BuyOrderRequest> addDeliverAddress(int addressId, int deliveryPersonId, String status, String transactionId);
	
	public Iterable<Object> deliverySellRequest(int deliveryId);
	
	public void updateBuyBookStatus(int buyOrderRequestId, String check_status);
	
	public Iterable<Object> deliverySellRequestAdmin();

	public BuyOrderRequest addQuantity(int requestBookId);

	public BuyOrderRequest minusQuantity(int requestBookId);
	
	public List<BuyOrderRequest> findBuyHistory();
	
	public int getQuantity(int bookId);

}
