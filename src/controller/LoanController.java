package controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.core.Item;
import model.core.Loan;
import model.core.Outstanding;
import utility.LoanManager;
import utility.OutstandingCalculator;

@EnableWebMvc
@RestController
@RequestMapping("/loan")
public class LoanController {
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addLoan(@RequestBody Loan loan) {
		if (null != loan.getLoanId() && loan.getLoanId() != "") 			
			loan.setLoanId(loan.getLoanId().toUpperCase());
		for (Item item: loan.getItems()) {
			item.setLoan(loan);
		}
		String result = new LoanManager().addLoan(loan); 
		return result;
	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public Loan getLoan(@RequestParam("loanId") String loanId ) {
		Loan loan = new LoanManager().getLoan(loanId);	
		return loan;
	}
	@RequestMapping(value="/getLoansByDate", method=RequestMethod.GET)
	public List<Loan> getLoan(@RequestParam("date") Date date ) {
		List<Loan> loans= new LoanManager().getLoans(date);	
		return loans;
	}
	@RequestMapping(value="/getOpenLoans", method=RequestMethod.GET)
	public List<Loan> getLoan(@RequestParam("fromDate")Date fromDate, @RequestParam("toDate")Date toDate) {
		List<Loan> loans = new LoanManager().getLoans(fromDate, toDate);
//		for (Iterator iterator = loans.iterator(); iterator.hasNext();) {
//			Loan loan = (Loan) iterator.next();
//			System.out.println(loan.getLoanId());
//		}
		return loans;	
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public long updateLoan(@RequestBody Loan loan) {
		if (null != loan.getLoanId() && loan.getLoanId() != "") 			
			loan.setLoanId(loan.getLoanId().toUpperCase());
		for (Item item: loan.getItems()) {
			item.setLoan(loan);
		}
		long result = new LoanManager().updateLoan(loan); 
		return result;
	}
	@RequestMapping(value="/getOutstanding", method=RequestMethod.GET)
	public Outstanding updateLoan(@RequestParam("loanId") String loanId) {
		return new OutstandingCalculator().calculateOutstanding(loanId);
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteLoan(@RequestParam("loanId") String loanId ) {
		return new LoanManager().deleteLoan(loanId);	
	}
	
}