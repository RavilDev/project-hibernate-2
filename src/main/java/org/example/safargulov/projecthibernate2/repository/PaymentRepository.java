package org.example.safargulov.projecthibernate2.repository;

import org.example.safargulov.projecthibernate2.entity.Payment;

public class PaymentRepository extends AbstractRepository<Payment, Integer> {
    public PaymentRepository() {
        super(Payment.class);
    }
}
