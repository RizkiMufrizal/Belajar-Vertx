package org.rizki.mufrizal.belajar.vertx.controller

import io.vertx.ext.web.Router
import org.rizki.mufrizal.belajar.vertx.extension.logger

/**
 * Created by rizkimufrizal on 5/20/17.
 */
class MainController(override val router: Router) : Controller({
    val log = logger(MainController::class)

    /**
     * fungsinya sama seperti router.get('/') di express js
     * sama seperti @GetMapping("/") di spring MVC
     */
    get("/").handler { context ->
        log.info("GET / from client")
        context.response().statusCode = 200
        context.response().end("Hello Word")
    }
})