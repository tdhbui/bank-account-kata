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
    transactionRepo.postTransaction(new Transaction(amount, clock.currentTime(), TransactionType.CREDIT));
  }

  public void withdraw(int amount) {
    transactionRepo.postTransaction(new Transaction(amount, clock.currentTime(), TransactionType.DEBIT));
  }

  public void printStatement() {
    statement.printStatement(transactionRepo.findAllTransactions());
  }
}
