package com.oldBookSell.serviceImpl;

import java.util.ArrayList;	
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.OldBookSellDTO;
import com.oldBookSell.model.Address;
import com.oldBookSell.model.UserDetails;
import com.oldBookSell.repository.UserDetailRepository;
import com.oldBookSell.service.OldBookSellServices;

/**
 * This is OldBookSellServiceImpl implements an application that
 * simply calls OldBookSellService interface methods
 * @author Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
*/

@Service
public class OldBookSellServiceImpl implements OldBookSellServices{

	private static final Logger LOGGER=LoggerFactory.getLogger(OldBookSellServiceImpl.class);
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
    private JavaMailSender sender;
	
	/**
	   * This method is used to register user. 
	   * @param oldbookSellDTO This is the paramter to createUser method
	   * @return OldBookSellDTO This returns user details.
	 */

	@Override
	public OldBookSellDTO createUser(OldBookSellDTO odlBookSellDTO) {
		
		LOGGER.info("OldBookSellService createUser method is calling.....");
		
		UserDetails userDetails=new UserDetails();
		
		userDetails.setFirstName(odlBookSellDTO.getFirstName());
		userDetails.setLastName(odlBookSellDTO.getLastName());
		userDetails.setMobileNumber(odlBookSellDTO.getMobileNumber());
		userDetails.setEmail(odlBookSellDTO.getEmail());
		userDetails.setPassword(bcryptEncoder.encode(odlBookSellDTO.getPassword()));
		userDetails.setRole(odlBookSellDTO.getRole());
		
		Address addressObj=new Address();
		
		addressObj.setAddress(odlBookSellDTO.getAddress());
		addressObj.setAddress2(odlBookSellDTO.getAddress2());
		addressObj.setDistrict(odlBookSellDTO.getDistrict());
		addressObj.setLocation(odlBookSellDTO.getLocation());
		addressObj.setPostalCode(odlBookSellDTO.getPinCode());
		addressObj.setState(odlBookSellDTO.getState());
		
		List<Address> list=new ArrayList<>();
		list.add(addressObj);
		
		if(userDetailRepository.existsByEmail(odlBookSellDTO.getEmail())) {
			UserDetails userObj=	userDetailRepository.findByEmail(odlBookSellDTO.getEmail());
			list.addAll(userObj.getAddress());
			userObj.setAddress(list);
			userDetailRepository.save(userObj);
		}else {
			userDetails.setAddress(list);
			userDetailRepository.save(userDetails);
		}
		String msg="Greetings :) Welcome in OldBookHouse :)" + " your UserName is your emial ";
		String result=sendMail(odlBookSellDTO.getEmail(),msg);
		LOGGER.info("In OldBOOKSelService result: "+result);
		LOGGER.info("User information is saved in user_details table");
		
		return odlBookSellDTO;
	}
	
	/**
	   * This method is used to add exgisting user address. 
	   * @param address This is the paramter of addAddress method
	   * @return UserDetails This returns user details.
	*/

	@Override
	public UserDetails addAddress(OldBookSellDTO address) {
		
		LOGGER.info("OldBookSellService addAddress method is calling.....");
		Address addressObj=new Address();
		
		addressObj.setAddress(address.getAddress());
		addressObj.setAddress2(address.getAddress2());
		addressObj.setDistrict(address.getDistrict());
		addressObj.setLocation(address.getLocation());
		addressObj.setPostalCode(address.getPinCode());
		addressObj.setState(address.getState());
		
		List<Address> list=new ArrayList<>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userObj=	userDetailRepository.findByEmail(authentication.getName());

		list.addAll(userObj.getAddress());
		list.add(addressObj);
		userObj.setAddress(list);
		
		userDetailRepository.save(userObj);
		LOGGER.info("Address is addedd in address table");
		return userDetailRepository.findByEmail(authentication.getName());
	}
	
	/**
	 * This method is used to get exgisting user address. 
	 * @return UserDetails This returns user details.
	 */
	
