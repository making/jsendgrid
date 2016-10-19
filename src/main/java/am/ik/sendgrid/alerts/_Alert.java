package am.ik.sendgrid.alerts;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import am.ik.sendgrid.Nullable;

@Value.Immutable
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class _Alert {
	@Nullable
	abstract Long id();

	@Nullable
	abstract String type();

	@JsonProperty("email_to")
	abstract String emailTo();

	@Nullable
	abstract Integer percentage();

	@Nullable
	abstract String frequency();

	@Nullable
	@JsonProperty("created_at")
	abstract Long createdAt();

	@Nullable
	@JsonProperty("updated_at")
	abstract Long updatedAt();
}
