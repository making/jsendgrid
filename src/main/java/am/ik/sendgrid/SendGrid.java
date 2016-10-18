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
package am.ik.sendgrid;

import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.ipc.netty.http.HttpClient;

public class SendGrid {

	public static Builder apiKey(String apiKey) {
		return new Builder(apiKey);
	}

	public static class Builder {
		private String apiKey;
		private String host = "api.sendgrid.com";
		private String version = "v3";
		private ObjectMapper objectMapper;
		private HttpClient httpClient;

		public Builder(String apiKey) {
			this.apiKey = apiKey;
		}

		public Builder host(String host) {
			this.host = host;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder objectMapper(ObjectMapper objectMapper) {
			this.objectMapper = objectMapper;
			return this;
		}

		public Builder httpClient(HttpClient httpClient) {
			this.httpClient = httpClient;
			return this;
		}

		public SendGridClient client() {
			SendGridClient sendGridClient = new SendGridClient(this.host, this.version);
			sendGridClient.apiKey = this.apiKey;
			sendGridClient.objectMapper = (this.objectMapper == null) ? new ObjectMapper()
					: this.objectMapper;
			sendGridClient.httpClient = (this.httpClient == null) ? HttpClient.create()
					: this.httpClient;
			return sendGridClient;
		}
	}
}
