package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.Item;
import model.Loan;
import utility.ManageLoan;

@EnableWebMvc
@RestController
@RequestMapping("/loan")
public class LoanController {
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public long addLoan(@RequestBody Loan loan) {
		for (Item item: loan.getItems()) {
			item.setLoan(loan);
		}
		long result = new ManageLoan().addLoan(loan); 
		return result;
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public Loan getLoan(@RequestParam("loanId") long loanId ) {
		Loan loan = new ManageLoan().getLoan(loanId);	
		return loan;
	}
}