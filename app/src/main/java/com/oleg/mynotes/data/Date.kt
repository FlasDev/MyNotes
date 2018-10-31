package com.oleg.mynotes.data

import java.time.Year
import java.util.*
import java.util.Calendar.*

data class Date(
        var year: Int? = 1998,
        var month: Int? = getInstance()[MONTH],
        var dayOfMonth: Int? = getInstance()[DAY_OF_MONTH],
        var hour: Int? = getInstance()[HOUR_OF_DAY],
        var minute: Int? = getInstance()[MINUTE]
){
    override fun toString(): String {
        return "$year,$month,$dayOfMonth,$hour,$minute"
    }
}
