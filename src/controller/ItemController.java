package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.core.Item;
import utility.ItemManager;

@EnableWebMvc
@RestController
@RequestMapping("/item")
public class ItemController {
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public Item getItem(@RequestParam("itemId") long itemId ) {
		Item item = new ItemManager().getItem(itemId);	
		return item;
	}
}