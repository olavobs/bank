package com.socialbank.tech.test.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.socialbank.tech.test.model.Account;
import com.socialbank.tech.test.model.AccountStatus;

import java.time.LocalDateTime;

public class AccountFixture implements TemplateLoader {
    public static final String VALID = "VALID";

    @Override
    public void load() {
        Fixture.of(Account.class).addTemplate(VALID, new Rule() {{
            add("id", "1");
            add("name", random("Anderson Parra", "Arthur Hirata"));
            add("status", AccountStatus.INACTIVE);
            add("createdDate", LocalDateTime.now());
            add("amount", (double) 0);
        }});
    }
}
