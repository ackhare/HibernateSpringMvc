package ID1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Service
public class ViewResolverUtil {
    @Autowired
    private WelcomeService welcomeService;

    public ModelAndView renderModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("title", welcomeService.getTitle());
        modelAndView.addObject("message", welcomeService.getMessage());
        return modelAndView;
    }

}
