package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.Item;
import model.Loan;
import model.Outstanding;
import model.Transaction;
import utility.ManageLoan;
import utility.OutstandingCalculator;

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
		for (Transaction transaction: loan.getTransactions()) {
			System.out.println(transaction.getDate());
		}
		return loan;
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public long updateLoan(@RequestBody Loan loan) {
		for (Item item: loan.getItems()) {
			item.setLoan(loan);
		}
		long result = new ManageLoan().updateLoan(loan); 
		return result;
	}
	@RequestMapping(value="/getOutstanding", method=RequestMethod.GET)
	public Outstanding updateLoan(@RequestParam("loanId") long loanId) {
		return new OutstandingCalculator().calculateOutstanding(loanId);
	}
}