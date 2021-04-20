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
   int totalpris;



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

    @GetMapping("/index.html")
    public String listCustomers(Model model) throws SQLException {




/*
        productRepository.save(new Product("Falun Gong Earl Grey",160,"https://picsum.photos/500?random=1","Ett svart te smaksatt med bergamott.",7000,"Dryck", true));
        productRepository.save(new Product("Glass",200,"https://picsum.photos/500?random=2","Ett svart te smaksatt med bergamott.",200,"Dryck",true));
        productRepository.save(new Product("Pasta",99,"https://picsum.photos/500?random=3","Ett svart te smaksatt med bergamott.",99,"Dryck",true));



 */

   //productRepository.save(new Product("Choklad",3,"https://picsum.photos/500?random=3","Ett svart te smaksatt med bergamott.",99,"Dryck",true));
        var a = productRepository.findAll();
        List<Product> productsToShow = new ArrayList<>();
        for (Product product: a) {
            if (product.isVisible() && product.getStorage()>0){
                productsToShow.add(product);
            }
        }




        model.addAttribute("listCustomers", productsToShow);

        return "index.html";
    }

    @RequestMapping("/index.html/add")
    public String addProductToCart1(@RequestParam("id") Product product){

        Product stock = productRepository.findById(product.getId()).get();


        if (stock.getStorage() > 0){
            stock.setStorage(stock.getStorage()-1);
            productRepository.save(stock);



            boolean newItemInCart = false;

            if (proInCart.size() == 0){
                newItemInCart = true;
            }else {
                for (int i = 0; i < proInCart.size(); i++) {
                    if (proInCart.get(i).getId().equals(product.getId())){
                        proInCart.get(i).setQuant(proInCart.get(i).getQuant()+1);
                        newItemInCart = false;
                        break;
                    }else {
                        newItemInCart = true;
                    }
                }
            }



            if (newItemInCart) {
                product.setQuant(product.getQuant()+1);
                proInCart.add(product);
            }

        }

        totalpris += product.getPrice();




        return "redirect:/index.html";
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

    @RequestMapping("/Startsida/produktsida/add")
    public String addProduct(@RequestParam("id") Product product){

        Product stock = productRepository.findById(product.getId()).get();


        if (stock.getStorage() > 0){
            stock.setStorage(stock.getStorage()-1);
            productRepository.save(stock);



            boolean newItemInCart = false;

            if (proInCart.size() == 0){
                newItemInCart = true;
            }else {
                for (int i = 0; i < proInCart.size(); i++) {
                    if (proInCart.get(i).getId().equals(product.getId())){
                        proInCart.get(i).setQuant(proInCart.get(i).getQuant()+1);
                        newItemInCart = false;
                        break;
                    }else {
                        newItemInCart = true;
                    }
                }
            }



            if (newItemInCart) {
                product.setQuant(product.getQuant()+1);
                proInCart.add(product);
            }

        }

        totalpris += product.getPrice();

        String url;

        if (product.getStorage() == 0){
             url = "redirect:/index.html";
        }else {
             url = "redirect:/Startsida/produktsida/?id=" + product.getId();
        }





        return url;
    }




   @GetMapping("varukorg/groceryCart.html")
   public String showCart(Model model){



        model.addAttribute("listProductsInCart", proInCart);
        model.addAttribute("totalpris", totalpris);



        return "varukorg/grocerycart.html";
   }



    @RequestMapping("/groceryCart/add")
    public String addProductToGrosCart(@RequestParam("id") Product product){

        Product stock = productRepository.findById(product.getId()).get();
        if (stock.getStorage() != 0){
            stock.setStorage(stock.getStorage()-1);
            productRepository.save(stock);





        for (int i = 0; i < proInCart.size(); i++) {
            if (proInCart.get(i).getId().equals(product.getId())){
                proInCart.get(i).setQuant(proInCart.get(i).getQuant()+1);
            }

        }

        totalpris += product.getPrice();
    }

        return "redirect:/varukorg/groceryCart.html";
    }



    @RequestMapping("/groceryCart/delete")
    public String deleteProductToGrosCart(@RequestParam("id") Product product){

        Product stock = productRepository.findById(product.getId()).get();
        stock.setStorage(stock.getStorage()+1);
        productRepository.save(stock);



        for (int i = 0; i < proInCart.size(); i++) {
            if (proInCart.get(i).getId().equals(product.getId())){
                proInCart.get(i).setQuant(proInCart.get(i).getQuant()-1);
                if (proInCart.get(i).getQuant() == 0){
                    proInCart.remove(i);
                }
            }

        }

        totalpris -= product.getPrice();


        return "redirect:/varukorg/groceryCart.html";
    }


}