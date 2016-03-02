package com.wakaleo.gameoflife.webtests.controllers;

import com.wakaleo.gameoflife.webtests.beans.GameBean;
import com.wakaleo.gameoflife.webtests.beans.HomeBean;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

public class WhenSpawningANewGeneration {

    @Test
    public void whenGeneratingTheNextGenerationAnUpdatedUniverseShouldBeProduced() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        request.setAttribute("rows", "3");
        request.setAttribute("columns", "3");
        GameBean bean = new GameBean();
        bean.nextGeneration(3, 3, request);
        assertThat(bean.getUniverse(), is(not(nullValue())));
    }

    @Test
    public void theIndexUrlShouldDisplayTheHomePage() {
        HomeBean bean = new HomeBean();
        String homeView = bean.index();
        assertThat(homeView, is("home"));
    }
}
