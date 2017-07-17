package controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.reports.Daily;
import utility.DailyReportsManager;

@EnableWebMvc
@RestController
@RequestMapping("/reports")
public class ReportsController {
	@RequestMapping(value="/calculateDaily", method=RequestMethod.GET)
	public Daily calculateDailyReport(@RequestParam("calculationDate") Date date) {
		return new DailyReportsManager().calculateDailyReport(date);
	}
//	@RequestMapping(value="/recalculateDaily", method=RequestMethod.GET)
//	public Daily recalculateDailyReport(@RequestParam("calculationDate") Date date) {
//		return new DailyReportsManager().recalculateDailyReport(date);
//	}
}