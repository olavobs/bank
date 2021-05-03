package com.socialbank.tech.test.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.socialbank.tech.test.controller.AccountHistoryConverter;
import com.socialbank.tech.test.controller.request.AccountRequest;
import com.socialbank.tech.test.exception.AccountNotFoundException;
import com.socialbank.tech.test.fixtures.AccountFixture;
import com.socialbank.tech.test.fixtures.AccountRequestFixture;
import com.socialbank.tech.test.model.Account;
import com.socialbank.tech.test.model.AccountStatus;
import com.socialbank.tech.test.repository.AccountRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountHistoryConverter accountHistoryConverter;

    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("com.socialbank.tech.test.fixtures");
    }

    @Test
    public void testCreateAccount() {
        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(AccountRequestFixture.VALID);
        Account account = Fixture.from(Account.class).gimme(AccountFixture.VALID);

        when(accountRepository.save(any())).thenReturn(account);
        accountService.create(accountRequest);
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    public void testCancelAccount() {
        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(AccountRequestFixture.VALID);
        Account account = Fixture.from(Account.class).gimme(AccountFixture.VALID);

        when(accountRepository.findByIdentifier(any())).thenReturn(java.util.Optional.ofNullable(account));
        when(accountRepository.save(any())).thenReturn(account.setStatus(AccountStatus.INACTIVE));
        accountService.cancel(accountRequest.getIdentifier());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    public void testCancelAccountWithAccoountNotFoundError() {
        AccountRequest accountRequest = Fixture.from(AccountRequest.class).gimme(AccountRequestFixture.VALID);
        Account account = Fixture.from(Account.class).gimme(AccountFixture.VALID);
        when(accountRepository.findByIdentifier(any())).thenThrow(new AccountNotFoundException());
        when(accountRepository.save(any())).thenReturn(account.setStatus(AccountStatus.INACTIVE));

        var exception = Assertions.assertThrows(AccountNotFoundException.class, () -> accountService.cancel(accountRequest.getIdentifier()));
        verify(accountRepository, times(1)).save(any());
    }

}
