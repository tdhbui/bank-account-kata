package kata.bank;

public class Account {

  private final TransactionRepo transactionRepo;
  private final Clock clock;
  private final Statement statement;

  public Account(TransactionRepo transactionRepo, Clock clock, Statement statement) {
    this.transactionRepo = transactionRepo;
    this.clock = clock;
    this.statement = statement;
  }

  public void deposit(int amount) {
    postTransation(amount, TransactionType.CREDIT);
  }

  public void withdraw(int amount) {
    postTransation(amount, TransactionType.DEBIT);
  }

  private void postTransation(int amount, TransactionType type) {
    int previousBalance = transactionRepo.findAllTransactions().stream()
        .mapToInt(Transaction::getAmount)
        .sum();
    int newBalance = 0;
    if (type.compareTo(TransactionType.CREDIT) == 0) {
      newBalance = previousBalance + amount;
    } else {
      newBalance = previousBalance - amount;
    }
    Transaction transaction = new Transaction(amount, clock.currentTime(), type, newBalance);
    transactionRepo.postTransaction(transaction);
  }
  public void printStatement() {
    statement.printStatement(transactionRepo.findAllTransactions());
  }
}
