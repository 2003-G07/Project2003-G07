package com.example.demo1.twilioSendGrid;// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java

import com.example.demo1.models.Customer;
import com.example.demo1.models.OrderDetails;
import com.example.demo1.models.Orders;
import com.example.demo1.models.Product;
import com.example.demo1.repositories.*;
import com.example.demo1.util.Present;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    String sendGridKey;

    public MailService(String sendGridKey) {
        this.sendGridKey = sendGridKey;
    }




    /*public static void main(String[] args) throws IOException {

        Email from = new Email("henke_e_96@hotmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("henke_e_96@hotmail.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch (IOException ex) {

            throw ex;
        }

    }
     */

    public void sendGreeting(Customer customer) throws IOException {

        Email sender = new Email("hakimslivs2@outlook.com");
        Email recipient = new Email(customer.getEmail());
        String subject = "Hej " + customer.getFirstName() +" " + customer.getLastName()+"! Välkommen till Hakims Livs";

        Content content = new Content("text/plain", "Du är nu registrerad kund hos Hakims Livs");
        Mail mail = new Mail(sender, subject, recipient, content);

        SendGrid sg = new SendGrid(sendGridKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            System.out.println("greeting sent");

        } catch (IOException ex) {

            throw ex;
        }

    }
    public void sendOrderConfirmation(long orderId) throws IOException {

        var orders = ordersRepository.findById(orderId).get();
        var orderDetails = orderDetailsRepository.findByOrders(orders);
        var recipient = orders.getCustomer().getEmail();
        List<Product> productList = new ArrayList<>();
        var customer = orders.getCustomer();
        Present present = new Present();


        Email sender = new Email("hakimslivs2@outlook.com");
        Email recipientEmail = new Email(recipient);

        String subject = "Din orderbekräftelse för order: " + orderId;
        String orderConfirmationBody = "Din beställning har tagits emot" + "\n" +
                "produkter: \n";

        for (int i = 0; i < orderDetails.size(); i++) {
            var product = orderDetails.get(i).getProduct();
            orderConfirmationBody += product.getQuant()+ "x " + product.getName() + " " + product.getPrice() + " kr/st " + "\n";
        }

        orderConfirmationBody += "Totalpris: " + orders.getCost() + " kr";

        Content content = new Content("text/plain", orderConfirmationBody);
        Mail mail = new Mail(sender, subject, recipientEmail, content);

        SendGrid sg = new SendGrid(sendGridKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            System.out.println("order confirmation sent");
            System.out.println(subject + "\n" + orderConfirmationBody);


        } catch (IOException ex) {

            throw ex;
        }

    }
}