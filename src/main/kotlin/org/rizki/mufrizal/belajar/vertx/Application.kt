package org.rizki.mufrizal.belajar.vertx

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import org.rizki.mufrizal.belajar.vertx.controller.MainController
import org.rizki.mufrizal.belajar.vertx.extension.logger
import org.rizki.mufrizal.belajar.vertx.extension.propertiesConfiguration
import org.rizki.mufrizal.belajar.vertx.extension.retrieveConfig
import org.rizki.mufrizal.belajar.vertx.extension.useLogBack
import org.rizki.mufrizal.belajar.vertx.verticle.MainVerticle

/**
 * Created by rizkimufrizal on 5/20/17.
 */

class Application {
    /**
     * companion object agar bisa di akses ke luar class
     * companion object sama dengan static di java
     */
    companion object {
        /**
         * karena menggunakan companion object maka fun main bersifat private,
         * agar dapat diakses maka digunakan fungsi @JvmStatic
         * Selain @JvmStatic, terdapat fungsi lain yaitu
         * lateinit dan const
         */
        @JvmStatic
        fun main(args: Array<String>) {
            /**
             * load konfigurasi logback
             */
            useLogBack()
            /**
             * inisialisasi logger pada class ini
             */
            val log = logger(Application::class)

            log.info("Inisialisasi Vertx")
            /**
             * inisialisasi object vertex
             */
            val vertex = Vertx.vertx()
            /**
             * load konfigurasi file application-config.properties
             */
            val configurationProperties = propertiesConfiguration("application-config.properties")
            /**
             * konfigurasi file properties ke vertex
             */
            val configuration = vertex.retrieveConfig(configurationProperties).toBlocking().first()
            /**
             * inisialisasi router vertx
             */
            val router = Router.router(vertex)

            log.info("Deploy Main Verticle")
            val mainController = MainController(router)
            val mainVerticle = MainVerticle(mainController)

            /**
             * konfigurasi vertx untuk load konfigurasi diatas
             */
            vertex.deployVerticle(mainVerticle, DeploymentOptions().apply {
                this.config = configuration
            })
            log.info("Application Success Running")
        }
    }
}