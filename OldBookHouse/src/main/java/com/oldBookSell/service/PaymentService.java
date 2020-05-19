package com.oldBookSell.service;
import com.oldBookSell.dto.PaymentDTO;
import com.oldBookSell.model.Payment;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

public interface PaymentService {
	
	public Payment savePayment(PaymentDTO payment);
	
	public Charge chargeCreditCard(String token, double amount) throws InvalidRequestException, AuthenticationException, APIConnectionException, CardException, APIException;
	
	public Iterable<Object> getInvoice(String transatctionId);
}
	