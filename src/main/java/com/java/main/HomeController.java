package com.java.main;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static Robot robot;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		System.out.println("get");
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(Model model, HttpServletRequest request) {
		
		System.out.println("post");
		
		String key = request.getParameter("key");
		System.out.println("get key --> "+key);
		if(key!=null)
			doAction(Integer.parseInt(key));
		return "home";
	}
	private boolean flag = false;
	
	/**
	 * 0 takeoff111111111111111111111111111111111111111111111111ddasd
	 * 1 forward
	 * 2 back
	 * 3 left
	 * 4 right
	 * 5 grab down
	 * 6 grab up
	 * @param num
	 */
	public void doAction(int num) {
		if(robot == null) {
			try {
				robot = new Robot();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(!flag) {
			flag = true;
			switch(num) {
			case 0:
				robot.keyPress(KeyEvent.VK_0);
				robot.keyRelease(KeyEvent.VK_0);
				
				break;
			case 1:
				robot.keyPress(KeyEvent.VK_1);
				robot.keyRelease(KeyEvent.VK_1);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				break;
			case 2:
				robot.keyPress(KeyEvent.VK_2);
				break;
			case 3:
				robot.keyPress(KeyEvent.VK_3);
				break;
			case 4:
				robot.keyPress(KeyEvent.VK_4);
				break;
			case 5:
				robot.keyPress(KeyEvent.VK_5);
				break;
			case 6:
				robot.keyPress(KeyEvent.VK_6);
				break;
			}
		}
		try {
			Thread.sleep(2000);
			flag = false;
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
