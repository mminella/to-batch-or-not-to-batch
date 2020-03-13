package io.spring.tobatchornottobatch.csvtomaptransformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsvToMapTransformerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvToMapTransformerApplication.class, args);
	}

	@Bean
	public Function<String, Map<String, String>> messageParser() {
		return s -> {
			System.out.println(">> Data = " + s);
			Map<String, String> item = new HashMap<>(3);

			String[] strings = s.split(",");

			item.put("one", strings[0]);
			item.put("two", strings[1]);
			item.put("three", strings[2]);
			return item;
		};
	}
}
