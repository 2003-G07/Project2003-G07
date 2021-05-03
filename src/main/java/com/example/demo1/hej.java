package com.example.demo1;

import com.example.demo1.controllers.OrdersController;
import com.example.demo1.models.CartService;
import com.example.demo1.models.CheckOutForm;
import com.example.demo1.models.Customer;
import com.example.demo1.models.Product;
import com.example.demo1.repositories.CustomerRepository;
import com.example.demo1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class hej {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    List<CartService> cartServiceList = new ArrayList<>();
    List<Product> proInCart = new ArrayList<>();

    CheckOutForm checkOutFormGlobal;


    int totalpris;
    List<Product> outOfStock = new ArrayList<>();
    String url;

    @GetMapping("/greeting")
    public String greetingForm(@RequestParam String firstNa, String lastNa,
                               String teleNr, String emailAd, Model model) {
        model.addAttribute("firstNa", firstNa);
        model.addAttribute("lastNa", lastNa);
        model.addAttribute("teleNr", teleNr);
        model.addAttribute("emailAd", emailAd);

        return "customers";
    }

    @GetMapping("/register")
    public String showForm(Model model) {

        return "regiser_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("customer") Customer customer) {
        return "register_success";
    }

    @GetMapping("admin/addKund.html")
    public ModelAndView addCustomer() {
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @GetMapping("/index.html")
    public String listCustomers(Model model) {

/*
        productRepository.save(new Product("Falun Gong Earl Grey",160,"https://picsum.photos/500?random=1","Ett svart te smaksatt med bergamott.",7000,"Dryck", true));
        productRepository.save(new Product("Glass",200,"https://picsum.photos/500?random=2","Ett svart te smaksatt med bergamott.",200,"Dryck",true));
        productRepository.save(new Product("Pasta",99,"https://picsum.photos/500?random=3","Ett svart te smaksatt med bergamott.",99,"Dryck",true));

        productRepository.save(new Product("Choklad",3,"https://picsum.photos/500?random=3","Ett svart te smaksatt med bergamott.",99,"Dryck",true));

 */
        var a = productRepository.findAll();
        List<Product> productsToShow = new ArrayList<>();
        for (Product product : a) {
            if (product.isVisible() && product.getStorage() > 0) {
                productsToShow.add(product);
            }
        }

        model.addAttribute("listCustomers", productsToShow);

        return "index.html";
    }

    @GetMapping("/")
    public String showIndex() {

        return "redirect:/index.html";
    }

    @RequestMapping("/index.html/add")
    public String addProductToCart1(@RequestParam("id") Product product) {
        Product stock = productRepository.findById(product.getId()).get();

        if (stock.getStorage() > 0) {
            stock.setStorage(stock.getStorage() - 1);
            productRepository.save(stock);

            boolean newItemInCart = false;

            /* REPLACE LOOP BELOW? /SANA

                        if (proInCart.size() == 0) {
                newItemInCart = true;
            } else {
                for (Product value : proInCart) {
                    if (value.getId().equals(product.getId())) {
                        value.setQuant(value.getQuant() + 1);
                        newItemInCart = false;
                        break;
                    } else {
                        newItemInCart = true;
                    }
                }
            }
             */
            if (proInCart.size() == 0) {
                newItemInCart = true;
            } else {
                for (int i = 0; i < proInCart.size(); i++) {
                    if (proInCart.get(i).getId().equals(product.getId())) {
                        proInCart.get(i).setQuant(proInCart.get(i).getQuant() + 1);
                        newItemInCart = false;
                        break;
                    } else {
                        newItemInCart = true;
                    }
                }
            }

            if (newItemInCart) {
                product.setQuant(product.getQuant() + 1);
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
    public ModelAndView showProducts(@RequestParam long id) {
        Product product = productRepository.getProductById(id);

        List<Product> productList = new ArrayList<>();

        productList.clear();
        //clear eller reset? /Sana
        productList.add(product);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productToShow", productList);

        return modelAndView;

    }

    @RequestMapping("/Startsida/produktsida/add")
    public String addProduct(@RequestParam("id") Product product) {
        Product stock = productRepository.findById(product.getId()).get();

        if (stock.getStorage() > 0) {
            stock.setStorage(stock.getStorage() - 1);
            productRepository.save(stock);

            boolean newItemInCart = false;

            /* REPLACE LOOP BELOW? /SANA
                        if (proInCart.size() == 0) {
                newItemInCart = true;
            } else {
                for (Product value : proInCart) {
                    if (value.getId().equals(product.getId())) {
                        value.setQuant(value.getQuant() + 1);
                        newItemInCart = false;
                        break;
                    } else {
                        newItemInCart = true;
                    }
                }
            }
             */

            if (proInCart.size() == 0) {
                newItemInCart = true;
            } else {
                for (int i = 0; i < proInCart.size(); i++) {
                    if (proInCart.get(i).getId().equals(product.getId())) {
                        proInCart.get(i).setQuant(proInCart.get(i).getQuant() + 1);
                        newItemInCart = false;
                        break;
                    } else {
                        newItemInCart = true;
                    }
                }
            }

            if (newItemInCart) {
                product.setQuant(product.getQuant() + 1);
                proInCart.add(product);
            }
        }

        totalpris += product.getPrice();

        String url;

        if (product.getStorage() == 0) {
            url = "redirect:/index.html";
        } else {
            url = "redirect:/Startsida/produktsida/?id=" + product.getId();
        }

        return url;
    }

    boolean sendErrorMessageIfEmptyStorage = false;
    @GetMapping("varukorg/groceryCart.html")
    public String showCart(Model model) {
        String error = "Slut på lager: ";
        if (outOfStock != null){
            for (int i = 0; i < outOfStock.size(); i++) {
                error += outOfStock.get(i).getName() + ", ";
            }
            error = error.substring(0, error.length()-2);
        }



        model.addAttribute("listProductsInCart", proInCart);
        model.addAttribute("totalpris", totalpris);
        if (sendErrorMessageIfEmptyStorage){
            model.addAttribute("errorMessage", error);
        }


        return "varukorg/groceryCart.html";
    }

    @RequestMapping("/groceryCart/add")
    public String addProductToGrosCart(@RequestParam("id") Product product) {

        Product stock = productRepository.findById(product.getId()).get();
        if (stock.getStorage() != 0) {
            stock.setStorage(stock.getStorage() - 1);
            productRepository.save(stock);
/* REPLACE LOOP BELOW? /SANA

            for (Product value : proInCart) {
                if (value.getId().equals(product.getId())) {
                    value.setQuant(value.getQuant() + 1);
                }

            }

 */
            for (int i = 0; i < proInCart.size(); i++) {
                if (proInCart.get(i).getId().equals(product.getId())) {
                    proInCart.get(i).setQuant(proInCart.get(i).getQuant() + 1);
                }
            }

            totalpris += product.getPrice();
        }

        return "redirect:/varukorg/groceryCart.html";
    }

    @RequestMapping("/groceryCart/delete")
    public String deleteProductToGrosCart(@RequestParam("id") Product product) {

        Product stock = productRepository.findById(product.getId()).get();
        stock.setStorage(stock.getStorage() + 1);
        productRepository.save(stock);

        for (int i = 0; i < proInCart.size(); i++) {
            if (proInCart.get(i).getId().equals(product.getId())) {
                proInCart.get(i).setQuant(proInCart.get(i).getQuant() - 1);
                if (proInCart.get(i).getQuant() == 0) {
                    proInCart.remove(i);
                }
            }
        }

        totalpris -= product.getPrice();

        return "redirect:/varukorg/groceryCart.html";
    }

    //Används vid navbar för att hämta alla produkter till varukorgen
    @PostMapping(value="/url")
    public String postCustomer(@RequestBody List<Integer> productIdToCart){

        Collections.sort(productIdToCart);
        long prev = 0;

        proInCart.clear();
        totalpris = 0;

        for (int i = 0; i < productIdToCart.size(); i++) {

            if (prev == productIdToCart.get(i)){
                for (int j = 0; j <proInCart.size(); j++) {
                    if (proInCart.get(j).getId().equals(prev)){
                        proInCart.get(j).setQuant(proInCart.get(j).getQuant()+1);
                        totalpris += proInCart.get(j).getPrice();
                    }
                }
            }else {
                prev = productIdToCart.get(i);
                Product temp = productRepository.getProductById(productIdToCart.get(i));
                temp.setQuant(1);
                proInCart.add(temp);
                totalpris += temp.getPrice();
            }

        }



        return "redirect:/varukorg/groceryCart.html";
    }


    @PostMapping("/varukorg/submitcheckoutform")
    public String submitCheckOutForm(@Valid CheckOutForm checkOutForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "varukorg/checkout.html";
        }

        checkOutFormGlobal = checkOutForm;


        return "redirect:/varukorg/confirmedorder.html";
    }




    //Avänds vid checkout om allt finns på lagret
    @PostMapping(value="/url2")
    public String postCustomer2(@RequestBody List<Integer> productIdToCart){

        Collections.sort(productIdToCart);
        long prev = 0;
        proInCart.clear();

        for (int i = 0; i < productIdToCart.size(); i++) {

            if (prev == productIdToCart.get(i)){
                for (int j = 0; j <proInCart.size(); j++) {
                    if (proInCart.get(j).getId().equals(prev)){
                        proInCart.get(j).setQuant(proInCart.get(j).getQuant()+1);
                        totalpris += proInCart.get(j).getPrice();
                    }
                }
            }else {
                prev = productIdToCart.get(i);
                Product temp = productRepository.getProductById(productIdToCart.get(i));
                temp.setQuant(1);
                proInCart.add(temp);
                totalpris += temp.getPrice();
            }

        }



        //ANVÄND PROINCART FÖR ATT HITTA ALLA KÖPTA VAROR
        //ANVÄND CHECKOUTFORMGLOBAL FÖR ATT HITTA KUNDEN

        OrdersController ordersController = new OrdersController();

        ordersController.addOrder(proInCart,checkOutFormGlobal.getFirstName(),checkOutFormGlobal.getLastName(),checkOutFormGlobal.getPhoneNumber(),checkOutFormGlobal.getEmail(),checkOutFormGlobal.getCity(),checkOutFormGlobal.getAddress(),checkOutFormGlobal.getZipCode());














        proInCart.clear();
        return "redirect:/varukorg/confirmedorder.html";
    }


    @PostMapping(value="varukorg/groceryCart.html/checkoutItems")
    public String checkoutCartStorageItems(@RequestBody List<Integer> product, HttpServletResponse response) {
        long prev = 0;

        proInCart.clear();
        totalpris = 0;
        outOfStock.clear();



        for (int i = 0; i < product.size(); i++) {

            if (prev == product.get(i)){
                for (int j = 0; j <proInCart.size(); j++) {
                    if (proInCart.get(j).getId().equals(prev)){
                        proInCart.get(j).setQuant(proInCart.get(j).getQuant()+1);
                        totalpris += proInCart.get(j).getPrice();
                    }
                }
            }else {
                prev = product.get(i);
                Product temp = productRepository.getProductById(product.get(i));
                temp.setQuant(1);
                proInCart.add(temp);
                totalpris += temp.getPrice();
            }

        }
        boolean redirect = true;

        for (int i = 0; i <proInCart.size() ; i++) {
            if (proInCart.get(i).getQuant() > productRepository.getProductById(proInCart.get(i).getId()).getStorage()){
                outOfStock.add(proInCart.get(i));
                redirect = false;
            }
        }

        if (redirect) {
            url = "redirect:/varukorg/checkout.html";
            sendErrorMessageIfEmptyStorage = false;
        } else{
            sendErrorMessageIfEmptyStorage = true;
            url = "redirect:/varukorg/groceryCart.html";
        }

        return url;
    }


    @GetMapping("/direct")
    public String direction(){

        return url;
    }






}


