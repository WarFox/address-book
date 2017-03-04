package com.gumtree.addressbook.utils;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilsTest {

    @Test
    public void testDaysBetweenTodayAndYesterday() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        assertThat(DateUtils.daysBetween(yesterday, today)).isEqualTo(1);
    }

    @Test
    public void testDaysBetween1stAnd3rdIs2() throws Exception {
        LocalDate date1 = LocalDate.of(2016, 1, 1);
        LocalDate date2 = LocalDate.of(2016, 1, 3);
        assertThat(DateUtils.daysBetween(date1, date2)).isEqualTo(2);
    }

    @Test
    public void testDaysBetweenSameDateShouldbeZero() throws Exception {
        LocalDate today = LocalDate.now();
        assertThat(DateUtils.daysBetween(today, today)).isEqualTo(0);
    }

    @Test
    public void testWhenFirstDateIsGreaterThanSecondDate() throws Exception {
        LocalDate date1 = LocalDate.of(2017, 1, 1);
        LocalDate date2 = LocalDate.of(2016, 1, 1);
        assertThat(DateUtils.daysBetween(date1, date2)).isEqualTo(-366);
    }

}
