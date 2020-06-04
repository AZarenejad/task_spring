package com.tapsel.appbackend.controllers

import com.tapsel.appbackend.DTO.AppStatisticsDTO
import com.tapsel.appbackend.models.AppStatistic
import com.tapsel.appbackend.services.AppStatisticsService
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@RestController//declare this class as rest controller able to catch http request
@RequestMapping("/app_statistics")//controller root path
class AppStatisticsController(private val appStatisticsService: AppStatisticsService){//injects AppStatisticsService by constructor


    @GetMapping("/all") fun getAll(pageable: Pageable): Page<AppStatistic> = appStatisticsService.getAll(pageable)
    @GetMapping("{id}")  fun getById(@PathVariable id:String): Optional<AppStatistic> = appStatisticsService.getById(id)
    @DeleteMapping("{id}")  fun deleteById(@PathVariable id: String): Optional<AppStatistic>  = appStatisticsService.deleteById(id)


    @GetMapping("/report")
    fun getStats(@RequestBody request: Request):List<AppStatisticsDTO>
            = appStatisticsService.getStats(LocalDate.parse(request.startDate),
            LocalDate.parse(request.endDate), request.type)

}


data class Request(val type: Int, val startDate: String, val endDate: String)

