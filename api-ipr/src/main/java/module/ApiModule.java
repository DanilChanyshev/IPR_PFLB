package module;

import com.google.inject.AbstractModule;
import services.pet.PetRestService;
import services.pet.PetRestServiceImpl;
import services.store.StoreRestService;
import services.store.StoreRestServiceImpl;
import services.user.UserRestService;
import services.user.UserRestServiceImpl;
import utils.DataFactory;

public class ApiModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(PetRestService.class).to(PetRestServiceImpl.class);
    bind(StoreRestService.class).to(StoreRestServiceImpl.class);
    bind(UserRestService.class).to(UserRestServiceImpl.class);
    bind(DataFactory.class);
  }
}
