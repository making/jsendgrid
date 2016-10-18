/*
 * Copyright (C) 2016 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package am.ik.sendgrid.sendmail;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MailTest {
	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void serialize() throws Exception {
		Mail mail = SimpleMailBuilder.from("other@example.com").subject("Hello World!!")
				.to("makingx@example.com").plain("Hi!!").build();
		assertThat(objectMapper.writeValueAsString(mail), is(
				"{\"personalizations\":[{\"to\":[{\"email\":\"makingx@example.com\"}]}],\"from\":{\"email\":\"other@example.com\"},\"subject\":\"Hello World!!\",\"content\":[{\"type\":\"text/plain\",\"value\":\"Hi!!\"}]}\n"));
	}

	@Test
	public void deserialize() throws Exception {
		Mail conetnt = objectMapper.readValue("{\n" + "  \"personalizations\": [\n"
				+ "    {\n" + "      \"to\": [\n" + "        {\n"
				+ "          \"email\": \"john@example.com\"\n" + "        }\n"
				+ "      ]\n" + "    }\n" + "  ],\n"
				+ "  \"subject\": \"Hello, World!\",\n" + "  \"from\": {\n"
				+ "    \"email\": \"from_address@example.com\"\n" + "  },\n"
				+ "  \"content\": [\n" + "    {\n" + "      \"type\": \"text/plain\",\n"
				+ "      \"value\": \"Hello, World!\"\n" + "    }\n" + "  ]\n" + "}",
				Mail.class);
		assertThat(conetnt.subject(), is("Hello, World!"));
	}

}