package com.oldBookSell.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.PaymentDTO;
import com.oldBookSell.model.Payment;
import com.oldBookSell.repository.PaymentRepository;
import com.oldBookSell.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

/**
 * This is PaymentServiceImpl implements an application that
 * simply calls PaymentService interface methods
 * @author Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
    private PaymentServiceImpl() {
        Stripe.apiKey = "sk_test_OR5dYaeZb1Lp7jVUXZfCDyPF00z2hG0s7j";
    }
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	/**
	 * This method is used to payment
	 * @param token this is the first parameter of chargeCredirCard method
	 * @param amount this is the second parameter of chargeCredirCard method
	 * @return Charge this returns payment information
	 * @throws Exception if something wrong then throw exception
	 */
	
	@Override
	public Charge chargeCreditCard(String token, double amount) throws InvalidRequestException, AuthenticationException, APIConnectionException, CardException, APIException {
		LOGGER.info("PaymentServiceImpl chargeCreditCard method is calling.....");
		Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "inr");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
	
	/**
	 * This method is used to save book payment information  
	 * @param paymentDTO this is the parameter of savePayment method
	 * @return Payment this return payment information
	 */
	
	@Override
	public Payment savePayment(PaymentDTO paymentDTO) {
		LOGGER.info("PaymentServiceImpl savePayment method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Payment payment =new Payment();
		payment.setAmount(paymentDTO.getAmount());
		payment.setCreated(paymentDTO.getCreated());
		payment.setPaymentId(paymentDTO.getPaymentId());
		payment.setStatus(paymentDTO.getStatus());
		payment.setTransactionId(paymentDTO.getTransactionId());
		payment.setUserId(authentication.getName());
		return paymentRepository.save(payment);
	}
	
	/**
	 * This method is used to genrate invoice
	 * @param transatctionId
	 * @return Iterable<Object> this returns invoice
	 */
	
	@Override
	public Iterable<Object> getInvoice(String transatctionId) {
		LOGGER.info("PaymentServiceImpl getInvoice method is calling.....");
		return paymentRepository.getInvoice(transatctionId);
	}

}
