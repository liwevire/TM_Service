package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.Loan;
import utility.ManageLoan;

@EnableWebMvc
@RestController
@RequestMapping("/loan")
public class LoanController {
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public long addLoan(Loan loan) {
		return new ManageLoan().addLoan(loan);
	}
}