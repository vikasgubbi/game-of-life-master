/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wakaleo.gameoflife.webtests.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
* This class rocks.
*
* @author johnsmart
*/
@Named
@RequestScoped
public class HomeBean {
    public String index() {
        return "home";
    }
    
    public String home() {
        return "home";
    }
}
