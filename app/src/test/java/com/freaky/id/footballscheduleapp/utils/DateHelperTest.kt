package com.freaky.id.footballscheduleapp.utils

import com.freaky.id.footballscheduleapp.utils.DateHelper.formatDateToMatch
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class DateHelperTest {

    @Test
    fun testFormatDateToMatch() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", formatDateToMatch(date))
    }
}