package com.oldBookSell.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.SellOrderRequest;

@Repository
@Transactional
public interface SellOrderRequestRepository extends JpaRepository<SellOrderRequest, Integer>{

	@Query(value="select * from sell_order_request where book_name=?1 or authors=?2",nativeQuery = true)
	SellOrderRequest findByBookNameOrAuthor(String BookName,String authors);
	
	@Query(value="select sell_order_request_id,book_name,authors,check_status,isbn_no1,isbn_no2,publisher, small_thumbnail,address, address2,district, postal_code,state,first_name,last_name,mobile_number from sell_order_request s, address a,user_details u where s.address_id = a.id and a.user_id = u.user_id and s.dilevery_person_id=?1 ORDER BY check_status DESC",nativeQuery = true)
	Iterable<Object> deliveryPersonRequest(int i);
	
	@Modifying
	@Query(value="update sell_order_request set check_status=?1,feedback_by_delivery_person=?2 where sell_order_request_id=?3",nativeQuery = true)
	void updateBookStatus(String check_status, String feedBack, int sellOrderRequestId);
	
	@Query(value="select * from sell_order_request where dilevery_person_id!=0 and  user_id=?1",nativeQuery = true)
	List<SellOrderRequest> findSellHistory(String userName);
		
	@Query(value="select sell_order_request_id,book_name,authors,check_status,isbn_no1,isbn_no2,publisher, small_thumbnail,address, address2,district, postal_code,state,first_name,last_name,mobile_number from sell_order_request s, address a,user_details u where s.address_id = a.id and a.user_id = u.user_id ORDER BY check_status DESC",nativeQuery = true)
	Iterable<Object> deliveryPersonRequestAdmin();
	
}

