package kata.bank;


import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BankAccountAcceptanceTest {

  @Mock
  Clock clock;
  @Mock
  Console console;

  @Test
  void print_statement_showing_all_transactions() {
    Account account = new Account();
    account.deposit(1000);
    account.withdraw(500);
    account.deposit((2000));
    account.printStatement();
    verify(console).print("Operation || Date || Amount || Balance\n" +
        "Credit || 01/01/2022 || 1000 || 1000\n" +
        "Debit  || 05/01/2022 || 500  || 500\n" +
        "Credit || 15/01/2022 || 2000 || 2500\n");
  }

}
