package org.rizki.mufrizal.belajar.vertx

import org.rizki.mufrizal.belajar.vertx.extension.logger
import org.rizki.mufrizal.belajar.vertx.extension.useLogBack

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

            log.info("Application Success Running")
        }
    }
}