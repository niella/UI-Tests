package com.uitest

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.views.ViewModel
import com.morpheusdata.web.PluginController
import com.morpheusdata.web.Route
import com.morpheusdata.model.Permission
import com.morpheusdata.views.JsonResponse
import com.morpheusdata.views.HTMLResponse
import groovy.util.logging.Slf4j


@Slf4j
class TestController implements PluginController {

    MorpheusContext morpheusContext
    Plugin plugin

    public TestController(Plugin plugin, MorpheusContext morpheusContext) {
        this.plugin = plugin
        this.morpheusContext = morpheusContext
    }

    @Override
    String getCode() {
        return 'testController'
    }

    @Override
    String getName() {
        return 'Test Controller'
    }

    @Override
    MorpheusContext getMorpheus() {
        return morpheusContext
    }

    @Override
    Plugin getPlugin() {
        return plugin
    }

    List<Route> getRoutes() {
        log.error("RETURNING ROUTES")
        [
                Route.build("/testController/example", "example", Permission.build("testController", "full")),
                Route.build("/testController/json", "json", Permission.build("testController", "full"))
        ]
    }

    def example(ViewModel<String> model) {
        println model
        println "user: ${model.user}"
        return HTMLResponse.success("foo: ${model.user.firstName} ${model.user.lastName}")
    }


    def json(ViewModel<Map> model) {
        println model
        model.object.foo = "fizz"
        return JsonResponse.of(model.object)
    }

}
