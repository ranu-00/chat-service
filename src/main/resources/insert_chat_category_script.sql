INSERT INTO `chatmessage`.`pay_mob_chat_category` (`id`, `categoryDiscription`, `categoryName`, `createdBy`) VALUES ('1', 'Do you want to send money?', 'send_money', '1');
INSERT INTO `chatmessage`.`pay_mob_chat_category` (`id`, `categoryDiscription`, `categoryName`, `createdBy`) VALUES ('2', 'Do you want to create or select bucketList?', 'bucket_list', '1');
INSERT INTO `chatmessage`.`pay_mob_chat_category` (`id`, `categoryDiscription`, `categoryName`, `createdBy`) VALUES ('3', 'For loan and Offer', 'loan_offer', '1');
update chatmessage.pay_mob_chat_category set categoryId = '1' where id ='1';
update chatmessage.pay_mob_chat_category set categoryId = '2' where id ='2';
update chatmessage.pay_mob_chat_category set categoryId = '3' where id ='3';