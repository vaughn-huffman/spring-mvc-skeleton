package com.xpanxion.skeleton.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xpanxion.skeleton.service.TestService;

/**
 * Controller for the Home Page.
 * 
 * @author brian
 * 
 */
@Controller
public class HomeController {

    private TestService testService;
    
    
    @RequestMapping(value="/", method = RequestMethod.GET)
	public String mainPage(ModelMap model) {
	return "main_page";
    }

    /**
     * The default controller action for the homepage. 
     * 
     * @return the Model and View for the home page. 
     */
    @RequestMapping("**/home")
    public ModelAndView getHomePage() {
        ModelAndView mAndV = new ModelAndView("home");
        mAndV.addObject("test", this.testService.getTestBeans());
        return mAndV;
    }

    /**
     * Sets the service for this controller
     * 
     * @param service the service to use in this controller. 
     */
    @Resource
    public void setTestService(TestService service) {
        this.testService = service;
    }

}
