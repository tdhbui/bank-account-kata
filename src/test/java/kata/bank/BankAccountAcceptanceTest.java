package kata.bank;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
    TransactionRepo transactionRepo = new TransactionRepo();
    TransactionFormatter formatter = new TransactionFormatter();
    Statement statement = new Statement(console, formatter);
    when(clock.currentTime())
        .thenReturn(LocalDateTime.of(2022, 1, 1, 0, 0))
        .thenReturn(LocalDateTime.of(2022, 1, 5, 0, 0))
        .thenReturn(LocalDateTime.of(2022, 1, 15, 0, 0));

    Account account = new Account(transactionRepo, clock, statement);
    account.deposit(1000);
    account.withdraw(500);
    account.deposit((2000));
    account.printStatement();
    verify(console).print("Operation || Date || Amount || Balance\n" +
        "CREDIT || 15/01/2022 || 2000 || 3500\n" +
        "DEBIT || 05/01/2022 || 500 || 500\n" +
        "CREDIT || 01/01/2022 || 1000 || 1000");
  }

}
