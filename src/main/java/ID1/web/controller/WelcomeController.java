package ID1.web.controller;

import ID1.model.student.Student;
import ID1.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class WelcomeController {
//    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Autowired
    private WelcomeService welcomeService;
    @Autowired
    private OneTOOneUnidirectionalService oneTOOneUnidirectionalService;
    @Autowired
    private OneToOneBirectionalService oneToOneBirectionalService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private OneToManyUnidirectionalService oneToManyUnidirectionalService;
    @Autowired
    private OneToManyBidirectionalService oneToManyBidirectionalService;
    @Autowired
    private ViewResolverUtil viewResolverUtil;
    @Autowired
    private InheritanceService inheritanceService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return viewResolverUtil.renderModelAndView();
    }



    @RequestMapping(value = {"/getOrLoadStudent"}, method = RequestMethod.GET)
    public ModelAndView getOrLoadStudent() {
        System.out.println("***************************STUDDENT******************************");

        Student student = studentService.getOrLoad();

        System.out.println("***************************STUDDENT******************************"+student.getId());

        return viewResolverUtil.renderModelAndView();
    }

    @RequestMapping(value = {"/createMessageAndEmail"}, method = RequestMethod.GET)
    public ModelAndView createMessageAndEmailUnidirectional() {
        System.out.println("***************************ONE TO ONE UNIDIRECTIONAL******************************");

        oneTOOneUnidirectionalService.saveMessageAndEmail();

        System.out.println("***************************ONE TO ONE UNIDIRECTIONAL******************************");
        return viewResolverUtil.renderModelAndView();
    }


    @RequestMapping(value = {"/createMessageAndEmailBidirectional"}, method = RequestMethod.GET)
    public ModelAndView createMessageAndEmailBidirectional() {
        System.out.println("***************************ONE TO ONE BIDIRECTIONAL******************************");

        oneToOneBirectionalService.saveMessageAndEmailBirectional();

        System.out.println("***************************ONE TO ONE BIDIRECTIONAL******************************");
        return viewResolverUtil.renderModelAndView();
    }


    @RequestMapping(value = {"/oneToManyBi"}, method = RequestMethod.GET)
    public ModelAndView createEmployeeAndAcoountsBi() {

        System.out.println("***************************ONE TO MANY BIDIRECTIONAL ******************************");

        oneToManyBidirectionalService.saveEmployeeAndAccounts();


        System.out.println("***************************ONE TO MANY BIDIRECTIONAL******************************");

        return viewResolverUtil.renderModelAndView();
    }


    @RequestMapping(value = {"/oneToManyUni"}, method = RequestMethod.GET)
    public ModelAndView createEmployeeAndUni() {
        System.out.println("***************************ONE TO MANY UNIDIRECTIONAL******************************");

        oneToManyUnidirectionalService.saveEmployeeAndAccounts();

        System.out.println("***************************ONE TO MANY UNIDIRECTIONAL******************************");

        return viewResolverUtil.renderModelAndView();
    }


    @RequestMapping(value={"/oneToManyUniCriteria"},method=RequestMethod.GET)
    public void oneToManyUniCriteria(){
        System.out.println("***************************Criteria Queries******************************");
        System.out.println("***************************Criteria Queries******************************");
        oneToManyUnidirectionalService.criteria();
//        oneToManyUnidirectionalService.projections();
//        oneToManyUnidirectionalService.pagintion();
        System.out.println("***************************Criteria Queries******************************");
        System.out.println("***************************Criteria Queries******************************");
    }


    @RequestMapping(value={"/inheritanceInSingleTable"},method=RequestMethod.GET)
    public ModelAndView singleTable(){
        System.out.println("**************************Inheritance in Single Table*****************************");
        inheritanceService.singleTable();
        System.out.println("**************************Inheritance in Single Table*****************************");
        return viewResolverUtil.renderModelAndView();


    }
    @RequestMapping(value={"/inheritanceInJoined"},method=RequestMethod.GET)
    public ModelAndView joined(){
        System.out.println("**************************Inheritance in Single Table*****************************");
        inheritanceService.joined();
        System.out.println("**************************Inheritance in Single Table*****************************");
        return viewResolverUtil.renderModelAndView();


    }

}
