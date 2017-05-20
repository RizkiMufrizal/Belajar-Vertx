package org.rizki.mufrizal.belajar.vertx.verticle

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpServer
import org.rizki.mufrizal.belajar.vertx.controller.MainController
import org.rizki.mufrizal.belajar.vertx.extension.logger
import org.rizki.mufrizal.belajar.vertx.extension.single

/**
 * Created by rizkimufrizal on 5/20/17.
 */
class MainVerticle constructor(val mainController: MainController) : AbstractVerticle() {
    private val log = logger(MainVerticle::class)

    /**
     * fungsi lazy adalah untuk membentuk singleton,
     * artinya konfigurasi hanya dijalankan sekali
     * meskipun selalu dipanggil
     */
    private val config by lazy { config() }

    override fun start(startFuture: Future<Void>) {
        log.info("Initialize Main Verticle...")

        log.info("Initialize Router...")
        val router = mainController.create()

        log.info("Starting HttpServer...")
        /**
         * load HTTP_PORT yang ada di file properties
         */
        val httpServer = single<HttpServer> {
            vertx.createHttpServer()
                    .requestHandler { router.accept(it) }
                    .listen(config.getInteger("HTTP_PORT"), it)
        }

        httpServer.subscribe(
                {
                    log.info("HttpServer started in port ${config.getInteger("HTTP_PORT")}")
                    log.info("Main Verticle Deployed!")
                    startFuture.complete()
                },
                {
                    log.error("Failed to start HttpServer. [${it.message}]", it)
                    log.error("Main Verticle Failed to Deploy!")
                    startFuture.fail(it)
                }
        )
    }
}