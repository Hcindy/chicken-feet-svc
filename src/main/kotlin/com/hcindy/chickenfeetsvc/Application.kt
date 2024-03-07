package com.hcindy.chickenfeetsvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.ApplicationPidFileWriter
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	try {
		runApplication<Application>(*args) {
			addListeners(ApplicationPidFileWriter()) // 配合application.yml中的spring.pid.file在启动时生成pid文件
		}
	} catch (e: Exception) {
		exitProcess(0)
	}
}
