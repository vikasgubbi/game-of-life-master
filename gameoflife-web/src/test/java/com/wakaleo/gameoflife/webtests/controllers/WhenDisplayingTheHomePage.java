package com.wakaleo.gameoflife.webtests.controllers;

import com.wakaleo.gameoflife.webtests.beans.HomeBean;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class WhenDisplayingTheHomePage {

    @Test
    public void theHomeUrlShouldDisplayTheHomePage() {
        HomeBean bean = new HomeBean();
        String homeView = bean.home();
        assertThat(homeView, is("home"));
    }

    @Test
    public void theIndexUrlShouldDisplayTheHomePage() {
        HomeBean bean = new HomeBean();
        String homeView = bean.index();
        assertThat(homeView, is("home"));
    }
}	

