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
@RequestMapping("app/statistics")//controller root path
class AppStatisticsController(private val appStatisticsService: AppStatisticsService){//injects AppStatisticsService by constructor


    @GetMapping("/all") fun getAll(pageable: Pageable): Page<AppStatistic> = appStatisticsService.getAll(pageable)
    @GetMapping("{id}")  fun getById(@PathVariable id:String): Optional<AppStatistic> = appStatisticsService.getById(id)
    @DeleteMapping("{id}")  fun deleteById(@PathVariable id: String): Optional<AppStatistic>  = appStatisticsService.deleteById(id)


    @GetMapping fun getStats():List<AppStatisticsDTO> = appStatisticsService.getStats(LocalDate.parse("1395-01-01"),
            LocalDate.parse("1399-01-01"), 2)

}

