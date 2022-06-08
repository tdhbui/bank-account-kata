package kata.bank;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

  private final int amount;
  private final LocalDateTime timestamp;
  private final TransactionType type;
  private final int balance;
  public Transaction(int amount, LocalDateTime timestamp, TransactionType type, int balance) {
    this.amount = amount;
    this.timestamp = timestamp;
    this.type = type;
    this.balance = balance;
  }

  public int getAmount() {
    return amount;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction) o;
    return amount == that.amount && balance == that.balance && timestamp.equals(that.timestamp)
        && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, timestamp, type, balance);
  }
}
