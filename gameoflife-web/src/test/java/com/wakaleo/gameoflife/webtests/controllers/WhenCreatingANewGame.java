package com.wakaleo.gameoflife.webtests.controllers;

import com.wakaleo.gameoflife.webtests.beans.GameBean;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;


public class WhenCreatingANewGame {

    GameBean bean = null;

    @Before
    public void initializeController() {
        bean = new GameBean();
    }

    @Test
    public void anEmptyUniverseShouldBeAddedToTheSession() {
        bean.newGame();
        assertThat(bean.getUniverse(), is(not(nullValue())));
    }

    @Test
    public void whenTheUserCreatesTheFirstGenerationAnEmptyUniverseShouldBeAddedToTheSession() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        bean.firstGeneration(5,5,request);
        assertThat(bean.getUniverse(), is(not(nullValue())));
    }

    @Test
    public void whenTheUserCreatesTheFirstGenerationTheUniverseDimensionsShouldBeAddedToTheSession() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        bean.firstGeneration(3, 5, request);
        assertThat(bean.getRows(), is(3));
        assertThat(bean.getColumns(), is(5));

    }
}	

