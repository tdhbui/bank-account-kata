package kata.bank;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

  private final int amount;
  private final LocalDateTime timestamp;
  private final TransactionType type;

  public Transaction(int amount, LocalDateTime timestamp, TransactionType type) {
    this.amount = amount;
    this.timestamp = timestamp;
    this.type = type;
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
    return amount == that.amount && timestamp.equals(that.timestamp) && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, timestamp, type);
  }
}
