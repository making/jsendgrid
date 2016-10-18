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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

import am.ik.sendgrid.Nullable;

@Value.Immutable
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class _Mail {
	abstract List<Personalization> personalizations();

	abstract EmailAddress from();

	@Nullable
	@JsonProperty("reply_to")
	abstract EmailAddress replyTo();

	abstract String subject();

	abstract List<Content> content();

	@Nullable
	abstract List<Attachment> attachments();

	@Nullable
	@JsonProperty("template_id")
	abstract String templateId();

	@Nullable
	abstract Map<String, String> sections();

	@Nullable
	abstract Map<String, String> headers();

	@Nullable
	abstract List<String> categories();

	@Nullable
	@JsonProperty("custom_args")
	abstract Map<String, String> customArgs();

	@Nullable
	@JsonProperty("send_at")
	abstract Integer sendAt();

	@Nullable
	@JsonProperty("batch_id")
	abstract String batchId();

	@Nullable
	abstract Asm asm();

	@Nullable
	@JsonProperty("ip_pool_name")
	abstract String ipPoolName();

	@Nullable
	@JsonProperty("mail_settings")
	abstract MailSettings mailSettings();
}
