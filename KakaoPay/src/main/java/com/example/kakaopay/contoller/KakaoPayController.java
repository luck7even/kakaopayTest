package com.example.kakaopay.contoller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kakaopay.models.CancelDTO;
import com.example.kakaopay.models.Payment;
import com.example.kakaopay.models.ReadyDTO;
import com.example.kakaopay.sevice.KakaoPayService;

@Controller
public class KakaoPayController {

	private KakaoPayService paymentsService;

	@Autowired
	public KakaoPayController(KakaoPayService paymentsService) {
		this.paymentsService = paymentsService;
	}

	@PostMapping(value = "/ready")
	@ResponseBody
	public String kakaoPayReady(ReadyDTO readyDTO, HttpSession session) {
		JSONObject obj = paymentsService.kakaoPayReady(readyDTO);
		session.setAttribute("tid", obj.get("tid").toString());
		
		return obj.toString();
	}
	
	//https://localhost/approval?pg_token=ad950e97794abfef4ce0
	@GetMapping(value = "/approval")
	public void kakaoPayApproval(@RequestParam(value = "pg_token", required = true) String pgToken, HttpSession session, HttpServletResponse response) throws IOException {
		JSONObject obj = paymentsService.kakaoPayApproval(pgToken,session.getAttribute("tid").toString());
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('결제가 완료되었습니다.');");
        out.println("opener.complete(" + obj + ")");
        out.println("close();</script>;");
        out.flush();
	}
	
	@PostMapping(value = "/subscription")
	@ResponseBody
	public String kakaoPaySubscription(Payment payment) {
		JSONObject obj = paymentsService.kakaoPaySubscription(payment);
		return obj.toString();
	}
	
	@PostMapping(value = "/cancel")
	@ResponseBody
	public String kakaoPayCancel(CancelDTO cancelDTO) {
		JSONObject obj = paymentsService.kakaoPayCancel(cancelDTO);
		return obj.toString();
	}
	
	@PostMapping(value = "/order")
	@ResponseBody
	public String kakaoPayOrder(Payment payment) {
		JSONObject obj = paymentsService.kakaoPayOrder(payment);
		return obj.toString();
	}
	
	@GetMapping(value = "/readycancel")
	public void kakaoPayReadyCancel(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('결제를 취소하였습니다.'); close(); </script>");
        out.flush();
	}
	
	@GetMapping(value = "/readyfail")
	public void kakaoPayReadyFail(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('결제가 실패하였습니다.'); close(); </script>");
        out.flush();
	}
	
	@GetMapping(value = "/payments")
	public @ResponseBody Iterable<Payment> getPaymentsList() {
		return paymentsService.getPaymentsList();
	}
	

}