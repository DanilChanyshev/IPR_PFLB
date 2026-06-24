package base;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import config.RestServiceConfig;
import module.ApiModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import utils.DataFactory;

@Execution(ExecutionMode.CONCURRENT)
public abstract class BaseApiTest {

  @Inject
  protected DataFactory dataFactory;

  protected static Injector injector;

  @BeforeAll
  public static void setup() {
    RestServiceConfig.start();
    injector = Guice.createInjector(new ApiModule());
  }

  @BeforeEach
  public void inject() {
    injector.injectMembers(this);
  }
}
