package com.tapsel.appbackend.util





import java.time.format.DateTimeFormatter
/*Singleton*/
object Objects {
    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}