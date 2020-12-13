package xyz.ottersbach.hwrscheduler.backend

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.datasource")
data class SpringDatasourceConfiguration(
    val url: String
)