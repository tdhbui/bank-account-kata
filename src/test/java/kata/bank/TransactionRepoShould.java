package kata.bank;

import static java.util.Arrays.asList;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class TransactionRepoShould {

  private TransactionRepo transactionRepo = new TransactionRepo();

  @Test
  public void be_empty_initially() {
    assert(transactionRepo.findAllTransactions().isEmpty());
  }

  @Test
  public void return_all_posted_transactions() {
    Transaction transaction1 = new Transaction(100, LocalDateTime.now(), TransactionType.CREDIT);
    Transaction transaction2 = new Transaction(50, LocalDateTime.now(), TransactionType.DEBIT);
    transactionRepo.postTransaction(transaction1);
    transactionRepo.postTransaction(transaction2);
    assert(transactionRepo.findAllTransactions()).containsAll(asList(transaction1, transaction2));
  }
}
