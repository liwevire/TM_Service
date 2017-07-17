package controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.core.Customer;
import model.core.MiscTransaction;
import utility.CustomerManager;
import utility.MiscTransactionManager;

@EnableWebMvc
@RestController
@RequestMapping("/miscTransaction")
public class MiscTransactionController {
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public long addCustomer(@RequestBody MiscTransaction miscTransaction) {
		long result = new MiscTransactionManager().addMiscTransaction(miscTransaction);
		return result;
	}
	@RequestMapping(value="/getByDate", method=RequestMethod.GET)
	public List<MiscTransaction> getMiscTransaction(@RequestParam("date") Date date) {
		return new MiscTransactionManager().getMiscTransaction(date);
	}
	@RequestMapping(value="/getByTransactionId", method=RequestMethod.GET)
	public MiscTransaction getMiscTransaction(@RequestParam("transactionId") long transactionId) {
		return new MiscTransactionManager().getMiscTransaction(transactionId);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public long updateMiscTransaction(@RequestBody MiscTransaction miscTransaction) {
		long result = new MiscTransactionManager().updateMiscTransaction(miscTransaction);
		return result;
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteMiscTransaction(@RequestParam("transactionId") long transactionId ) {
		return new MiscTransactionManager().deleteMiscTransaction(transactionId);	
		
	}
}