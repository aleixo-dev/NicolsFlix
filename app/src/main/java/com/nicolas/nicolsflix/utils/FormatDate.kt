package com.nicolas.nicolsflix.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FormatDate {

    companion object {
        fun getDateMovie(dateMovie: String): String {
            val locale = Locale("pt", "BR")

            val formatDate = SimpleDateFormat("dd-MM-yyyy", locale)
            val day = formatDate.parse(dateMovie)!!
            return DateFormat.getDateInstance(DateFormat.LONG, locale).format(day)
        }
    }

}