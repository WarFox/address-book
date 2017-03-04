package com.gumtree.addressbook.models;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GenderTest {

    @Test
    public void genderIsEnum() {
        assertThat(Gender.Female).isInstanceOf(Enum.class);
    }

    @Test
    public void genderHasTwoElements() {
        assertThat(Gender.values()).hasSize(2);
    }

    @Test
    public void genderCanBeMale() {
        assertThat(Gender.valueOf("Male")).isEqualTo(Gender.Male);
    }

    @Test
    public void genderCanBeFemale() {
        assertThat(Gender.valueOf("Female")).isEqualTo(Gender.Female);
    }

}