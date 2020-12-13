package xyz.ottersbach.hwrscheduler.backend

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig(private val springDatasourceConfiguration: SpringDatasourceConfiguration) {

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = springDatasourceConfiguration.url

        return HikariDataSource(config)
    }
}

