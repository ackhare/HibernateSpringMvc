package ID1.web.controller;

import ID1.services.ManyToManyService;
import ID1.services.ViewResolverUtil;
import ID1.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/manyToMany")
public class ManyToManyController {
    @Autowired
    private ManyToManyService manyToManyService;
    @Autowired
    private ViewResolverUtil viewResolverUtil;

    @RequestMapping(value = {"/createReaderAndSubscribers"}, method = RequestMethod.GET)
    public ModelAndView createReaderAndSubscribers(){
        System.out.println("***************************MANYTOMANY******************************");
        System.out.println("***************************MANYTOMANY******************************");

        manyToManyService.createReaderAndSubscribers();

        System.out.println("***************************MANYTOMANY******************************");
        System.out.println("***************************MANYTOMANY******************************");
        return viewResolverUtil.renderModelAndView();


    }
}
