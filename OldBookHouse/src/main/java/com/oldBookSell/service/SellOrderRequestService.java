package com.oldBookSell.service;

import java.util.List;
import java.util.Optional;

import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.SellOrderRequest;

public interface SellOrderRequestService {
	
	public SellOrderRequestDTO bookRequest(SellOrderRequestDTO sellOrderRequestDTO);
	
	public List<SellOrderRequest> findSellHistory();
	
	public Iterable<Object> deliveryRequest(int deliveryPerson_id);
	
	public void updateBookStatus(SellOrderRequestDTO sellOrderRequestDTO);

	public Iterable<Object> deliveryRequestAdmin();
	
	public Optional<SellOrderRequest> findBookById(int bookId);

}
