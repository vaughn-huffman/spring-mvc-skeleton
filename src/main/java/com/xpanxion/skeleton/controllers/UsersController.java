package com.xpanxion.skeleton.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xpanxion.skeleton.dto.beans.UserBean;
import com.xpanxion.skeleton.service.UserService;

/**
 * Controller for Users page
 * 
 * @author vhuffman
 * 
 */

@Controller
@RequestMapping("/users")
public class UsersController {
	
	//Logger
	final static Logger logger = Logger.getLogger(Class.class);
	
	//Service for Users activity
	private UserService userService;
	
	
	@ModelAttribute("userbean")
	public UserBean getUserBeanObject() {
		return new UserBean();
	}
	
	/**
	 * Get action for users page
	 * 
	 * @return ModelAndView for the users page
	 * 
	 */
	@RequestMapping(value = "/MV", method = RequestMethod.GET)
	public ModelAndView getUsers() {
        ModelAndView mAndV = new ModelAndView("users");
        List<UserBean> list = this.userService.getUsers();
        Collections.sort(list, new UserComparator());
        mAndV.addObject("users", list);
        
        logger.info("Model and View of Users");
        
        return mAndV;
	}
	
	/**
	 * Get action for users page
	 * 
	 * @return ModelAndView for the users page
	 * 
	 */
	@RequestMapping(value = "/MVWH", method = RequestMethod.GET)
	public ModelAndView getUsersWH(@RequestHeader("HWToken") String token, HttpServletResponse response) {
		
		   if (token != null) {
			   System.out.println("Header:"+token.toString());
		   }
		   response.addHeader("GWToken", "GoodbyeWorld");
		
        ModelAndView mAndV = new ModelAndView("users");
        mAndV.addObject("users", this.userService.getUsers());
        return mAndV;
	}
	
	/**
	 * GET action for new user jsp page
	 * 
	 * @return new user jsp page
	 * 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createUserPage(Model model) {
		model.addAttribute("user",  new UserBean());
        return "new_user";
	}
	
	/**
	 * POST action for new user
	 * 
	 * @return new user
	 * 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String newUser(@ModelAttribute("user") UserBean user, Model model) {
		UserBean userBean =  this.userService.addUser(user);
		model.addAttribute("user", userBean);
		return "redirect:/users/MV"; //"added_user";
	}

	//Update user page
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateUserForm(@PathVariable("id") long userId, @ModelAttribute("user") UserBean user, Model model) {
		UserBean userBean =  this.userService.getUser(user.getId());
		model.addAttribute("user", userBean);
		return "update_user";
	}
	
	//Update user method
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateUser(@PathVariable("id") long userId, @ModelAttribute("user") UserBean user, Model model) {
		UserBean userBean =  this.userService.updateUser(user.getId(), user);
		model.addAttribute("user", userBean);
		return "redirect:/users/MV";  //"updated_user";
	}
	
	//Delete user
	@RequestMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id") long userId, @ModelAttribute("user") UserBean user, Model model) {
		UserBean userBean =  this.userService.deleteUser(userId);
		model.addAttribute("user", userBean);
		return "redirect:/users/MV"; //"deleted_user";
	}
	
	
    /**
     * Sets the service for this controller
     * 
     * @param service the service to use in this controller. 
     */
    @Resource
    public void setUserService(UserService service) {
        this.userService = service;
    }
    
	private class UserComparator implements Comparator<UserBean> {
    	public int compare(UserBean user1, UserBean user2) {
    		return (int) (((com.xpanxion.skeleton.dto.beans.UserBean) user1).getId() - ((com.xpanxion.skeleton.dto.beans.UserBean) user2).getId());
    	}

    }
    
}

