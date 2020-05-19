package com.oldBookSell.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.UserDetails;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails,Integer>{
			
	UserDetails findByEmail(String name);
			
	boolean	existsByEmail(String name);
	
	@Query(value="select role from user_details where email=?1",nativeQuery = true)
	String hasRole(String email);
	
	@Query(value="select user_id from user_details where role=?1",nativeQuery = true)
	List<Integer> findAllByRole(String role);
	
	@Query(value="select user_id from user_details where email=?1",nativeQuery = true)
	int getDevileryPersonId(String userName);
	
}
