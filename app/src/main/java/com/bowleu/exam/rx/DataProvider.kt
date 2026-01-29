package com.bowleu.exam.rx

import com.bowleu.exam.rx.models.DiscountCard
import io.reactivex.rxjava3.core.Observable

object DataProvider {
    fun getServer1(): Observable<List<DiscountCard>> {
        return Observable.fromCallable {
            if (Math.random() < 0.3) throw RuntimeException("Server #1 failed")
            listOf(
                DiscountCard(1, "Card1"),
                DiscountCard(2, "Card2")
            )
        }
    }

    fun getServer2(): Observable<List<DiscountCard>> {
        return Observable.fromCallable {
            if (Math.random() < 0.3) throw RuntimeException("Server #2 failed")
            listOf(
                DiscountCard(3, "Card3"),
                DiscountCard(4, "Card4")
            )
        }
    }
}