package controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import model.core.Customer;
import utility.CustomerManager;

@EnableWebMvc
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public List<Customer> findCustomer(@RequestParam("name") String name) throws Exception {
		return new CustomerManager().listCustomer(name);
	}
	@RequestMapping(value="/getByLoanId", method=RequestMethod.GET)
	public Customer getByLoanId(@RequestParam("loanId") String loanId ) {
		Customer customer = new CustomerManager().getCustomerByLoan(loanId);	
		return customer;
	}
	
	@RequestMapping(value="/getByCustomerId", method=RequestMethod.GET)
	public Customer getCustomerByCustomerId(@RequestParam("customerId") String customerId ) {
		Customer customer = new CustomerManager().getCustomer(customerId);	
		return customer;
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public long addCustomer(@RequestBody Customer customer) {
		long result = new CustomerManager().addCustomer(customer);
		return result;
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public long updateCustomer(@RequestBody Customer customer) {
		long result = new CustomerManager().updateCustomer(customer);
		return result;
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteCustomer(@RequestParam("customerId") String customerId ) {
		return new CustomerManager().deleteCustomer(customerId);	
		
	}
}