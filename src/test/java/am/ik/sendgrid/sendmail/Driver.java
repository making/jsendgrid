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

import am.ik.sendgrid.SendGrid;
import am.ik.sendgrid.SendGridClient;

public class Driver {
	public static void main(String[] args) {
		SendGridClient client = SendGrid.apiKey(System.getenv("SENDGRID_API_KEY"))
				.client();
//		Mail mail = SimpleMailBuilder.from("other@example.com").subject("Hello World!!")
//				.to("tmaki@pivotal.io").plain("Hi!!").build();
//		Mono<Void> sent = client.postMailSend(mail);
//		sent.block();
//		client.getAlerts().log().doOnSuccess(x -> System.out.println(x)).block();

		client.getUserProfile().log().doOnSuccess(x -> System.out.println(x)).block();
	}
}
