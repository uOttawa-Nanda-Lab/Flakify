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

package org.springframework.boot.autoconfigure.thymeleaf;

import java.io.File;
import java.util.Collections;
import java.util.Locale;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ThymeleafAutoConfiguration}.
 *
 * @author Dave Syer
 */
public class ThymeleafAutoConfigurationTests {

	private AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

	@Test public void createLayoutFromConfigClass() throws Exception{AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);MockServletContext servletContext=new MockServletContext();context.setServletContext(servletContext);context.refresh();ThymeleafView view=(ThymeleafView)context.getBean(ThymeleafViewResolver.class).resolveViewName("view",Locale.UK);MockHttpServletResponse response=new MockHttpServletResponse();MockHttpServletRequest request=new MockHttpServletRequest();request.setAttribute(RequestContext.WEB_APPLICATION_CONTEXT_ATTRIBUTE,context);view.render(Collections.singletonMap("foo","bar"),request,response);String result=response.getContentAsString();assertTrue("Wrong result: " + result,result.contains("<title>Content</title>"));assertTrue("Wrong result: " + result,result.contains("<span>bar</span>"));context.close();}

}
