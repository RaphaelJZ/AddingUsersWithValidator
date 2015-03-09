package com.springapp.mvc;

import org.springframework.ui.ModelMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rafal zielinski on 2/26/2015.
 */
public class Helper {



    public static Customer getCustomer(Long customerId,CustomerRepository customerRepository ) {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName(customerRepository.findOne(customerId).getName());
        customer.setEmail(customerRepository.findOne(customerId).getEmail());
        customer.setTelephone(customerRepository.findOne(customerId).getTelephone());
        customer.setStreet(customerRepository.findOne(customerId).getStreet());
        customer.setCity(customerRepository.findOne(customerId).getCity());
        customer.setZip(customerRepository.findOne(customerId).getZip());
        customer.setState(customerRepository.findOne(customerId).getState());
        return customer;
    }


    public static void updateCustomer(Customer customer, Customer updatedCustomer,CustomerRepository customerRepository) {
        updatedCustomer.setName(customer.getName());
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setTelephone(customer.getTelephone());
        updatedCustomer.setStreet(customer.getStreet());
        updatedCustomer.setCity(customer.getCity());
        updatedCustomer.setStreet(customer.getStreet());
        updatedCustomer.setZip(customer.getZip());
        updatedCustomer.setState(customer.getState());
        customerRepository.saveAndFlush(updatedCustomer);
    }

    public static void initialStatesList(ModelMap model) throws IOException {
        List<String> stateList  = readFile("d:/CyberCare_Test/statesList.txt");
        model.addAttribute("states", stateList);
    }

    public static List<String> readFile(String file)  throws IOException
    {
        BufferedReader in = null;
        List<String> list = new ArrayList<String>();
        try {
            in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                if(!str.isEmpty()) {
                    String[] ar = str.split(",");
                    list.addAll(Arrays.asList(ar));
                    list.removeAll(Arrays.asList("", null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        return list;
    }

}
