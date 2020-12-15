package com.example.kakaopay.sevice;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.example.kakaopay.models.CancelDTO;
import com.example.kakaopay.models.Payment;
import com.example.kakaopay.models.PaymentRepository;
import com.example.kakaopay.models.ReadyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoPayService {

	private HttpCaller httpCaller;
	private PaymentRepository paymentRepository;

	@Autowired
	public KakaoPayService(HttpCaller httpCaller, PaymentRepository paymentRepository) {
		this.httpCaller = httpCaller;
		this.paymentRepository = paymentRepository;
	}

	public JSONObject kakaoPayReady(ReadyDTO readyDTO) {

		MultiValueMap<String, String> map = MultiValueMapConverter.convert(readyDTO);

		JSONObject obj = new JSONObject(httpCaller.postRequestXurlencoded(map, API.KAKAOPAY_READY));
		
		paymentRepository.save(Payment.builder()
				.cid(readyDTO.getCid())
				.cid_secret(readyDTO.getCid_secret())
				.partner_order_id(readyDTO.getPartner_order_id())
				.partner_user_id(readyDTO.getPartner_user_id())
				.item_name(readyDTO.getItem_name())
				.quantity(readyDTO.getQuantity())
				.total_amount(readyDTO.getTotal_amount())
				.tax_free_amount(readyDTO.getTax_free_amount())
				.tid(obj.get("tid").toString()).build());
		
		return obj;
	}
	public JSONObject kakaoPayApproval(String pgToken,String tid) {
		
		Payment payment = paymentRepository.findPaymentByTid(tid);
		
		MultiValueMap<String, String> map = MultiValueMapConverter.convert(payment);
		map.set("pg_token", pgToken);
		
		JSONObject obj = new JSONObject(httpCaller.postRequestXurlencoded(map, API.KAKAOPAY_APPROVE));
		
		if(!obj.isNull("sid")) {
			payment.setSid(obj.get("sid").toString());
		}
		
		payment.setAid(obj.get("aid").toString());
		payment.setTid(obj.get("tid").toString());
		payment.setPg_token(pgToken);
		payment.setCreated_at(obj.get("created_at").toString());
		payment.setApproved_at(obj.get("approved_at").toString());
		
		paymentRepository.save(payment);
		
		return obj;
	}
	
	public List<Payment> getPaymentsList() {
		return paymentRepository.findAll();
	}

	public JSONObject kakaoPaySubscription(Payment payment) {
		
		MultiValueMap<String, String> map = MultiValueMapConverter.convert(payment);
		
		JSONObject obj = new JSONObject(httpCaller.postRequestXurlencoded(map, API.KAKAOPAY_SUBSCRIPTION));
		
		if(!obj.isNull("sid")) {
			payment.setAid(obj.get("aid").toString());
			payment.setTid(obj.get("tid").toString());
			payment.setSid(obj.get("sid").toString());
			payment.setCreated_at(obj.get("created_at").toString());
			payment.setApproved_at(obj.get("approved_at").toString());
			paymentRepository.save(payment);
		}

		return obj;
	}

	public JSONObject kakaoPayCancel(CancelDTO cancelDTO) {
		
		Payment payment = paymentRepository.findPaymentByTid(cancelDTO.getTid());
		
		MultiValueMap<String, String> map = MultiValueMapConverter.convert(cancelDTO);
		JSONObject obj = new JSONObject(httpCaller.postRequestXurlencoded(map, API.KAKAOPAY_CANCEL));
		
		if(!obj.isNull("canceled_at")) {
			payment.setCanceled_at(obj.get("canceled_at").toString());
			paymentRepository.save(payment);
		}
		
		return obj;
	}

	public JSONObject kakaoPayOrder(Payment payment) {
		
		MultiValueMap<String, String> map = MultiValueMapConverter.convert(payment);

		JSONObject obj = new JSONObject(httpCaller.postRequestXurlencoded(map, API.KAKAOPAY_ORDER));

		return obj;
	}

}
