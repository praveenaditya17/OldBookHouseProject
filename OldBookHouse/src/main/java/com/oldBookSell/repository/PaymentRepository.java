package com.oldBookSell.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.Payment;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	@Query(value="select u.first_name,u.last_name,u.mobile_number,a.address,a.address2,a.district,a.postal_code,a.state,b.buy_order_request_id,b.book_name,b.quantity,b.amount,b.date,p.created from buy_order_request b INNER JOIN payment p ON b.transaction_id= p.transaction_id INNER JOIN user_details u ON u.email=p.user_id INNER JOIN address a ON a.id=b.address_id where p.transaction_id=?1",nativeQuery = true)
	Iterable<Object> getInvoice(String transatctionId);

}
