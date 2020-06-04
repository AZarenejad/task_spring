package com.tapsel.appbackend.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate


@Document data class AppStatistic(
        @Id val id:String,
        val reportTime:LocalDate,
        val type:Int,
        val videoRequests:Int,
        val webViewRequests:Int,
        val videoClicks:Int,
        val webViewClicks:Int,
        val videoInstalls:Int,
        val webViewInstalls:Int){
    fun countRequest() : Int = videoRequests + webViewRequests
    fun  countClicks(): Int = videoClicks + webViewClicks
    fun countInstalls():Int = videoInstalls + webViewInstalls
}
