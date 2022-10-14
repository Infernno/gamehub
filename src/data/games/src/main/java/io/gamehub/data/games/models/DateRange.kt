package io.gamehub.data.games.models

import java.time.LocalDate

data class DateRange(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    private val stepDays: Long = 1
) : Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> = DateIterator(start, endInclusive, stepDays)

    override fun toString(): String {
        return if (start == endInclusive)
            start.toString()
        else
            "$start,$endInclusive"
    }

    fun startDateAsTimeStamp(): Long {
        return start.toEpochDay()
    }

    fun endDateAsTimeStamp(): Long {
        return endInclusive.toEpochDay()
    }

    internal class DateIterator(
        startDate: LocalDate,
        private val endDate: LocalDate,
        private val stepDays: Long
    ) : Iterator<LocalDate> {

        private var currentDate = startDate

        override fun hasNext() = currentDate.isBefore(endDate)

        override fun next(): LocalDate {
            val newDate = currentDate.plusDays(stepDays)
            currentDate = newDate

            return newDate
        }
    }

    companion object {
        val EMPTY = DateRange(LocalDate.MIN, LocalDate.MIN)

        fun single(date: LocalDate): DateRange {
            return DateRange(date, date)
        }
    }
}
