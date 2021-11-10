package com.nicolas.nicolsflix.common


class FormatDate {

    companion object {
        fun getDateMovie(dateMovie: String): String {

            val convertData = dateMovie.split("-".toRegex()).toTypedArray()
            val dateBrazil = arrayOfNulls<String>(3)

            for (i in convertData.indices) {
                dateBrazil[0] = convertData[2]
                dateBrazil[1] = convertData[1]
                dateBrazil[2] = convertData[0]
            }

            val days = dateBrazil[0]
            val month = dateBrazil[1]
            val year = dateBrazil[2]

            return "$days/$month/$year"

        }
    }

}