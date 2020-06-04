package com.innovate.paymob.paymobchatservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovate.paymob.chatmodel.ChatMessageModel;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessageModel, Long>{
  public ChatMessageModel findBymessageId(Long messageId);
}
