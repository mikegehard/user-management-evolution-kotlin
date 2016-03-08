package com.example.payments

class RecurlyGateway : Gateway {
    override fun createReocurringPayment(paymentMonthlyAmount: Int): Boolean {
        return true
    }
}
