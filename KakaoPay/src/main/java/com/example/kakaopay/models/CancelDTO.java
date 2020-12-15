package com.example.kakaopay.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelDTO {
	String cid;
	String cid_secret;
	String tid;
	String cancel_amount;
	String cancel_tax_free_amount;
	String cancel_vat_amount;
}
