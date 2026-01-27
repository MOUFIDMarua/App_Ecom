package org.moufid.customerservice;

import org.moufid.customerservice.config.CustomerConfigParams;
import org.moufid.customerservice.entities.Customer;
import org.moufid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository ){
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("Maroua").email("marouaaamoufid@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Sara").email("sebtisara@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Abderrahmane").email("hadliabdo@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Khaled").email("khaledechdeigui@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Aya").email("ayamoufid@gmail.com")
                    .build());
            customerRepository.findAll().forEach(c->{
                System.out.println("======================");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("=======================");
            });
        };
    }

}
