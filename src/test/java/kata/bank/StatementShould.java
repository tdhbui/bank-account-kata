package kata.bank;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
  private Transaction transaction1, transaction2;

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
    when(formatter.format(transaction1)).thenReturn("some formatted transaction");
    statement.printStatement(asList(transaction1));
    verify(console).print("Operation || Date || Amount || Balance\n" +
        "some formatted transaction");
  }

}
