CREATE TABLE IF NOT EXISTS payment (
  id bigint(5) NOT NULL AUTO_INCREMENT, 
  cid varchar(100) NOT NULL, 
  cid_secret varchar(100) NOT NULL, 
  partner_order_id varchar(100) NOT NULL,
  partner_user_id varchar(100) NOT NULL,  
  item_name varchar(100) NOT NULL, 
  quantity int(100) NOT NULL, 
  total_amount int(100) NOT NULL, 
  tax_free_amount int(100) NOT NULL, 
  pg_token varchar(100) NOT NULL, 
  aid varchar(100) NOT NULL, 
  tid varchar(100) NOT NULL, 
  sid varchar(100) NOT NULL, 
  created_at varchar(100) NOT NULL, 
  approved_at varchar(100) NOT NULL, 
  canceled_at varchar(100) NOT NULL, 
  PRIMARY KEY (m_num)
);

INSERT INTO PAYMENT VALUES(1, 'A2839101385986070639','2020-12-11T19:23:25','','TCSUBSCRIP','testMerchant','2020-12-11T19:22:31','초코파이','A202012100158785','luck7even','f6f6e3f5a4c41f056128','1','S2839101394575476591','5000','T2839101162647242605','10000');