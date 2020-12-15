package com.example.kakaopay.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadyDTO {
	String cid;
	String cid_secret;
	String partner_order_id;
	String partner_user_id;
	String item_name;
	int quantity;
	int total_amount;
	int tax_free_amount;
	String approval_url;
	String cancel_url;
	String fail_url;
}
