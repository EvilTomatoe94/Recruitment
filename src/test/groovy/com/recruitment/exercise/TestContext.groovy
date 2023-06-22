package com.recruitment.exercise

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

@EnableAutoConfiguration
@EnableFeignClients("com.recruitment.exercise.**")
@EnableJpaRepositories("com.recruitment.exercise.**")
@ComponentScan(basePackages = [
		"com.recruitment.exercise.***",
		"com.recruitment.exercise.***",
])
@EntityScan("com.recruitment.exercise.infrastructure.**")
class TestContext {

	@Bean
	TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
		new TransactionTemplate(platformTransactionManager)
	}
}
