package org.rizki.mufrizal.belajar.vertx.controller

import io.vertx.ext.web.Router

/**
 * Created by rizkimufrizal on 5/20/17.
 */
abstract class Controller(val handlers: Router.() -> Unit) {
    abstract val router: Router
    fun create(): Router {
        return router.apply {
            handlers()
        }
    }
}