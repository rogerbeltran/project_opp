package com.ups.oop.Bootstrap;
import com.ups.oop.dto.Person;
import com.ups.oop.entity.*;
import com.ups.oop.repository.*;
import com.ups.oop.service.*;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import java.util.Date;
import java.util.Optional;

@Component
public class BootStrapData  implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;
    private final ProductRepository productRepository;
    private final PaymentMethRepository paymentMethRepository;
    private final ReceiptRepository receiptRepository;
    private final DetailsRepository detailsRepository;
    private final BranchRepository branchRepository;
    private final DistributorRepository distributorRepository;

    public BootStrapData(PersonRepository personRepository, ClientRepository clientRepository,
                         WorkerRepository workerRepository, ProductRepository productRepository,
                         PaymentMethRepository paymentMethRepository, ReceiptRepository receiptRepository,
                         DetailsRepository detailsRepository, BranchRepository branchRepository,
                         DistributorRepository distributorRepository) {

        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
        this.workerRepository = workerRepository;
        this.productRepository = productRepository;
        this.paymentMethRepository = paymentMethRepository;
        this.receiptRepository = receiptRepository;
        this.detailsRepository = detailsRepository;
        this.branchRepository = branchRepository;
        this.distributorRepository = distributorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        PaymentMeth p1 = new PaymentMeth();
        p1.setMethod("Debit Card");

        PaymentMeth p2 = new PaymentMeth();
        p2.setMethod("Cash");

        paymentMethRepository.save(p1);
        paymentMethRepository.save(p2);

        Branch bra1 = new Branch();
        bra1.setBranch_name("Sucursal Sur");

        Branch  bra2 = new Branch();
        bra2.setBranch_name("Sucursal Norte");

        Branch  bra3 = new Branch();
        bra3.setBranch_name("Sucursal Via la costa");

        branchRepository.save(bra1);
        branchRepository.save(bra2);
        branchRepository.save(bra3);


        Worker w1 = new Worker();
        w1.setWorkerCode("W/001");
        w1.setName("Juan");
        w1.setLastName("Diaz");
        w1.setAge(25);

        Worker w2 = new Worker();
        w2.setWorkerCode("W/002");
        w2.setName("Luis");
        w2.setLastName("Pérez");
        w2.setAge(38);

        workerRepository.save(w1);
        workerRepository.save(w2);

        Client c1 = new Client();
        c1.setClientCode("C/001");
        c1.setName("Christian");
        c1.setLastName("Llaguno");
        c1.setAge(21);

        Client c2 = new Client();
        c2.setClientCode("C/002");
        c2.setName("Gustav");
        c2.setLastName("Elijah");
        c2.setAge(21);

        clientRepository.save(c1);
        clientRepository.save(c2);

        Product pr1 = new Product();
        pr1.setProductId("P/001");
        pr1.setName("Whale Shark T-Shirt");
        pr1.setPrice(25.00);

        Product pr2 = new Product();
        pr2.setProductId("P/002");
        pr2.setName("Great White Shark T-shirt");
        pr2.setPrice(20.00);

        Product pr3 = new Product();
        pr3.setProductId("P/003");
        pr3.setName("Great White Cap");
        pr3.setPrice(10.00);

        productRepository.save(pr1);
        productRepository.save(pr2);
        productRepository.save(pr3);

        Distributor dist1 = new Distributor();
        dist1.setName("Tiburón Costero");
        dist1.getProducts().add(pr1);

        Distributor dist2 = new Distributor();
        dist2.setName("Under Armour");
        dist2.getProducts().add(pr2);

        productRepository.save(pr1);
        productRepository.save(pr2);
        distributorRepository.save(dist1);
        distributorRepository.save(dist2);


        Receipt recp1 = new Receipt();
        recp1.setClient(c1);
        recp1.setBranches(bra2);
        recp1.setWorker(w2);
        recp1.setSerial("REC/001");
        recp1.setTotal_price(50);
        recp1.setPaymentMeth(p1);
        recp1.setReceiptDate(new Date());
        receiptRepository.save(recp1);

        Details det1 = new Details();
        det1.setReceipt(recp1);
        det1.setProduct(pr1);
        det1.setReceipt(recp1);
        det1.setQuantity(2);
        detailsRepository.save(det1);

        recp1.getDetailList().add(det1);
        receiptRepository.save(recp1);


        Receipt recp2 = new Receipt();
        recp2.setClient(c2);
        recp2.setBranches(bra3);
        recp2.setWorker(w1);
        recp2.setSerial("REC/002");
        recp2.setTotal_price(20);
        recp2.setPaymentMeth(p2);
        recp2.setReceiptDate(new Date());
        receiptRepository.save(recp2);

        Details det2 = new Details();
        det2.setReceipt(recp2);
        det2.setProduct(pr3);
        det2.setReceipt(recp2);
        det2.setQuantity(2);
        detailsRepository.save(det2);

        recp2.getDetailList().add(det2);
        receiptRepository.save(recp2);

    }
}