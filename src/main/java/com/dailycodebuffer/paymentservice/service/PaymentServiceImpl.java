package com.dailycodebuffer.paymentservice.service;

import com.dailycodebuffer.paymentservice.entity.TransactionDetails;
import com.dailycodebuffer.paymentservice.model.PaymentMode;
import com.dailycodebuffer.paymentservice.model.PaymentRequest;
import com.dailycodebuffer.paymentservice.model.PaymentResponce;
import com.dailycodebuffer.paymentservice.repossitory.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;


    @Override
    public long dopayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details:{}", paymentRequest);
        // Record the Payment Details in the DB
       // String mode = String.valueOf(paymentRequest.getPaymentMode().toString());
        TransactionDetails transactionDetails =
                TransactionDetails.builder()
                        .paymentDate(Instant.now())
                        .paymentMode(paymentRequest.getPaymentMode().toString())
                        .amount(paymentRequest.getAmount())
                        .orderId(paymentRequest.getOrderId())
                        .paymentStatus("SUCCESS")
                        .referenceNumber(paymentRequest.getReferenceNumber())
                       .build();
        log.info("the paymentMode is:{} " , transactionDetails.getPaymentMode());

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction Completed with Id:{}", transactionDetails.getTransactionId());

        return transactionDetails.getTransactionId();
    }

    @Override
    public PaymentResponce getPaymentDetailsByOrderId(long orderId) {
        log.info("get PaymentDetails By Order Id:{}", orderId);
        TransactionDetails transactionDetails =
                transactionDetailsRepository.findByOrderId(orderId);

        PaymentResponce paymentResponce =
                PaymentResponce.builder()
                        .paymentId(transactionDetails.getTransactionId())
                        .orderId(transactionDetails.getOrderId())
                        .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                        .amount(transactionDetails.getAmount())
                        .paymentDate(transactionDetails.getPaymentDate())
                        .status(transactionDetails.getPaymentStatus())
                        .build();
        return paymentResponce;

    }
}
