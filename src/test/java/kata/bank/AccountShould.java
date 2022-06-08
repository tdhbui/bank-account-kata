package kata.bank;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountShould {

  @Mock
  TransactionRepo transactionRepo;
  @Mock
  Clock clock;
  @Mock
  Statement statement;
  @InjectMocks
  Account account;

  @Test
  public void post_deposit() {
    int amount = 400;
    LocalDateTime timestamp = LocalDateTime.now();
    when(clock.currentTime()).thenReturn(timestamp);
    account.deposit(amount);

    Transaction depositTransaction = new Transaction(amount, timestamp, TransactionType.CREDIT,
        amount);
    verify(transactionRepo).postTransaction(depositTransaction);
  }

  @Test
  public void post_withdrawal() {
    int amount = 200;
    LocalDateTime timestamp = LocalDateTime.now();
    when(clock.currentTime()).thenReturn(timestamp);
    account.withdraw(amount);

    Transaction withdrawalTransaction = new Transaction(amount, timestamp, TransactionType.DEBIT,
        -amount);
    verify(transactionRepo).postTransaction(withdrawalTransaction);
  }

  @Test
  public void print_a_statement() {
    List<Transaction> transactions = asList(createTransaction(500, TransactionType.CREDIT),
        createTransaction(100, TransactionType.DEBIT));
    when(transactionRepo.findAllTransactions()).thenReturn(transactions);
    account.printStatement();
    verify(statement).printStatement(transactions);
  }

  @Test
  public void calculate_a_running_balance() {
    LocalDateTime timestamp = LocalDateTime.now();
    when(clock.currentTime()).thenReturn(timestamp);

    Transaction transaction1 = new Transaction(100, timestamp, TransactionType.CREDIT, 100);
    Transaction transaction2 = new Transaction(75, timestamp, TransactionType.DEBIT, 25);

    when(transactionRepo.findAllTransactions())
        .thenReturn(Collections.emptyList())
        .thenReturn(asList(transaction1));

    account.deposit(100);
    account.withdraw(75);

    verify(transactionRepo).postTransaction(transaction1);
    verify(transactionRepo).postTransaction(transaction2);
  }

  private Transaction createTransaction(int amount, TransactionType type) {
    return new Transaction(amount, LocalDateTime.now(), type, 0);
  }
}
