package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.Customer;
import model.Item;
import model.Loan;
import utility.ManageLoan;

@EnableWebMvc
@RestController
@RequestMapping("/loan")
public class LoanController {
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public long addLoan() {
		Customer customer = new Customer("Prady","M",new Date(),"Addr","Mettur","1234","0987654321");
		List<Item> items = new ArrayList<Item>();
		Item item = new Item("test1", (double)10); 
		items.add(item);
		Loan loan = new Loan(customer, new Date(), (double)10, items);
		item.setLoan(loan);
		return new ManageLoan().addLoan(loan);
	}
}