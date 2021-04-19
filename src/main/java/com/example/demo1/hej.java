package com.example.demo1;


import com.example.demo1.models.CartService;
import com.example.demo1.models.Customer;
import com.example.demo1.models.Product;
import com.example.demo1.repositories.CustomerRepository;
import com.example.demo1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-13
 * Time: 15:45
 * Project: Project2003-G07
 * Copyright: MIT
 */
@Controller
public class hej {



    @Autowired
    private CustomerRepository customerRepository;

   @Autowired
   private ProductRepository productRepository;

   List<CartService> cartServiceList = new ArrayList<>();

   List<Product> proInCart = new ArrayList<>();



    @GetMapping("/greeting")
    public String greetingForm(@RequestParam String firstNa, String lastNa, String teleNr , String emailAd , Model model) throws SQLException {

        model.addAttribute("firstNa",firstNa);
        model.addAttribute("lastNa",lastNa);
        model.addAttribute("teleNr",teleNr);
        model.addAttribute("emailAd",emailAd);


        return "customers";
    }


    @GetMapping("/register")
    public String showForm(Model model){
        System.out.println("register funkar");


        return "regiser_form";
    }

   @PostMapping("/register")
   public String submitForm(@ModelAttribute("customer") Customer customer){
       System.out.println(customer);
       return "register_success";
   }





    @GetMapping("admin/addKund.html")
    public ModelAndView addCustomer() throws SQLException {

        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    @GetMapping("/index")
    public String listCustomers(Model model) throws SQLException {


       // ModelAndView modelAndView = new ModelAndView();
/*

        productRepository.save(new Product("Falun Gong Earl Grey",160,"https://picsum.photos/500?random=1","Ett svart te smaksatt med bergamott.",7000,"Dryck", true));
        productRepository.save(new Product("Glass",200,"https://picsum.photos/500?random=2","Ett svart te smaksatt med bergamott.",200,"Dryck",true));
        productRepository.save(new Product("Pasta",99,"https://picsum.photos/500?random=3","Ett svart te smaksatt med bergamott.",99,"Dryck",true));


 */

       // modelAndView.addObject("listCustomers", productRepository.findAll());

        model.addAttribute("listCustomers", productRepository.findAll());

        return "index";
    }

    @RequestMapping("/index/add")
    public String addProductToCart1(@RequestParam("id") Product product){

        Product stock = productRepository.findById(product.getId()).get();


        if (stock.getStorage() > 0){
           // cartService.setProductInCart(product);
            stock.setStorage(stock.getStorage()-1);
            productRepository.save(stock);

            System.out.println(productRepository.findById(product.getId()).get().getStorage());


proInCart.add(product);

            boolean newItemInCart = false;


            for (int i = 0; i < cartServiceList.size(); i++) {
                if (cartServiceList.get(i).getProduct().getId() == product.getId()){
                    cartServiceList.get(i).setQuantity(cartServiceList.get(i).getQuantity()+1);
                    newItemInCart = false;
                    break;
                }else {
                    newItemInCart = true;
                }
            }
            if (newItemInCart) {
                cartServiceList.add(new CartService(product, 1));
            }

        }else {

        }






        return "redirect:/index";
    }



    @GetMapping("Startsida/produktsida")
    public ModelAndView showProduct() throws SQLException {

        ModelAndView modelAndView = new ModelAndView();


        modelAndView.addObject("listCustomers", productRepository.findAll());


        return modelAndView;
    }


    @GetMapping("/Startsida/produktsida/")
    public ModelAndView showProducts(@RequestParam long id){
        Product product = productRepository.getProductById(id);

        List<Product> productList = new ArrayList<>();
        productList.clear();
        productList.add(product);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productToShow",productList);

        return modelAndView;
    }


   @GetMapping("varukorg/groceryCart")
   public String showCart(Model model){



        model.addAttribute("listProductsInCart", proInCart);



        return "varukorg/grocerycart";
   }



}