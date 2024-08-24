package com.dailycodebuffer.paymentservice.controller;
import com.dailycodebuffer.paymentservice.model.PaymentRequest;
import com.dailycodebuffer.paymentservice.model.PaymentResponce;
import com.dailycodebuffer.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<Long> dopayment(@RequestBody PaymentRequest paymentRequest) {
        long paymentId = paymentService.dopayment(paymentRequest);
        return new ResponseEntity<>(paymentId, HttpStatus.OK);
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponce> getPaymentDetailsByOrderId(@PathVariable long orderId) {
        return  new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId), HttpStatus.OK
        );


    }

}
