package xyz.ottersbach.hwrscheduler.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MultiModuleSpringBootApplication

fun main(args: Array<String>) {
	runApplication<MultiModuleSpringBootApplication>(*args)
}
