package com.socialbank.tech.test.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.socialbank.tech.test.controller.request.AccountRequest;

public class AccountRequestFixture implements TemplateLoader {
    public static final String VALID = "VALID";

    @Override
    public void load() {
        Fixture.of(AccountRequest.class).addTemplate(VALID, new Rule() {{
            add("identifier", "12345");
            add("name", random("Anderson Parra", "Arthur Hirata"));
        }});
    }
}
