package com.oldBookSell.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.BuyOrderRequest;

@Repository
@Transactional
public interface BuyOrderRequestRepository extends JpaRepository<BuyOrderRequest,Integer>{

	@Query(value="select count(buy_order_request_id) from buy_order_request where user_id=?1 and check_status=?2",nativeQuery = true)
	int countOrderRequest(String name,String status);

	@Query(value="select * from buy_order_request where user_id=?1 and check_status=?2",nativeQuery = true)
	List<BuyOrderRequest> getOrderRequest(String name, String string);
	
	@Modifying
	@Query(value="update buy_order_request set check_status=?1, address_id=?2, dilevery_person_id=?3 , status=?4,transaction_id=?5 where user_id=?6 and check_status=?7",nativeQuery = true)
	void addDeliverAddress(String checkStatus, int addressId, int deliveryPersonId, String status, String transactionId, String serName, String checkStatus2);

	@Query(value="select buy_order_request_id,book_name,authors,amount,check_status,small_thumbnail,address, address2,district, postal_code,state,first_name,last_name,mobile_number from buy_order_request b, address a,user_details u where b.address_id = a.id and a.user_id = u.user_id and b.dilevery_person_id=?1 ORDER BY check_status ASC",nativeQuery = true)
	Iterable<Object> deliveryPersonRequest(int deliveryId);
	
	@Modifying
	@Query(value="update buy_order_request set check_status=?2 where buy_order_request_id=?1",nativeQuery = true)
	void updateBuyBookStatus(int buyOrderRequestId, String check_status);
	
	@Query(value="select * from buy_order_request where dilevery_person_id!=0 and user_id=?1",nativeQuery=true)
	List<BuyOrderRequest> findBuyHistory(String buyUserId);
	
	@Query(value="select buy_order_request_id,book_name,authors,amount,check_status,small_thumbnail,address, address2,district, postal_code,state,first_name,last_name,mobile_number from buy_order_request b, address a,user_details u where b.address_id = a.id and a.user_id = u.user_id ORDER BY check_status DESC",nativeQuery = true)
	Iterable<Object> deliveryGetAdmin();
	
	@Query(value="select * from buy_order_request where user_id=?1 and book_id=?2 and check_status=?3",nativeQuery=true)
	BuyOrderRequest checkBook(String userName, int bookId, String checkStatus);
	
	@Query(value="select * from buy_order_request where user_id=?1 and check_status=?2",nativeQuery = true)
	List<BuyOrderRequest> getBuyRequest(String userName, String checkStatus);
}
