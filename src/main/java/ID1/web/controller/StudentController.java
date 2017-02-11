package ID1.web.controller;

import ID1.model.Register;
import ID1.model.student.Student;
import ID1.services.StudentService;
import ID1.services.ViewResolverUtil;
import ID1.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Status;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private WelcomeService welcomeService;
    @Autowired
    private ViewResolverUtil viewResolverUtil;


    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView create() {
        System.out.println("***************************Create******************************");
        System.out.println("***************************Create******************************");

        studentService.create();
//        studentService.persist();
//        studentService.saveOrUpdate();
//        studentService.merge();
//        studentService.refresh();


        System.out.println("***************************Create******************************");
        System.out.println("***************************Create******************************");

        return viewResolverUtil.renderModelAndView();
    }

    @RequestMapping(value = {"/regis"}, method = RequestMethod.GET)
    public ModelAndView regis() {
        System.out.println("***************************Create******************************");
        System.out.println("***************************Create******************************");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = {"/regis"}, method = RequestMethod.POST)
    public ModelAndView regisyer(@ModelAttribute("employee") Register register, BindingResult result, Status status) {
        System.out.println("***************************Create******************************");
        System.out.println("***************************Create******************************");

        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.setViewName("register");

        }
        else{
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/hqlUpdate"}, method = RequestMethod.GET)
    public ModelAndView hqlUpdate() {
        System.out.println("*******************************UPDATE**************************");
        System.out.println("*******************************UPDATE**************************");
        Integer id = studentService.hqlUpdate();

        System.out.println("========================Number of rows affected=================================" + id);
        System.out.println("========================Number of rows affected=================================" + id);


        System.out.println("*******************************UPDATE**************************");
        System.out.println("*******************************UPDATE**************************");
        return viewResolverUtil.renderModelAndView();
    }

    @RequestMapping(value = {"/hqlDelete"}, method = RequestMethod.GET)
    public ModelAndView hqlDelete() {
        System.out.println("*******************************UPDATE**************************");
        System.out.println("*******************************UPDATE**************************");

        Integer id = studentService.hqlDelete();
        System.out.println("========================Number of rows affected=================================" + id);
        System.out.println("========================Number of rows affected=================================" + id);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("title", welcomeService.getTitle());
        model.addObject("message", welcomeService.getMessage());

        System.out.println("*******************************UPDATE**************************");
        System.out.println("*******************************UPDATE**************************");
        return model;
    }

    @RequestMapping(value = {"/hqlFrom"}, method = RequestMethod.GET)
    public ModelAndView hqlFrom() {
        System.out.println("*******************************hqlFrom**************************");
        System.out.println("*******************************hqlFrom**************************");
        List<Student> list = studentService.hqlFrom();

        for (Student student : list) {
            System.out.println("====================Student ID============" + student.getId());
            System.out.println("====================Student FirstName============" + student.getFirstName());
            System.out.println("====================Student LastName============" + student.getLastName());
            System.out.println("====================Student EmailAddress============" + student.getEmailAddress());

        }
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("title", welcomeService.getTitle());
        model.addObject("message", welcomeService.getMessage());

        System.out.println("*******************************HQL From**************************");
        System.out.println("*******************************HQL From**************************");
        return model;
    }


    @RequestMapping(value = {"/hqlSelect"}, method = RequestMethod.GET)
    public ModelAndView hqlSelect() {
        System.out.println("*******************************HQL Select**************************");
        System.out.println("*******************************HQL Select**************************");

        List<Object> list = studentService.hqlSelect();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object[] row = (Object[]) iterator.next();
            System.out.println("===================Last Name===================" + row[0]);
            System.out.println("===================Email Address===================" + row[1]);
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("title", welcomeService.getTitle());
        model.addObject("message", welcomeService.getMessage());

        System.out.println("*******************************HQL Select**************************");
        System.out.println("*******************************HQL Select**************************");
        return model;
    }


    @RequestMapping(value = {"/firstLevelCache"}, method = RequestMethod.GET)
    public void firstLevelCache() {
        System.out.println("*******************************First Level Cache**************************");
        System.out.println("*******************************First Level Cache**************************");
        Student student = studentService.firstLevelCache(21);

        System.out.println("==========================FIRSTNAME================" + student.getFirstName());
        System.out.println("==========================LastNAME================" + student.getLastName());
//        Retrieving Student at Id=21 second Time with new session
        /*student=studentService.getOrLoad(21);*/

        System.out.println("*******************************First Level Cache**************************");
        System.out.println("*******************************First Level Cache**************************");
    }


}
