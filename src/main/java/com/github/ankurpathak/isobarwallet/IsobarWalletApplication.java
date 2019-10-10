package com.github.ankurpathak.isobarwallet;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class IsobarWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsobarWalletApplication.class, args);
    }

}


@Component
class TestClr implements CommandLineRunner {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
//        accountRepository.save(new Account());
    }
}
