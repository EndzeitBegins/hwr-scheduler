package xyz.ottersbach.hwrscheduler.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SpringDatasourceConfiguration::class)
class MultiModuleSpringBootApplication

fun main(args: Array<String>) {
	runApplication<MultiModuleSpringBootApplication>(*args)
}
