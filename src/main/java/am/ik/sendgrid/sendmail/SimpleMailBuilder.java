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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SimpleMailBuilder {

	private List<EmailAddress> to;
	private EmailAddress from;
	private String subject;
	private List<Content> content;

	private Mail.Builder build() {
		return Mail.builder().personalization(Personalization.builder().to(to).build())
				.from(from).subject(subject).content(content);
	}

	public static SubjectBuilder from(String email) {
		return from(EmailAddress.builder().email(email).build());
	}

	public static SubjectBuilder from(EmailAddress from) {
		SimpleMailBuilder builder = new SimpleMailBuilder();
		builder.from = from;
		return new SubjectBuilder(builder);
	}

	public static class SubjectBuilder {
		private final SimpleMailBuilder builder;

		public SubjectBuilder(SimpleMailBuilder builder) {
			this.builder = builder;
		}

		public ToBuilder subject(String subject) {
			this.builder.subject = subject;
			return new ToBuilder(this.builder);
		}
	}

	public static class ToBuilder {

		private final SimpleMailBuilder builder;

		public ToBuilder(SimpleMailBuilder builder) {
			this.builder = builder;
		}

		public ContentBuilder to(String... email) {
			return to(Stream.of(email).map(x -> EmailAddress.builder().email(x).build())
					.toArray(EmailAddress[]::new));
		}

		public ContentBuilder to(EmailAddress... to) {
			this.builder.to = Arrays.asList(to);
			return new ContentBuilder(builder);
		}

	}

	public static class ContentBuilder {
		private final SimpleMailBuilder builder;

		public ContentBuilder(SimpleMailBuilder builder) {
			this.builder = builder;
		}

		public Mail.Builder plain(String... text) {
			return content("text/plain", text);
		}

		public Mail.Builder html(String... text) {
			return content("text/html", text);
		}

		private Mail.Builder content(String type, String... text) {
			return content(Stream.of(text)
					.map(x -> Content.builder().type(type).value(x).build())
					.toArray(Content[]::new));
		}

		public Mail.Builder content(Content... content) {
			this.builder.content = Arrays.asList(content);
			return this.builder.build();
		}
	}

}
