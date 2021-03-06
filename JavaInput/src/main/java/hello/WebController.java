package hello;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class WebController extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }
    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @GetMapping("/JsStudy")
    public String showJsStudy() {
        return "JsStudyOne";
    }
    @GetMapping("/protocal")
    public String getProtocal()
    {
        return "protocal";
    }

    @GetMapping("/Js2")
    public String showJs2( ) {
        return  "jsStudy2";
    }

    @GetMapping("/cookie")
    public String showCookie( ) {
        return  "CookieTest";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return  "redirect:/results";
    }


}
