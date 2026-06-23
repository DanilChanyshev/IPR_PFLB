import com.google.inject.Guice;
import com.google.inject.Injector;
import config.RestServiceConfig;
import module.ApiModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseApiTest {

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
