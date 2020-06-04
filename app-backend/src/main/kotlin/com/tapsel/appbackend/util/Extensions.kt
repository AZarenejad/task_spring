package com.tapsel.appbackend.util


import java.time.LocalDate

fun String.toLocalDate() = LocalDate.parse(this, Objects.dateFormat)