	@Override
	public UserDetails getAddress() {
		
		LOGGER.info("OldBookSellService getAddress method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDetailRepository.findByEmail(authentication.getName());
	}
	
	/**
	 *  This method is used to get List of exgisting user.
	 * @return Iterable<UserDetails> this returns user details
	 */
	
	@Override
	public Iterable<UserDetails> userList(){
		LOGGER.info("OldBookSellService userList method is calling...");
		return userDetailRepository.findAll();
		
	}
	
	/**
	 * This method is used to get a exgisting user details.
	 * @param id This is the paramter of getUserById method
	 * @return UserDetails This returns user details
	 */
	
	@Override
	public Optional<UserDetails> findById(int id) {
		LOGGER.info("OldBookSellService findById method is calling...");
		return userDetailRepository.findById(id);
	}
	
	/**
	 * This method is used to update exgisting user
	 * @param userDetails This is the parameter of updateUser method
	 * @return Optional<UserDetails> This returns user details
	 */

	@Override
	public Optional<UserDetails> updateUser(UserDetails userDetails){
		LOGGER.info("OldBookSellService updateUser method is calling...");
		
		UserDetails user=userDetailRepository.findById(userDetails.getUserId()).get();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setMobileNumber(userDetails.getMobileNumber());
		user.setRole(userDetails.getRole());
		userDetailRepository.save(user);
		return Optional.of(user);
	}
	
	/**
	 * This method is used to delete exgisting user
	 * @param userId This is the parameter of deleteUser method
	 * @return int This returns 0
	 */
	
	@Override
	public int deleteUser(int id){
		LOGGER.info("OldBookSellService deleteUser method is calling...");
		userDetailRepository.deleteById(id);
		return 0;
	}
	
	/**
	 * This method is used to get the role of exgisting user
	 * @return String this returns user role
	 */

	@Override
	public String getRole() {
		LOGGER.info("OldBookSellService getRole method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		return userDetailRepository.hasRole(authentication.getName());
	}

	/**
	 * This method is used to get deliver or pickup book request for a particular delivery person
	 * @return int this returns delivery person id
	 */
	
	@Override
	public int getDeliveryPerson() {
		LOGGER.info("OldBookSellService getDelivery method is calling.....");
		List<Integer> list= userDetailRepository.findAllByRole("deliveryPerson");
		int deliveryPersonId=(int) (Math.random()*list.size());
		LOGGER.info("In OldBookSellService getDeliveryPersion List="+list);
		LOGGER.info("In OldBookSellService getDeliveryPersion i="+deliveryPersonId);
		return list.get(deliveryPersonId);
	}

	/**
	 * This method is used to get delivery person Id
	 * @return int this returns delivery person id
	 */
	
	@Override
	public int getDeliveryPersonId() {
		LOGGER.info("OldBookSellService getDeliveryPersonId method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDetailRepository.getDevileryPersonId(authentication.getName());
	}
	
	/**
	 * This method is used to send mail during registration
	 * @param email this is the first parameter of sendMail method
	 * @param msg this is the second parameter of sendMail method
	 * @return String this return a message mail sucessfully send or not
	 */
	
	@Override
	public String sendMail(String email,String msg) {
		LOGGER.info("OldBookSellService sendMail method is calling.....");
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setText(msg);
            helper.setSubject("OldBookHouse");
        } catch (MessagingException exception) {
            exception.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
		
	}
	
	/**
	   * This method is used to forget password. 
	   * @param userName This is the paramter of changePassword method
	 */
	
	@Override
	public void changePassword(String userName) {
		LOGGER.info("OldBookSellService changePassword method is calling.....");
		Random rand=new Random();
		String password=rand.nextInt(99999)+"";
		boolean result=userDetailRepository.existsByEmail(userName);
		if(result) {
			LOGGER.info(" In OldBookSellService sendMail method password is "+password);
			String msg="Greetings :) your password changed sucessfully 😄 "
					+ " your Password is "+password;
			sendMail(userName, msg);
			
			UserDetails userObj=	userDetailRepository.findByEmail(userName);
			userObj.setPassword(bcryptEncoder.encode(password));
			userDetailRepository.save(userObj);
		}
	}

}
