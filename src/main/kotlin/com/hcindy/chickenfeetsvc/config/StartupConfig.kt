package com.hcindy.chickenfeetsvc.config

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Configuration
import java.nio.charset.Charset

@Configuration
class StartupConfig : ApplicationRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(args: ApplicationArguments) {
        logger.info("startup run begin")

        if (Charset.defaultCharset() != Charsets.UTF_8) {
            logger.warn("-------------------------------WARN-----------------------------------")
            logger.warn("|    default charset isn't UTF-8, chinese log will be messy code     |")
            logger.warn("~~~~~TIP~~~~~append '-Dfile.encoding=UTF-8' when starting~~~~~TIP~~~~~")
        }

        logger.info("startup run end")
    }
}
