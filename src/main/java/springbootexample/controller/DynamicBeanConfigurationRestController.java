package springbootexample.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springbootexample.logic.dbean.ClientRouter;

@RestController
@Profile("dynamic-bean-creation")
public class DynamicBeanConfigurationRestController {

    private final ClientRouter router;

    public DynamicBeanConfigurationRestController(final ClientRouter router) {
        this.router = router;
    }

    @RequestMapping("call")
    public String getLastDocumentByName(@RequestParam(value = "name") final String name) {
        return router.route(name);
    }
}
