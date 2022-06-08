package kata.bank;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepo {

  private final List<Transaction> transactions = new ArrayList<>();

  public void postTransaction(Transaction transaction) {
    transactions.add(transaction);
  }

  public List<Transaction> findAllTransactions() {
    return transactions;
  }
}
