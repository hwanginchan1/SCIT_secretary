package net.softsociety.secretary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("main")
public class MainController {

	/**
	 * 메인화면!
	 */
	@GetMapping({"", "/"})
	public String main() {
		return "mainView/index";
	}

}
