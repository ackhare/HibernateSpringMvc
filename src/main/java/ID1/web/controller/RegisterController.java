package ID1.web.controller;



import javax.validation.*;

import ID1.model.Register;
import ID1.services.ManyToManyService;
import ID1.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Controller
@RequestMapping(value = "/register")
//@SessionAttributes("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    private Validator validator;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView save(@Valid Register register,
                             BindingResult result/*, SessionStatus status*/) {
        /*Set<ConstraintViolation<RegisterCO>> violations = validator.validate(registerCO);
        for (ConstraintViolation<RegisterCO> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("employee",propertyPath,

                    "Invalid "+ propertyPath + "(" + message + ")"));
        }*/
        ModelAndView modelAndView=new ModelAndView();

        if(result.hasErrors()){
            modelAndView.setViewName("register");

        }
        else{
            modelAndView.setViewName("index");

        }
//        status.setComplete();
        return modelAndView;
    }
}
