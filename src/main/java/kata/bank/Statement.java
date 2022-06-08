package kata.bank;

import java.util.Comparator;
import java.util.List;

public class Statement {

  private final Console console;
  private final TransactionFormatter formatter;

  public Statement(Console console, TransactionFormatter formatter) {
    this.console = console;
    this.formatter = formatter;
  }

  public void printStatement(List<Transaction> transactions) {
    StringBuilder statement = new StringBuilder();
    statement.append("Operation || Date || Amount || Balance");

    transactions.stream()
        .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
        .forEach(transaction -> {
      statement.append("\n");
      statement.append(formatter.format(transaction));
    });
    console.print(statement.toString());
  }
}
