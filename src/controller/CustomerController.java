package controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import model.Customer;
import utility.ManageCustomer;
//import utility.DBUtility;

@EnableWebMvc
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Customer> getAllCustomer() {
		return new ManageCustomer().listCustomer();
		
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public List<Customer> findCustomer(@RequestParam("name") String name) throws Exception {
		return new ManageCustomer().listCustomer(name);
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public List<Customer> findCustomer(@RequestParam("name") String name,
			@RequestParam("secondaryName") String secondaryName) {
		return new ManageCustomer().listCustomer(name);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public long addCustomer(@RequestBody Customer customer) {
		return new ManageCustomer().addCustomer(customer);
	}
}