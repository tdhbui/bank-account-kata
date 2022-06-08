package kata.bank;

import static org.mockito.Mockito.verify;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatementShould {

  @InjectMocks
  private Statement statement;

  @Mock
  private Console console;

  @Test
  public void print_an_empty_statement() {
    statement.printStatement(Collections.emptyList());
    verify(console).print("Operation || Date || Amount || Balance");
  }

}
