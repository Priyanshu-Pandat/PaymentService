package com.dailycodebuffer.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponce {
    private long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private long orderId;
    private Instant paymentDate;
    private long amount;
}
