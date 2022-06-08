package kata.bank;

import java.util.List;

public class Statement {

  private final Console console;
  private final TransactionFormatter formatter;

  public Statement(Console console, TransactionFormatter formatter) {
    this.console = console;
    this.formatter = formatter;
  }

  public void printStatement(List<Transaction> transactions) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Operation || Date || Amount || Balance");

    transactions.forEach(transaction -> {
      stringBuilder.append("\n");
      stringBuilder.append(formatter.format(transaction));
    });
    console.print(stringBuilder.toString());
  }
}
