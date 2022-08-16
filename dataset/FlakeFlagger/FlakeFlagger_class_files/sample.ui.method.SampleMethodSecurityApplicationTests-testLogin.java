/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.ui.method;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Basic integration tests for demo application.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleMethodSecurityApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class SampleMethodSecurityApplicationTests {

	@Value("${local.server.port}")
	private int port;

	@Test public void testLogin() throws Exception{HttpHeaders headers=new HttpHeaders();headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));MultiValueMap<String, String> form=new LinkedMultiValueMap<String, String>();form.set("username","admin");form.set("password","admin");getCsrf(form,headers);ResponseEntity<String> entity=new TestRestTemplate().exchange("http://localhost:" + this.port + "/login",HttpMethod.POST,new HttpEntity<MultiValueMap<String, String>>(form,headers),String.class);assertEquals(HttpStatus.FOUND,entity.getStatusCode());assertEquals("http://localhost:" + this.port + "/",entity.getHeaders().getLocation().toString());}

	private void getCsrf(MultiValueMap<String, String> form, HttpHeaders headers) {
		ResponseEntity<String> page = new TestRestTemplate().getForEntity(
				"http://localhost:" + this.port + "/login", String.class);
		String cookie = page.getHeaders().getFirst("Set-Cookie");
		headers.set("Cookie", cookie);
		String body = page.getBody();
		Matcher matcher = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*")
				.matcher(body);
		matcher.find();
		form.set("_csrf", matcher.group(1));
	}

}
