/**
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.tobatchornottobatch.batch;

import javax.sql.DataSource;

import io.spring.tobatchornottobatch.domain.Pojo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @author Michael Minella
 */
@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job(Step step1) {
		return this.jobBuilderFactory.get("job")
				.start(step1)
				.build();
	}

	@Bean
	public Step step1(ItemReader<Pojo> reader, ItemWriter<Pojo> writer) {
		return this.stepBuilderFactory.get("step1")
				.<Pojo, Pojo>chunk(10)
				.reader(reader)
				.writer(writer)
				.build();
	}

	@Bean
	public FlatFileItemReader<Pojo> reader() {
		return new FlatFileItemReaderBuilder<Pojo>()
				.name("reader")
				.resource(new FileSystemResource("/Users/mminella/Documents/IntelliJWorkspace/to-batch-or-not-to-batch/source/input.txt"))
				.delimited()
				.names("one", "two", "three")
				.targetType(Pojo.class)
				.build();
	}

	@Bean
	public JdbcBatchItemWriter<Pojo> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Pojo>()
				.dataSource(dataSource)
				.beanMapped()
				.sql("INSERT INTO FOO VALUES (:itemCount, :one, :two, :three)")
				.build();
	}
}
