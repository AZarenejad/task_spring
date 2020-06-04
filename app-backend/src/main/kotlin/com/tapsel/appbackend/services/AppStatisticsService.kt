package com.tapsel.appbackend.services

import com.tapsel.appbackend.DTO.AppStatisticsDTO
import com.tapsel.appbackend.models.AppStatistic
import com.tapsel.appbackend.util.BasicCrud
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*
import kotlin.collections.ArrayList



@Service//declare this class as a Service "Component specialization"
/*injects DAO objects by constructor & implements BasicCrud interface*/
class AppStatisticsService(val appStatisticsDAO: AppStatisticsDAO):BasicCrud<String,AppStatistic>{

    override fun getAll(pageable: Pageable): Page<AppStatistic> = appStatisticsDAO.findAll(pageable)

    override fun getById(id: String): Optional<AppStatistic> =  appStatisticsDAO.findById(id)

    override fun insert(obj: AppStatistic): AppStatistic = appStatisticsDAO.insert(obj)

    override fun deleteById(id: String): Optional<AppStatistic> {
        return appStatisticsDAO.findById(id).apply {
            this.ifPresent {
                appStatisticsDAO.delete(it)
            }
        }
    }

    @Cacheable(value = ["appsStatistics"])
    fun getStats(startDate: LocalDate, endDate:LocalDate, type:Int):List<AppStatisticsDTO> {
        print("heloo")
       val apps = appStatisticsDAO.findByTypeAndReportTimeBetween(type, startDate, endDate)
        var appsDTO : ArrayList<AppStatisticsDTO> = ArrayList()
        for(app in apps){
            val weekNum = app.reportTime.get(WeekFields.of(DayOfWeek.SATURDAY, 1).weekOfWeekBasedYear())
            appsDTO.add(AppStatisticsDTO(weekNum,app.reportTime.year,app.countRequest(),
            app.countClicks(), app.countInstalls()))
        }
        print("hello") // to check cache with redis working fine!
        return appsDTO.sortedWith(compareBy(AppStatisticsDTO::year, AppStatisticsDTO::weekNum))
    }



}


