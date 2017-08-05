package com.programmingfree.springservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.programmingfree.springservice.dao.TempService;
import com.programmingfree.springservice.domain.Temp;

@RestController
@RequestMapping("/temp/items.json")
public class TempServiceController {

	TempService tempService = new TempService();

	
	@RequestMapping(value = "/{search}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Temp> getMenus(@PathVariable String search) {
		List<Temp> menus = tempService.getMenuByCategory(search);
		return menus;
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Temp> getAllMenus() {
		List<Temp> menus = tempService.getAllMenu();
		return menus;
	}

}
