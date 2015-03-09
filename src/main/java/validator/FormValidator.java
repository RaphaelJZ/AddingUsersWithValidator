package validator;
/**
 * Created by raphael zielinski on 2/5/2015.
 */

import com.springapp.mvc.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.regex.Pattern;

public class FormValidator implements Validator {
    private final  Logger log = LoggerFactory.getLogger(FormValidator.class);
    private final  String zipCodeRegex = "^\\d{5}(-\\d{4})?$";
    private final  String phoneRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
    private final  String phoneFullRegex = "^?(\\d{10})";
    private final  String cityRegex = "^[A-Za-z]+$";

    //which objects can be validated by this validator
    @Override
    public boolean supports(Class<?> paramClass) {
        return Customer.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        try {
            Customer customer = (Customer) obj;
            blankField(errors, "name", "city", "telephone", "email", "state", "zip", "street");

            if (!isValidEmailAddress(customer.getEmail()) && !(customer.getEmail()).equals("")) {
                errors.rejectValue("email", "Email");
            }

            if (!isAValidRegex(zipCodeRegex, customer.getZip()) && !(customer.getZip()).equals("")) {
                errors.rejectValue("zip", "Zip");
            }
            if (!(isAValidRegex(phoneRegex, customer.getTelephone()) || isAValidRegex(phoneFullRegex, customer.getTelephone())) && !(customer.getTelephone()).equals("")) {
                errors.rejectValue("telephone", "Telephone");
            }
            if (!isAValidRegex(cityRegex, customer.getCity()) && !(customer.getCity()).equals("")) {
                errors.rejectValue("city", "City");
            }

        }catch (Exception e) {
           e.printStackTrace();

        }
    }
    private void blankField(Errors errors,String... fields){
        for(String field : fields){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, field + ".required");
        }
    }


    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException e) {
            log.error(e.getMessage(), e);
            result = false;
        }
        return result;
    }


    private boolean isAValidRegex(String regex,String field) {
        return Pattern.matches(regex, field);
    }
}


