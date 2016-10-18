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

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import am.ik.sendgrid.Nullable;

@Value.Immutable
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class _Ganalytics extends AbstractSetting {
	@Nullable
	@JsonProperty("utm_source")
	abstract String utmSource();

	@Nullable
	@JsonProperty("utm_medium")
	abstract String utmMedium();

	@Nullable
	@JsonProperty("utm_term")
	abstract String utmTerm();

	@Nullable
	@JsonProperty("utm_content")
	abstract String utmContent();

	@Nullable
	@JsonProperty("utm_campaign")
	abstract String utmCampaign();
}
