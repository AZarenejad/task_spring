package com.tapsel.appbackend.services

import com.tapsel.appbackend.models.AppStatistic
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDate


/*Spring does the implementation for us*/
interface AppStatisticsDAO:MongoRepository<AppStatistic,String> {
    fun findByTypeAndReportTimeBetween(type: Int,startDate:LocalDate, endDate:LocalDate): List<AppStatistic>
}