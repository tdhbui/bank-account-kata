package kata.bank;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatementShould {
  @Mock
  private Console console;

  @Mock
  private TransactionFormatter formatter;

  @Mock
  private Transaction transaction, laterTransaction;

  @InjectMocks
  private Statement statement;



  @Test
  public void print_an_empty_statement() {
    statement.printStatement(Collections.emptyList());
    verify(formatter, never()).format(any());
    verify(console).print("Operation || Date || Amount || Balance");
  }

  @Test
  public void print_a_formatted_transaction() {
    when(formatter.format(transaction)).thenReturn("some formatted transaction");
    statement.printStatement(asList(transaction));
    verify(console).print("Operation || Date || Amount || Balance\n" +
        "some formatted transaction");
  }

  @Test
  public void print_a_list_of_transactions_in_reversed_chronological_order() {
    LocalDateTime timestamp = LocalDateTime.now();
    when(transaction.getTimestamp()).thenReturn(timestamp);
    when(laterTransaction.getTimestamp()).thenReturn(timestamp.plusSeconds(5));
    doReturn("some formatted later transaction").when(formatter).format(laterTransaction);
    doReturn("some formatted earlier transaction").when(formatter).format(transaction);

    statement.printStatement(asList(transaction, laterTransaction));
    verify(console).print("Operation || Date || Amount || Balance\n" +
        "some formatted later transaction\n" +
        "some formatted earlier transaction");
  }


}
