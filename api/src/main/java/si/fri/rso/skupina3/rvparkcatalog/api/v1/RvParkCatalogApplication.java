package si.fri.rso.skupina3.rvparkcatalog.api.v1;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService(value="rv-park-catalog-service", environment="dev", version = "1.0.0")
public class RvParkCatalogApplication extends Application {
}
