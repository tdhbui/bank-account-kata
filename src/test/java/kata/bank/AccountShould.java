package kata.bank;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountShould {

  @Mock
  TransactionRepo transactionRepo;
  @Mock
  Clock clock;

  @Test
  public void post_deposit() {
    int amount = 400;
    LocalDateTime timestamp = LocalDateTime.now();
    when(clock.currentTime()).thenReturn(timestamp);
    Account account = new Account(transactionRepo, clock);
    account.deposit(amount);

    Transaction depositTransaction = new Transaction(amount, timestamp, TransactionType.CREDIT);
    verify(transactionRepo).postTransaction(depositTransaction);
  }

  @Test
  public void post_withdrawal() {
    int amount = 200;
    LocalDateTime timestamp = LocalDateTime.now();
    when(clock.currentTime()).thenReturn(timestamp);
    Account account = new Account(transactionRepo, clock);
    account.withdraw(amount);

    Transaction withdrawalTransaction = new Transaction(amount, timestamp, TransactionType.DEBIT);
    verify(transactionRepo).postTransaction(withdrawalTransaction);
  }
}
