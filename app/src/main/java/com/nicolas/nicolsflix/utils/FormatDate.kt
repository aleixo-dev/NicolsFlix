package com.nicolas.nicolsflix.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FormatDate {

    companion object {
        fun getDateMovie(dateMovie: String): String {

            val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val day = formatDate.parse(dateMovie)!!
            return DateFormat.getDateInstance(DateFormat.LONG,Locale.ENGLISH).format(day)

        }
    }

}