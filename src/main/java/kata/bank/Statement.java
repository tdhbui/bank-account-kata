package kata.bank;

import java.util.List;

public class Statement {

  private final Console console;

  public Statement(Console console) {
    this.console = console;
  }

  public void printStatement(List<Transaction> transactions) {
    console.print("Operation || Date || Amount || Balance");
  }
}
