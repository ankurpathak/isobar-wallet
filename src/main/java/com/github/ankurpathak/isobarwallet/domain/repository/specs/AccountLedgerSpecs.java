package com.github.ankurpathak.isobarwallet.domain.repository.specs;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.ParameterExpression;
import java.time.Instant;

public class AccountLedgerSpecs  {

    private AccountLedgerSpecs(){}

    public static Specification<AccountLedger> accountLedgerOfAccount(Account account) {
        return (root, query, builder) -> builder.equal(root.get(AccountLedger_.account), account);
    }

    public static Specification<AccountLedger> accountLedgerOfAccountInInterval(Account account, Instant start, Instant end) {
        return (root, query, builder) -> builder.and(builder.equal(root.get(AccountLedger_.account), account),
                    builder.greaterThanOrEqualTo(root.get(AccountLedger_.created), start),
                    builder.lessThanOrEqualTo(root.get(AccountLedger_.created), end)
                );
    }
}
