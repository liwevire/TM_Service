package controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.core.MiscTransaction;
import utility.MiscTransactionManager;

@EnableWebMvc
@RestController
@RequestMapping("/miscTransaction")
public class MiscTransactionController {
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public List<MiscTransaction> calculateDailyReport(@RequestParam("date") Date date) {
		System.out.println(date.toString());
		return new MiscTransactionManager().getMiscTransaction(date);
	}
}