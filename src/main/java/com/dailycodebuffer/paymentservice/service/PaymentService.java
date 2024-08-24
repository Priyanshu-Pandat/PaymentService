package com.dailycodebuffer.paymentservice.service;

import com.dailycodebuffer.paymentservice.model.PaymentRequest;
import com.dailycodebuffer.paymentservice.model.PaymentResponce;

public interface PaymentService {
    long dopayment(PaymentRequest paymentRequest);

    PaymentResponce getPaymentDetailsByOrderId(long orderId);
}
