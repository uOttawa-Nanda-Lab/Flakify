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

package org.springframework.boot.autoconfigure.web;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link DefaultErrorAttributes}.
 *
 * @author Phillip Webb
 */
public class DefaultErrorAttributesTests {

	private DefaultErrorAttributes errorAttributes = new DefaultErrorAttributes();

	private MockHttpServletRequest request = new MockHttpServletRequest();

	private RequestAttributes requestAttributes = new ServletRequestAttributes(
			this.request);

	@Test public void servletError() throws Exception{RuntimeException ex=new RuntimeException("Test");this.request.setAttribute("javax.servlet.error.exception",ex);Map<String, Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);assertThat(this.errorAttributes.getError(this.requestAttributes),sameInstance((Object)ex));assertThat(attributes.get("exception"),equalTo((Object)RuntimeException.class.getName()));assertThat(attributes.get("message"),equalTo((Object)"Test"));}
}
