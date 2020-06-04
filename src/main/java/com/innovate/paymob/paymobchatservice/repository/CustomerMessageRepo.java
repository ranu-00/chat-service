package com.innovate.paymob.paymobchatservice.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.innovate.paymob.chatmodel.CustomerMessage;
@Repository
public interface CustomerMessageRepo extends JpaRepository<CustomerMessage, Long>{
	
	@Query(value = "SELECT c FROM CustomerMessage c where c.messageCategoryId = :messageCategoryId and c.mobileNumber =:mobileNumber")   
	
	public CustomerMessage findByCatIdAndMobileNumber(@Param("messageCategoryId") Long messageCategoryId,@Param("mobileNumber") String mobileNumber);

	@Modifying
	@Query("update CustomerMessage u set u.chatMessageId = :chatMessageId where u.messageCategoryId = :messageCategoryId and u.mobileNumber=:mobileNumber")
	
	public void updateCustomerMessage(@Param("chatMessageId")Long chatMessageId,@Param("messageCategoryId") Long messageCategoryId, @Param("mobileNumber")String mobileNumber);

	public CustomerMessage findBymobileNumber(String mobileNumber);
	
	
	@Modifying
	@Query("update CustomerMessage u set u.senderMobileNumber = :senderMobileNumber where u.chatMessageId = :chatMessageId")
	
	public void updateSenderMobileNumber(@Param("chatMessageId")Long chatMessageId,@Param("senderMobileNumber") String senderMobileNumber);

	@Modifying
	@Query("update CustomerMessage u set u.senderName = :senderName where u.chatMessageId = :chatMessageId")
	
	public void updateSenderNameNumber(@Param("chatMessageId")Long chatMessageId,@Param("senderName") String senderName);

	@Modifying
	@Query("update CustomerMessage u set u.amount = :amount where u.chatMessageId = :chatMessageId")
	
	public void updateSenderamount(@Param("chatMessageId")Long chatMessageId,@Param("amount") String amount);

}
