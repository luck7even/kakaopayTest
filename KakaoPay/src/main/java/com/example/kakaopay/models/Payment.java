package com.example.kakaopay.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="payment")
@NoArgsConstructor
@Getter
@Setter
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="m_num", insertable = true, updatable = false)
	private String m_num;
	
	@Column(name="cid", insertable = true, updatable = false)
	private String cid;
	
	@Column(name="cid_secret", insertable = true, updatable = false)
	private String cid_secret;

	@Column(name="partner_order_id", insertable = true, updatable = false)
	private String partner_order_id;
	
	@Column(name="partner_user_id", insertable = true, updatable = false)
	private String partner_user_id;
	
	@Column(name="item_name", insertable = true, updatable = false)
	private String item_name;

	@Column(name="quantity", insertable = true, updatable = false)
	private int quantity;
	
	@Column(name="total_amount", insertable = true, updatable = false)
	private int total_amount;
	
	@Column(name="tax_free_amount", insertable = true, updatable = false)
	private int tax_free_amount;

	@Column(name="pg_token", insertable = true, updatable = true)
	private String pg_token;
	
	@Column(name="aid", insertable = true, updatable = true)
	private String aid;
	
	@Column(name="tid", insertable = true, updatable = true)
	private String tid;
	
	@Column(name="sid", insertable = true, updatable = true)
	private String sid;
	
	@Column(name="created_at", insertable = true, updatable = true)
	private String created_at;
	
	@Column(name="approved_at", insertable = true, updatable = true)
	private String approved_at;
	
	@Column(name="canceled_at", insertable = true, updatable = true)
	private String canceled_at;
	
	@Builder
	public Payment(String m_num, String cid, String cid_secret, String partner_order_id, String partner_user_id, String item_name, int quantity, int total_amount, int tax_free_amount,
			String pg_token, String aid, String tid, String sid, String created_at, String approved_at,
			String canceled_at) {
		this.m_num = m_num;
		this.cid = cid;
		this.cid_secret = cid_secret;
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.total_amount = total_amount;
		this.tax_free_amount = tax_free_amount;
		this.pg_token = pg_token;
		this.aid = aid;
		this.tid = tid;
		this.sid = sid;
		this.created_at = created_at;
		this.approved_at = approved_at;
		this.canceled_at = canceled_at;

	}
	
}

