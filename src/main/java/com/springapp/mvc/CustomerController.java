
package com.springapp.mvc;

/**
 * Created by raphael zielinski on 2/5/2015.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    private Long idEditedRow =null;
    private Customer customer;
    private static final Logger logger = LoggerFactory
            .getLogger(CustomerController.class);

    @Autowired
    @Qualifier("employeeValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ExceptionHandler(CustomExceptionHandler.class)
    public ModelAndView handleCustomException(CustomExceptionHandler ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", ex.getMessage());
        return model;
    }
 //Request and response of home page , allow to add new customer and list if isn`t empty
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Object listCustomers(ModelMap model) throws IOException {
        if(idEditedRow==null){
            model.addAttribute("customer", new Customer());
        }
        else{
            model.addAttribute("customer", Helper.getCustomer(idEditedRow, customerRepository));
        }
        model.addAttribute("customers", customerRepository.findAll());
        Helper.initialStatesList(model);
        return "customers";
    }

    @RequestMapping(value = "/Allusers", method = RequestMethod.GET)
    public Object listCustomersAll(ModelMap model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "Alluser";
    }

    @RequestMapping(value = "/Contact", method = RequestMethod.GET)
    public Object ContactPage() {
        return "Contact";
    }

//Request and response while adding customer from a list
   @RequestMapping(value="/add" ,method= RequestMethod.POST)
    public Object addCustomer(@Valid Customer customer, BindingResult result, ModelMap model) throws IOException {
        if(result.hasErrors()){
            model.addAttribute("customer",customer);
            model.addAttribute("customers", customerRepository.findAll());
            Helper.initialStatesList(model);
           return "customers";
        }else {
            if (idEditedRow == null) {
                customerRepository.save(customer);
                if (customerRepository.findAll().size() > 5) {
                    //throw new Exception("Too many customers in database ;)");
                    throw new CustomExceptionHandler("Too many customers in a database ;)");
                }
            }else{
                Customer updatedCustomer = this.customerRepository.findOne(idEditedRow);
                Helper.updateCustomer(customer, updatedCustomer, customerRepository);
                idEditedRow = null;
            }
        }
       return "redirect:/";
    }
//Request and response while deleting customer from a list method=RequestMethod.DELETE
    @RequestMapping(value = "/delete/{customerId}")
    public Object deleteCustomer(@PathVariable("customerId") Long customerId,ModelMap model) {
        customerRepository.delete(customerRepository.findOne(customerId));
        idEditedRow=null;
        return "redirect:/";
    }

//Request and response while editing customer from a list method=RequestMethod.POST
    @RequestMapping(value ="/edit/{customerId}")
    public Object editCustomer(@PathVariable("customerId") Long customerId,ModelMap model){
        Customer customer = Helper.getCustomer(customerId, customerRepository);
        model.addAttribute("customer", customer);
        model.addAttribute("customers", customerRepository.findAll());
        idEditedRow=customerId;
        return "redirect:/";
    }

    @RequestMapping(value = "/allCustomersJSON", method = RequestMethod.GET)
    public

    String listUsersJsonMenu(ModelMap model) throws JSONException {
        JSONArray userArray = new JSONArray();
        int i =0;
        for (Customer customer : customerRepository.findAll()) {
            i++;
            JSONObject userJSON = new JSONObject();
            userJSON.put("#",i);
            userJSON.put("id", customer.getId());
            userJSON.put("Name", customer.getName());
            userJSON.put("Email", customer.getEmail());
            userJSON.put("City", customer.getCity());
            userJSON.put("Telephone", customer.getTelephone());
            userJSON.put("Street", customer.getStreet());
            userJSON.put("Zip", customer.getZip());
            userArray.put(userJSON);

        }
        model.addAttribute("JSON",userArray.toString());
        return "AlluserJSON";
    }

}