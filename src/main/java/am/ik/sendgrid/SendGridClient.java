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

import java.util.function.Function;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import am.ik.sendgrid.sendmail.Mail;
import am.ik.sendgrid.util.JsonCodec;
import io.netty.buffer.ByteBuf;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.HttpClient;
import reactor.ipc.netty.http.HttpClientRequest;
import reactor.ipc.netty.http.HttpClientResponse;
import reactor.ipc.netty.http.HttpOutbound;

public class SendGridClient {
	final String host;
	final String version;
	String apiKey;
	ObjectMapper objectMapper;
	HttpClient httpClient;

	SendGridClient(String host, String version) {
		this.host = host;
		this.version = version;
	}

	public Mono<JsonNode> getAlerts() {
		return this.doGet("getAlerts", JsonNode.class);
	}

	public Mono<JsonNode> getUserProfile() {
		return this.doGet("user/profile", JsonNode.class);
	}

	public Mono<Void> postMailSend(Mail mail) {
		return this.doPost("mail/send", mail, Void.class);
	}

	public Mono<JsonNode> apiKeys() {
		return this.doGet("api_keys", JsonNode.class);
	}

	private <T> Mono<T> doGet(String api, Class<T> responseType) {
		return this.httpClient.get(this.url(api), o -> this.addHeaders(o).sendHeaders())
				.compose(this.deserializedResponse(responseType));
	}

	private <T> Mono<T> doPost(String api, Object request, Class<T> responseType) {
		return this.httpClient
				.post(this.url(api),
						o -> this.addHeaders(o).send(this.serializedRequest(o, request)))
				.compose(this.deserializedResponse(responseType));
	}

	private String url(String api) {
		return "https://" + this.host + "/" + this.version + "/" + api;
	}

	private <T> Function<Mono<HttpClientResponse>, Mono<T>> deserializedResponse(
			Class<T> responseType) {
		return inbound -> inbound.then(i -> i.receive().aggregate().toInputStream())
				.map(JsonCodec.decode(this.objectMapper, responseType));
	}

	private Mono<ByteBuf> serializedRequest(HttpClientRequest outbound, Object request) {
		return Mono.just(request)
				.filter(req -> this.objectMapper.canSerialize(req.getClass()))
				.map(JsonCodec.encode(this.objectMapper, outbound));
	}

	private HttpOutbound addHeaders(HttpClientRequest outbound) {
		return outbound.addHeader("Authorization", "Bearer " + this.apiKey)
				.addHeader("Accept", "application/json");
	}
}
