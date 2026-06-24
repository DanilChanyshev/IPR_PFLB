package main.base;

import extension.UiExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@ExtendWith(UiExtension.class)
@Execution(ExecutionMode.CONCURRENT)
public class BaseTest {
}
