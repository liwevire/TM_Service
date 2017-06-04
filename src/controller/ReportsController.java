package controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.DailyReport;
import utility.DailyReportsManager;

@EnableWebMvc
@RestController
@RequestMapping("/reports")
public class ReportsController {
	@RequestMapping(value="/calculateDaily", method=RequestMethod.GET)
	public DailyReport calculateDailyReport(@RequestParam("calculationDate") Date date) {
		return new DailyReportsManager().calculateDailyReport(date);
	}
}