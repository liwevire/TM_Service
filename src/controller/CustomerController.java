package controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import model.Customer;
import utility.CustomerManager;

@EnableWebMvc
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public List<Customer> findCustomer(@RequestParam("name") String name) throws Exception {
		return new CustomerManager().listCustomer(name);
	}

//	@RequestMapping(value = "/find", method = RequestMethod.GET)
//	public List<Customer> findCustomer(@RequestParam("name") String name,
//			@RequestParam("secondaryName") String secondaryName) {
//		return new ManageCustomer().listCustomer(name);
//	}
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public Customer getCustomer(@RequestParam("loanId") long loanId ) {
		Customer customer = new CustomerManager().getCustomer(loanId);	
		return customer;
	}
}