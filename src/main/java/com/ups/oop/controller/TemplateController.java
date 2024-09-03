package com.ups.oop.controller;

import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private final PersonService personService;
    private final ClientService clientService;
    private final WorkerService workerService;
    private final ProductService productService;
    private final PaymentMethService paymentMethService;
    private final ReceiptService receiptService;
    private final DetailsService detailsService;
    private final BranchService branchService;
    private final DistributorService distributorService;

    public TemplateController(PersonService personService, ClientService clientService,
                              WorkerService workerService, ProductService productService,
                              PaymentMethService paymentMethService, ReceiptService receiptService,
                              DetailsService detailsService, BranchService branchService,
                              DistributorService distributorService) {
        this.personService = personService;
        this.clientService = clientService;
        this.workerService = workerService;
        this.productService = productService;
        this.paymentMethService = paymentMethService;
        this.receiptService = receiptService;
        this.detailsService = detailsService;
        this.branchService = branchService;
        this.distributorService = distributorService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model) {
        return "template";
    }

    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "person/list";
    }

    @GetMapping("/clients")
    public String getClient(Model model) {
        model.addAttribute("clients", clientService.getClient());
        return "client/list";
    }

    @GetMapping("/workers")
    public String getWorker(Model model) {
        model.addAttribute("workers", workerService.getAllWorker());
        return "worker/list";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("clients", productService.getProduct());
        return "client/list";
    }

    @GetMapping("/branches")
    public String getBranches(Model model) {
        model.addAttribute("branches", branchService.getBranch());
        return "branch/list";
    }

    @GetMapping("/distributors")
    public String getDistributor(Model model) {
        model.addAttribute("distributors", distributorService.getDistributor());
        return "distributors/list";
    }

    @GetMapping("/paymentmethod")
    public String getPaymentMethod(Model model) {
        model.addAttribute("distributors", paymentMethService.getPayment());
        return "paymentmeth/list";
    }

    @GetMapping("/receipts")
    public String getReceipts(Model model) {
        model.addAttribute("receipts", receiptService.getReceipts());
        return "receipt/list";
    }

    @GetMapping("/receipt-details")
    public String getDetails(Model model) {
        model.addAttribute("receiptDetails", detailsService.getDetails());
        return "details/list";
    }
}