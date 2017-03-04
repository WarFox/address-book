package com.gumtree.addressbook.utils;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilsTest {

    @Test
    public void testDaysBetweenTodayAndYesterday() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        assertThat(DateUtils.daysBetween(today, yesterday)).isEqualTo(1);
    }

}