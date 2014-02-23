package com.gurps.mongo.main;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.gurps.mongo.repository.Address;
import com.gurps.mongo.repository.Customer;
import com.gurps.mongo.repository.CustomerRepository;




@ComponentScan(basePackageClasses = {Main.class})
@EnableAutoConfiguration

/**
 * This flow makes use of spring data repositories rather than a MongoDB template
 * 
 * Some quick fire examples showing
 * 1)Insertion of simple document
 * 2)Insertion of document with a embedded document
 * 3)Updating pre-existing document with with an embedded array
 * 4)Finding a list of documents where the criteria is a embedded document i.e. find customer by address.
 * @author gurps
 *
 */
public class Main implements CommandLineRunner{
	
	  public static Logger LOGGER = LoggerFactory.getLogger(Main.class);


		@Autowired
		private CustomerRepository customerRepository;

		public static void main(String[] args) {
		
			SpringApplication.run(Main.class, args);
		}

		
		public void run(String... args) throws Exception {
			
			customerRepository.deleteAll();

			Customer simpleCustomer = new Customer("Simple", "Simon");
			customerRepository.save(simpleCustomer);
			
			Customer customerWithAddress = new Customer("Gurps", "Bassi");
			customerWithAddress.setAddress(new Address("421 Acadia Drive", null, "Hamilton", "L8W 2R4", "Canada"));
			Customer savedCustomer = customerRepository.save(customerWithAddress);
			
			//Try adding hobbies to an existing customer
			savedCustomer.setHobbies(Arrays.asList("football", "reading"));
			customerRepository.save(savedCustomer);
			
			List<Customer> customersFoundByAddress = customerRepository.findByAddress(new Address("421 Acadia Drive", null, "Hamilton", "L8W 2R4", "Canada"));
			for(Customer customer : customersFoundByAddress){
				System.out.println("Customer found by address = " + customer);
			}

			// fetch all customers
			LOGGER.info("Customers found with findAll():");
			LOGGER.info("-------------------------------");
			for (Customer customer : customerRepository.findAll()) {
				System.out.println(customer);
			}
			

			// fetch an individual customer
			LOGGER.info("Customer found with findByFirstName('Simple'):");
			LOGGER.info("--------------------------------");
			LOGGER.info(customerRepository.findByFirstName("Simple").toString());

			LOGGER.info("Customers found with findByLastName('Simon'):");
			LOGGER.info("--------------------------------");
			for (Customer customer : customerRepository.findByLastName("Simon")) {
				System.out.println(customer);
			}
		}
	}