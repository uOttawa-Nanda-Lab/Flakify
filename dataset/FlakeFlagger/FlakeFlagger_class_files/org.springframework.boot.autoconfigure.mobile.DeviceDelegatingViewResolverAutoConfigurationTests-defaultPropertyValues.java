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

package org.springframework.boot.autoconfigure.mobile;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverAutoConfiguration.DeviceDelegatingViewResolverConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizerBeanPostProcessor;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MockEmbeddedServletContainerFactory;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.view.AbstractDeviceDelegatingViewResolver;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link DeviceDelegatingViewResolverAutoConfiguration}.
 *
 * @author Roy Clarkson
 */
public class DeviceDelegatingViewResolverAutoConfigurationTests {

	private static final MockEmbeddedServletContainerFactory containerFactory = new MockEmbeddedServletContainerFactory();

	private AnnotationConfigEmbeddedWebApplicationContext context;

	@Test public void defaultPropertyValues() throws Exception{this.context=new AnnotationConfigEmbeddedWebApplicationContext();EnvironmentTestUtils.addEnvironment(this.context,"spring.mobile.devicedelegatingviewresolver.enabled:true");this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class,DeviceDelegatingViewResolverConfiguration.class);this.context.refresh();LiteDeviceDelegatingViewResolver liteDeviceDelegatingViewResolver=this.context.getBean("deviceDelegatingViewResolver",LiteDeviceDelegatingViewResolver.class);Field normalPrefixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"normalPrefix");normalPrefixField.setAccessible(true);String normalPrefix=(String)ReflectionUtils.getField(normalPrefixField,liteDeviceDelegatingViewResolver);assertEquals("",normalPrefix);Field mobilePrefixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"mobilePrefix");mobilePrefixField.setAccessible(true);String mobilePrefix=(String)ReflectionUtils.getField(mobilePrefixField,liteDeviceDelegatingViewResolver);assertEquals("mobile/",mobilePrefix);Field tabletPrefixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"tabletPrefix");tabletPrefixField.setAccessible(true);String tabletPrefix=(String)ReflectionUtils.getField(tabletPrefixField,liteDeviceDelegatingViewResolver);assertEquals("tablet/",tabletPrefix);Field normalSuffixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"normalSuffix");normalSuffixField.setAccessible(true);String normalSuffix=(String)ReflectionUtils.getField(normalSuffixField,liteDeviceDelegatingViewResolver);assertEquals("",normalSuffix);Field mobileSuffixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"mobileSuffix");mobileSuffixField.setAccessible(true);String mobileSuffix=(String)ReflectionUtils.getField(mobileSuffixField,liteDeviceDelegatingViewResolver);assertEquals("",mobileSuffix);Field tabletSuffixField=ReflectionUtils.findField(LiteDeviceDelegatingViewResolver.class,"tabletSuffix");tabletSuffixField.setAccessible(true);String tabletSuffix=(String)ReflectionUtils.getField(tabletSuffixField,liteDeviceDelegatingViewResolver);assertEquals("",tabletSuffix);}

	@Configuration
	protected static class Config {

		@Bean
		public EmbeddedServletContainerFactory containerFactory() {
			return containerFactory;
		}

		@Bean
		public EmbeddedServletContainerCustomizerBeanPostProcessor embeddedServletContainerCustomizerBeanPostProcessor() {
			return new EmbeddedServletContainerCustomizerBeanPostProcessor();
		}

	}

}
