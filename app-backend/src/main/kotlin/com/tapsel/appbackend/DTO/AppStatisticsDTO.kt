package com.tapsel.appbackend.DTO

import java.io.Serializable


class AppStatisticsDTO(val weekNum:Int, val year:Int, val requests:Int, val clicks:Int, val installs:Int):Serializable