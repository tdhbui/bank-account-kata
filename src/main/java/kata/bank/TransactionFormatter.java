package kata.bank;

import java.time.format.DateTimeFormatter;

public class TransactionFormatter {
  public  String format(Transaction transaction) {
return String.format("%s || %s || %d || %d",
    transaction.getType(),
    transaction.getTimestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
    transaction.getAmount(),
    transaction.getBalance());
  }

}
