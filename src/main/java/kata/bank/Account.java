package kata.bank;

public class Account {

  private final TransactionRepo transactionRepo;
  private final Clock clock;

  public Account(TransactionRepo transactionRepo, Clock clock) {
    this.transactionRepo = transactionRepo;
    this.clock = clock;
  }

  public void deposit(int amount) {
    transactionRepo.postTransaction(new Transaction(amount, clock.currentTime(), TransactionType.CREDIT));
  }

  public void withdraw(int amount) {

  }

  public void printStatement() {

  }
}
