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
				"{\"personalizations\":[{\"to\":[{\"email\":\"makingx@example.com\"}]}],\"from\":{\"email\":\"other@example.com\"},\"subject\":\"Hello World!!\",\"content\":[{\"type\":\"text/plain\",\"value\":\"Hi!!\"}]}"));
	}

	@Test
	public void deserialize() throws Exception {
		String json = "{\"personalizations\":[{\"to\":[{\"email\":\"john@example.com\"}]}],\"from\":{\"email\":\"from_address@example.com\"},\"subject\":\"Hello, World!\",\"content\":[{\"type\":\"text/plain\",\"value\":\"Hello, World!\"}]}";
		Mail mail = objectMapper.readValue(json, Mail.class);
		assertThat(mail.from().email(), is("from_address@example.com"));
		assertThat(mail.subject(), is("Hello, World!"));
		assertThat(mail.personalizations().get(0).to().get(0).email(),
				is("john@example.com"));
		assertThat(mail.content().get(0).type(), is("text/plain"));
		assertThat(mail.content().get(0).value(), is("Hello, World!"));
	}
}