package kata.bank;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;
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
  @Mock
  Statement statement;

  @Test
  public void post_deposit() {
    int amount = 400;
    LocalDateTime timestamp = LocalDateTime.now();
    when(clock.currentTime()).thenReturn(timestamp);
    Account account = new Account(transactionRepo, clock, statement);
    account.deposit(amount);

    Transaction depositTransaction = new Transaction(amount, timestamp, TransactionType.CREDIT);
    verify(transactionRepo).postTransaction(depositTransaction);
  }

  @Test
  public void post_withdrawal() {
    int amount = 200;
    LocalDateTime timestamp = LocalDateTime.now();
    when(clock.currentTime()).thenReturn(timestamp);
    Account account = new Account(transactionRepo, clock, statement);
    account.withdraw(amount);

    Transaction withdrawalTransaction = new Transaction(amount, timestamp, TransactionType.DEBIT);
    verify(transactionRepo).postTransaction(withdrawalTransaction);
  }

  @Test
  public void print_a_statement() {
    Account account = new Account(transactionRepo, clock, statement);
    List<Transaction> transactions = asList(createTransaction(500, TransactionType.CREDIT),
        createTransaction(100, TransactionType.DEBIT));
    when(transactionRepo.findAllTransactions()).thenReturn(transactions);
    account.printStatement();
    verify(statement).printStatement(transactions);
  }

  private Transaction createTransaction(int amount, TransactionType type) {
    return new Transaction(amount, LocalDateTime.now(), type);
  }
}
