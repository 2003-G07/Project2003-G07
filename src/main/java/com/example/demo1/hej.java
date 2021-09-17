package com.example.demo1;

import com.example.demo1.models.*;
import com.example.demo1.repositories.*;
import com.example.demo1.util.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class hej {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    AuditLogger auditLogger;

    private final static Logger LOG = LoggerFactory.getLogger(AuditLogger.class);

    List<CartService> cartServiceList = new ArrayList<>();
    List<Product> proInCart = new ArrayList<>();

    CheckOutForm checkOutFormGlobal;

    int totalpris;
    List<Product> outOfStock = new ArrayList<>();
    String url;

    /**
     * This method is not being used currently
     *
     * @param firstNa
     * @param lastNa
     * @param teleNr
     * @param emailAd
     * @param model
     * @return
     */
    @GetMapping("/greeting")
    public String greetingForm(@RequestParam String firstNa, String lastNa,
                               String teleNr, String emailAd, Model model) {
        model.addAttribute("firstNa", firstNa);
        model.addAttribute("lastNa", lastNa);
        model.addAttribute("teleNr", teleNr);
        model.addAttribute("emailAd", emailAd);

        return "customers";
    }

    /**
     * This method is not being used currently
     *
     * @param model
     * @return
     */

    @GetMapping("/register")
    public String showForm(Model model) {

        return "regiser_form";
    }

    /**
     * This method is not being used currently
     *
     * @param customer
     * @return
     */

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("customer") Customer customer) {
        return "register_success";
    }

    /**
     * This method is not being used currently
     *
     * @return
     */

    @GetMapping("admin/addKund.html")
    public ModelAndView addCustomer() {
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    /**
     * Generating all products when entering index page.
     * Check if product is visible and has storage.
     *
     * @param model
     * @return
     */

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

    /**
     * Simple redirect to index when entering webpage
     *
     * @return
     */

    @GetMapping("/")
    public String showIndex() {

        return "redirect:/index.html";
    }

    /**
     * This method is not being used currently.
     *
     * @param product
     * @return
     */

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

    /**
     * This method is not being used currently.
     *
     * @return
     * @throws SQLException
     */

    @GetMapping("Startsida/produktsida")
    public ModelAndView showProduct() throws SQLException {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("listCustomers", productRepository.findAll());

        return modelAndView;
    }

    /**
     * Return selected product to show all info in page.
     *
     * @param id
     * @return
     */

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

    /**
     * This method is not being used currently.
     *
     * @param product
     * @return
     */

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

    /**
     * Returns error message if trying to buy a product out of stock.
     *
     * @param model
     * @return
     */

    @GetMapping("varukorg/groceryCart.html")
    public String showCart(Model model) {
        String error = "Slut på lager: ";
        if (outOfStock != null) {
            for (int i = 0; i < outOfStock.size(); i++) {
                error += outOfStock.get(i).getName() + ", ";
            }
            error = error.substring(0, error.length() - 2);
        }

        model.addAttribute("listProductsInCart", proInCart);
        model.addAttribute("totalpris", totalpris);

        if (sendErrorMessageIfEmptyStorage) {
            model.addAttribute("errorMessage", error);
        }

        return "varukorg/groceryCart.html";
    }

    /**
     * This method is not being used currently.
     *
     * @param product
     * @return
     */

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

    /**
     * This method is not being used currently.
     *
     * @param product
     * @return
     */

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

    /**
     * Show all products user has in shopping cart.
     *
     * @param productIdToCart
     * @return
     */

    @PostMapping(value = "/url")
    public String postCustomer(@RequestBody List<Integer> productIdToCart) {

        Collections.sort(productIdToCart);
        long prev = 0;

        proInCart.clear();
        totalpris = 0;

        for (int i = 0; i < productIdToCart.size(); i++) {

            if (prev == productIdToCart.get(i)) {
                for (int j = 0; j < proInCart.size(); j++) {
                    if (proInCart.get(j).getId().equals(prev)) {
                        proInCart.get(j).setQuant(proInCart.get(j).getQuant() + 1);
                        totalpris += proInCart.get(j).getPrice();
                    }
                }
            } else {
                prev = productIdToCart.get(i);
                Product temp = productRepository.getProductById(productIdToCart.get(i));
                temp.setQuant(1);
                proInCart.add(temp);
                totalpris += temp.getPrice();
            }

        }

        return "redirect:/varukorg/groceryCart.html";
    }

    /**
     * Store customer information from the checkout form.
     *
     * @param checkOutForm
     * @param bindingResult
     * @return
     */

    @PostMapping("/varukorg/submitcheckoutform")
    public String submitCheckOutForm(@Valid CheckOutForm checkOutForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "varukorg/checkout.html";
        }
        checkOutFormGlobal = checkOutForm;

        return "redirect:/varukorg/confirmedorder.html";
    }

    /**
     * Store customer, shopping cart information in database.
     * Set storage to new amount.
     *
     * @param productIdToCart
     * @return
     */

    @PostMapping(value = "/url2")
    public String postCustomer2(@RequestBody List<Integer> productIdToCart) {

        Collections.sort(productIdToCart);
        long prev = 0;
        proInCart.clear();
        totalpris = 0;

        for (int i = 0; i < productIdToCart.size(); i++) {

            if (prev == productIdToCart.get(i)) {
                for (int j = 0; j < proInCart.size(); j++) {
                    if (proInCart.get(j).getId().equals(prev)) {
                        proInCart.get(j).setQuant(proInCart.get(j).getQuant() + 1);
                        totalpris += proInCart.get(j).getPrice();
                    }
                }
            } else {
                prev = productIdToCart.get(i);
                Product temp = productRepository.getProductById(productIdToCart.get(i));
                temp.setQuant(1);
                proInCart.add(temp);
                totalpris += temp.getPrice();
            }
            LOG.info("totalpris i loop = " + totalpris);
            System.out.println("totalpris i loop = " + totalpris);

        }

        LOG.info("totalpris = " + totalpris);
        System.out.println("totalpris = " + totalpris);


        //ANVÄND PROINCART FÖR ATT HITTA ALLA KÖPTA VAROR
        //ANVÄND CHECKOUTFORMGLOBAL FÖR ATT HITTA KUNDEN

        Customer customer;
        Address address;

        // Create the time for the order.
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

        if (customerRepository == null) {
            customer = new Customer(checkOutFormGlobal.getFirstName(), checkOutFormGlobal.getLastName(), checkOutFormGlobal.getPhoneNumber(), checkOutFormGlobal.getEmail());
            customerRepository.save(customer);
        }
        // First check to see if customer exists. If they don't create a new and save to repo, if they do use the same one.
        else if (customerRepository.findByFirstNameAndLastNameAndTelAndEmail(checkOutFormGlobal.getFirstName(), checkOutFormGlobal.getLastName(), checkOutFormGlobal.getPhoneNumber(), checkOutFormGlobal.getEmail()).isEmpty()) {
            customer = new Customer(checkOutFormGlobal.getFirstName(), checkOutFormGlobal.getLastName(), checkOutFormGlobal.getPhoneNumber(), checkOutFormGlobal.getEmail());
            customerRepository.save(customer);

        } else {
            customer = customerRepository.findByFirstNameAndLastNameAndTelAndEmail(checkOutFormGlobal.getFirstName(), checkOutFormGlobal.getLastName(), checkOutFormGlobal.getPhoneNumber(), checkOutFormGlobal.getEmail()).get(0);
        }

        // Second check to see if address exists. If it doesn't create a new and save to repo, if it does use the same.
        if (addressRepository.findByCityAndAddressAndZip(checkOutFormGlobal.getCity(), checkOutFormGlobal.getAddress(), checkOutFormGlobal.getZipCode()).isEmpty()) {
            address = new Address(checkOutFormGlobal.getCity(), checkOutFormGlobal.getAddress(), checkOutFormGlobal.getZipCode());
            addressRepository.save(address);

        } else {
            address = addressRepository.findByCityAndAddressAndZip(checkOutFormGlobal.getCity(), checkOutFormGlobal.getAddress(), checkOutFormGlobal.getZipCode()).get(0);
        }

        // third check to see if product exists. if it doesnt send error, if it does proceed.

        // Create Order
        Orders orders = new Orders((long) totalpris, 1, customer, address);
        ordersRepository.save(orders);

        // add Order to OrderDetails
        OrderDetails orderDetails;
        for (Product product : proInCart) {
            orderDetails = new OrderDetails(orders, product);
            orderDetailsRepository.save(orderDetails);
            System.out.println("orders = " + orders);

            var b = productRepository.findById(product.getId()).get();

            int c = productRepository.findById(product.getId()).get().getStorage() - product.getQuant();
            b.setStorage(c);

            productRepository.save(b);

        }

        Iterable<Orders> orderToFind = ordersRepository.findAll();
        List<Orders> result = StreamSupport.stream(orderToFind.spliterator(), false).collect(Collectors.toList());
        var lastID = result.get(result.size()-1).getId();
        auditLogger.notify(new PaymentDto(lastID.toString(), 0));

        proInCart.clear();
        return "redirect:/varukorg/confirmedorder.html";
    }

    /**
     * Check if all products in shopping cart has stock.
     * Redirect user back to shopping cart if stock is to low.
     * Redirect user to checkout if stock has enough.
     *
     * @param product
     * @param response
     * @return
     */

    @PostMapping(value = "varukorg/groceryCart.html/checkoutItems")
    public String checkoutCartStorageItems(@RequestBody List<Integer> product, HttpServletResponse response) {
        long prev = 0;

        proInCart.clear();
        totalpris = 0;
        outOfStock.clear();

        for (int i = 0; i < product.size(); i++) {

            if (prev == product.get(i)) {
                for (int j = 0; j < proInCart.size(); j++) {
                    if (proInCart.get(j).getId().equals(prev)) {
                        proInCart.get(j).setQuant(proInCart.get(j).getQuant() + 1);
                        totalpris += proInCart.get(j).getPrice();
                    }
                }
            } else {
                prev = product.get(i);
                Product temp = productRepository.getProductById(product.get(i));
                temp.setQuant(1);
                proInCart.add(temp);
                totalpris += temp.getPrice();
            }

        }

        boolean redirect = true;

        if(proInCart.size()==0){
            redirect = false;
        }

        for (int i = 0; i < proInCart.size(); i++) {
            if (proInCart.get(i).getQuant() > productRepository.getProductById(proInCart.get(i).getId()).getStorage()) {
                outOfStock.add(proInCart.get(i));
                redirect = false;
            }
        }

        if (redirect) {
            url = "redirect:/varukorg/checkout.html";
            sendErrorMessageIfEmptyStorage = false;
        } else {
            sendErrorMessageIfEmptyStorage = true;
            url = "redirect:/varukorg/groceryCart.html";
        }

        return url;
    }

    /**
     * Simple redirect to right url.
     *
     * @return
     */

    @GetMapping("/direct")
    public String direction() {

        return url;
    }

}


