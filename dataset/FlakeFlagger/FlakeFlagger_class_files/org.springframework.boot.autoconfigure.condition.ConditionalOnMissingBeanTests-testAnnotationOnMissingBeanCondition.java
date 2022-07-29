/*
 * Copyright 2012-2013 the original author or authors.
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

package org.springframework.boot.autoconfigure.condition;

import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link ConditionalOnMissingBean}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Jakub Kubrynski
 */
@SuppressWarnings("resource")
public class ConditionalOnMissingBeanTests {

	private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

	@Test public void testAnnotationOnMissingBeanCondition(){this.context.register(FooConfiguration.class,OnAnnotationConfiguration.class);this.context.refresh();assertFalse(this.context.containsBean("bar"));assertEquals("foo",this.context.getBean("foo"));}

	@Configuration
	@ConditionalOnMissingBean(name = "foo")
	protected static class OnBeanNameConfiguration {
		@Bean
		public String bar() {
			return "bar";
		}
	}

	@Configuration
	protected static class FactoryBeanConfiguration {
		@Bean
		public FactoryBean<ExampleBean> exampleBeanFactoryBean() {
			return new ExampleFactoryBean("foo");
		}
	}

	@Configuration
	protected static class ConcreteFactoryBeanConfiguration {
		@Bean
		public ExampleFactoryBean exampleBeanFactoryBean() {
			return new ExampleFactoryBean("foo");
		}
	}

	@Configuration
	protected static class UnhelpfulFactoryBeanConfiguration {
		@Bean
		@SuppressWarnings("rawtypes")
		public FactoryBean exampleBeanFactoryBean() {
			return new ExampleFactoryBean("foo");
		}
	}

	@Configuration
	@Import(NonspecificFactoryBeanRegistrar.class)
	protected static class NonspecificFactoryBeanConfiguration {
	}

	protected static class NonspecificFactoryBeanRegistrar implements
			ImportBeanDefinitionRegistrar {

		@Override
		public void registerBeanDefinitions(AnnotationMetadata meta,
				BeanDefinitionRegistry registry) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder
					.genericBeanDefinition(NonspecificFactoryBean.class);
			builder.addConstructorArgValue("foo");
			builder.getBeanDefinition().setAttribute(
					OnBeanCondition.FACTORY_BEAN_OBJECT_TYPE, ExampleBean.class);
			registry.registerBeanDefinition("exampleBeanFactoryBean",
					builder.getBeanDefinition());
		}

	}

	@Configuration
	@Import(FactoryBeanRegistrar.class)
	protected static class RegisteredFactoryBeanConfiguration {
	}

	protected static class FactoryBeanRegistrar implements ImportBeanDefinitionRegistrar {

		@Override
		public void registerBeanDefinitions(AnnotationMetadata meta,
				BeanDefinitionRegistry registry) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder
					.genericBeanDefinition(ExampleFactoryBean.class);
			builder.addConstructorArgValue("foo");
			registry.registerBeanDefinition("exampleBeanFactoryBean",
					builder.getBeanDefinition());
		}

	}

	@Configuration
	@ImportResource("org/springframework/boot/autoconfigure/condition/factorybean.xml")
	protected static class FactoryBeanXmlConfiguration {
	}

	@Configuration
	protected static class ConditionalOnFactoryBean {
		@Bean
		@ConditionalOnMissingBean(ExampleBean.class)
		public ExampleBean createExampleBean() {
			return new ExampleBean("direct");
		}
	}

	@Configuration
	@ConditionalOnMissingBean(annotation = EnableScheduling.class)
	protected static class OnAnnotationConfiguration {
		@Bean
		public String bar() {
			return "bar";
		}
	}

	@Configuration
	@EnableScheduling
	protected static class FooConfiguration {
		@Bean
		public String foo() {
			return "foo";
		}
	}

	@Configuration
	@ConditionalOnMissingBean(name = "foo")
	protected static class HierarchyConsidered {
		@Bean
		public String bar() {
			return "bar";
		}
	}

	@Configuration
	@ConditionalOnMissingBean(name = "foo", search = SearchStrategy.CURRENT)
	protected static class HierarchyNotConsidered {
		@Bean
		public String bar() {
			return "bar";
		}
	}

	@Configuration
	protected static class ExampleBeanConfiguration {
		@Bean
		public ExampleBean exampleBean() {
			return new ExampleBean("test");
		}
	}

	@Configuration
	protected static class ImpliedOnBeanMethod {

		@Bean
		@ConditionalOnMissingBean
		public ExampleBean exampleBean2() {
			return new ExampleBean("test");
		}

	}

	public static class ExampleBean {

		private String value;

		public ExampleBean(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}

	}

	public static class ExampleFactoryBean implements FactoryBean<ExampleBean> {

		public ExampleFactoryBean(String value) {
			Assert.state(!value.contains("$"));
		}

		@Override
		public ExampleBean getObject() throws Exception {
			return new ExampleBean("fromFactory");
		}

		@Override
		public Class<?> getObjectType() {
			return ExampleBean.class;
		}

		@Override
		public boolean isSingleton() {
			return false;
		}

	}

	public static class NonspecificFactoryBean implements FactoryBean<Object> {

		public NonspecificFactoryBean(String value) {
			Assert.state(!value.contains("$"));
		}

		@Override
		public ExampleBean getObject() throws Exception {
			return new ExampleBean("fromFactory");
		}

		@Override
		public Class<?> getObjectType() {
			return ExampleBean.class;
		}

		@Override
		public boolean isSingleton() {
			return false;
		}

	}
}
