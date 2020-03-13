/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.tobatchornottobatch.integration;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.config.FileReadingMessageSourceFactoryBean;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.jdbc.JdbcMessageHandler;

/**
 * @author Michael Minella
 */
//@Configuration
public class IntegrationConfiguration {

	@Bean
	public IntegrationFlow integrationFlow(FileReadingMessageSource source, JdbcMessageHandler jdbcMessageHandler) {

		return IntegrationFlows.from(source, spec -> spec.poller(Pollers.fixedDelay(500)))
				.split(Files.splitter()
						.applySequence(true))
				.transform(new CsvMapper())
				.handle(jdbcMessageHandler)
				.get();
	}

	@Bean
	public JdbcMessageHandler jdbcMessageHandler(DataSource dataSource) {
		return new JdbcMessageHandler(dataSource,
				"INSERT INTO FOO VALUES (:headers[sequenceNumber], :payload.one, :payload.two, :payload.three)");
	}

	@Bean
	public FileReadingMessageSourceFactoryBean fileReader() {
		FileReadingMessageSourceFactoryBean factoryBean = new FileReadingMessageSourceFactoryBean();

		factoryBean.setDirectory(new File("/Users/mminella/Documents/IntelliJWorkspace/to-batch-or-not-to-batch/source"));

		return factoryBean;
	}
}
