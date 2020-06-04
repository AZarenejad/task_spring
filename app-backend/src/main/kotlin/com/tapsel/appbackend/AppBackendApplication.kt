package com.tapsel.appbackend

import com.tapsel.appbackend.models.AppStatistic
import com.tapsel.appbackend.services.AppStatisticsDAO
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableAsync
import java.time.LocalDate


@EnableCaching
@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
class AppBackendApplication(private val appStatisticsDAO: AppStatisticsDAO): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        if(appStatisticsDAO.count()<1) this.createAppStatistics()
    }


    private fun createAppStatistics(){
        this.cleanCollections()
        val appStatistics = listOf(
                AppStatistic(id="4", reportTime = LocalDate.parse("1398-10-25"), type = 2,
                        videoRequests=4,webViewRequests=4,
                        videoClicks=4,webViewClicks=4,
                        videoInstalls=4, webViewInstalls=4),
                AppStatistic(id="1", reportTime = LocalDate.parse("1396-02-01"), type = 1,
                        videoRequests=1,webViewRequests=1,
                        videoClicks=1,webViewClicks=1,
                        videoInstalls=1, webViewInstalls=1 ),
                AppStatistic(id="5", reportTime = LocalDate.parse("1397-05-31"), type = 2,
                        videoRequests=5,webViewRequests=5,
                        videoClicks=5,webViewClicks=5,
                        videoInstalls=5, webViewInstalls=5),
                AppStatistic(id="2", reportTime = LocalDate.parse("1396-01-29"), type = 2,
                        videoRequests=2,webViewRequests=2,
                        videoClicks=2,webViewClicks=2,
                        videoInstalls=2, webViewInstalls=2),
                AppStatistic(id="3", reportTime = LocalDate.parse("1397-04-20"), type = 2,
                        videoRequests=3,webViewRequests=3,
                        videoClicks=3,webViewClicks=3,
                        videoInstalls=3, webViewInstalls=3),

                AppStatistic(id="6", reportTime = LocalDate.parse("1397-01-12"), type = 3,
                        videoRequests=5,webViewRequests=5,
                        videoClicks=5,webViewClicks=5,
                        videoInstalls=5, webViewInstalls=5),
                AppStatistic(id="7", reportTime = LocalDate.parse("1397-10-12"), type = 3,
                        videoRequests=1,webViewRequests=1,
                        videoClicks=2,webViewClicks=2,
                        videoInstalls=2, webViewInstalls=2)

        )
        appStatisticsDAO.insert(appStatistics)
    }

    private fun cleanCollections(){
        appStatisticsDAO.deleteAll()
    }
}



fun main(args: Array<String>) {
    runApplication<AppBackendApplication>(*args)
}