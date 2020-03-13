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
package io.spring.tobatchornottobatch.domain;

import org.springframework.batch.item.ItemCountAware;

/**
 * @author Michael Minella
 */
public class Pojo implements ItemCountAware {

	private String one;

	private String two;

	private long three;

	private int count;

	public Pojo() {
	}

	public Pojo(String one, String two, long three) {
		this.one = one;
		this.two = two;
		this.three = three;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public long getThree() {
		return three;
	}

	public void setThree(long three) {
		this.three = three;
	}

	@Override
	public void setItemCount(int count) {
		this.count = count;
	}

	public int getItemCount() {
		return this.count;
	}
}
