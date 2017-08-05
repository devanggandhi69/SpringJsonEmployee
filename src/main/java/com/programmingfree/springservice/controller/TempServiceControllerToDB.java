package com.programmingfree.springservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programmingfree.springservice.dao.ToDatabaseService;

@RestController
@RequestMapping("/toDB/")
public class TempServiceControllerToDB {

	ToDatabaseService toDatabaseService = new ToDatabaseService();

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public String toDatabase() {
		toDatabaseService.go();
		return "Data inserted";
	}

}
