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

import java.util.function.Function;

import io.spring.tobatchornottobatch.domain.Pojo;

/**
 * @author Michael Minella
 */
public class CsvMapper implements Function<String, Pojo> {

	@Override
	public Pojo apply(String input) {
		String[] strings = input.split(",");
		return new Pojo(strings[0], strings[1], Long.parseLong(strings[2]));
	}
}
