package ru.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.service.Product;
import ru.edu.service.ProductCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private ProductCache cache;

    @Autowired
    public void setCache(ProductCache cache) {
        this.cache = cache;
    }

    @GetMapping(value = "/create")
    public ModelAndView createProductView(HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {

//        if (!isAuthenticated(request)) {
//            response.sendRedirect("/auth/login");
//            return null;
//        }
        return new ModelAndView("/create_product.jsp");

    }


//    private ModelAndView getLoginForm() {
//        ModelAndView modelAndView = new ModelAndView("/login_form.jsp");
//        return modelAndView;
//
//    }


    @PostMapping("/update")
    public ModelAndView updateProduct(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam("id") String id,
                                      @RequestParam("name") String name,
                                      @RequestParam("price") String price) throws IOException {

//        if (!isAuthenticated(request)) {
//            response.sendRedirect("/auth/login");
//            return null;
//        }

        Product info = new Product(id, name, Double.parseDouble(price));
        cache.update(info);

        ModelAndView view = new ModelAndView("/success_create_product.jsp");
        return view;

    }

//    private boolean isAuthenticated(HttpServletRequest request) {
//
//        HttpSession session = request.getSession();
//        String authObject = (String) session.getAttribute("authObject");
//        if (!"AUTHENTICATED".equals(authObject)) {
//            return false;
//        }
//        return true;
//
//    }
}
