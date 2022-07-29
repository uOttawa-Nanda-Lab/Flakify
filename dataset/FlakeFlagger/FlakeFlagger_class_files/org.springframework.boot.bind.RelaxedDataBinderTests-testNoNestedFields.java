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

package org.springframework.boot.bind;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link RelaxedDataBinder}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 */
public class RelaxedDataBinderTests {

	@Rule
	public ExpectedException expected = ExpectedException.none();

	private ConversionService conversionService;

	@Test public void testNoNestedFields() throws Exception{VanillaTarget target=new VanillaTarget();RelaxedDataBinder binder=getBinder(target,"foo");binder.setIgnoreUnknownFields(false);binder.setIgnoreNestedProperties(true);BindingResult result=bind(binder,target,"foo.foo: bar\n" + "foo.value: 123\n" + "foo.nested.bar: spam");assertEquals(123,target.getValue());assertEquals("bar",target.getFoo());assertEquals(0,result.getErrorCount());}

	private void doTestBindCaseInsensitiveEnums(VanillaTarget target) throws Exception {
		BindingResult result = bind(target, "bingo: THIS");
		assertThat(result.getErrorCount(), equalTo(0));
		assertThat(target.getBingo(), equalTo(Bingo.THIS));

		result = bind(target, "bingo: oR");
		assertThat(result.getErrorCount(), equalTo(0));
		assertThat(target.getBingo(), equalTo(Bingo.or));

		result = bind(target, "bingo: that");
		assertThat(result.getErrorCount(), equalTo(0));
		assertThat(target.getBingo(), equalTo(Bingo.THAT));
	}

	private BindingResult bind(Object target, String values) throws Exception {
		return bind(target, values, null);
	}

	private BindingResult bind(DataBinder binder, Object target, String values)
			throws Exception {
		Properties properties = PropertiesLoaderUtils
				.loadProperties(new ByteArrayResource(values.getBytes()));
		binder.bind(new MutablePropertyValues(properties));
		binder.validate();

		return binder.getBindingResult();
	}

	private BindingResult bind(Object target, String values, String namePrefix)
			throws Exception {
		return bind(getBinder(target, namePrefix), target, values);
	}

	private RelaxedDataBinder getBinder(Object target, String namePrefix) {
		RelaxedDataBinder binder = new RelaxedDataBinder(target, namePrefix);
		binder.setIgnoreUnknownFields(false);
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.afterPropertiesSet();
		binder.setValidator(validatorFactoryBean);
		binder.setConversionService(this.conversionService);
		return binder;
	}

	@Documented
	@Target({ ElementType.FIELD })
	@Retention(RUNTIME)
	@Constraint(validatedBy = RequiredKeysValidator.class)
	public @interface RequiredKeys {

		String[] value();

		String message() default "Required fields are not provided for field ''{0}''";

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};

	}

	public static class RequiredKeysValidator implements
			ConstraintValidator<RequiredKeys, Map<String, Object>> {

		private String[] fields;

	}

	public static class TargetWithValidatedMap {

		@RequiredKeys({ "foo", "value" })
		private Map<String, Object> info;

	}

	public static class TargetWithNestedMap {

		private Map<String, Object> nested;

	}

	@SuppressWarnings("rawtypes")
	public static class TargetWithNestedUntypedMap {

		private Map nested;

	}

	public static class TargetWithNestedMapOfString {

		private Map<String, String> nested;

	}

	public static class TargetWithNestedMapOfEnum {

		private Map<Bingo, Object> nested;
	}

	public static class TargetWithNestedMapOfListOfString {

		private Map<String, List<String>> nested;

	}

	public static class TargetWithNestedMapOfListOfBean {

		private Map<String, List<VanillaTarget>> nested;

	}

	public static class TargetWithNestedMapOfBean {

		private Map<String, VanillaTarget> nested;

	}

	public static class TargetWithNestedList {

		private List<String> nested;

	}

	public static class TargetWithReadOnlyNestedList {

		private final List<String> nested = new ArrayList<String>();

	}

	public static class TargetWithReadOnlyDoubleNestedList {

		TargetWithReadOnlyNestedList bean = new TargetWithReadOnlyNestedList();

	}

	public static class TargetWithReadOnlyNestedCollection {

		private final Collection<String> nested = new ArrayList<String>();

	}

	public static class TargetWithNestedSet {

		private Set<String> nested = new LinkedHashSet<String>();

	}

	public static class TargetWithNestedObject {
		private VanillaTarget nested;
	}

	public static class VanillaTarget {

		private String foo;

		private char[] bar;

		private int value;

		private String foo_bar;

		private String fooBaz;

		private Bingo bingo;

		static enum Bingo {
		THIS, or, THAT
	}

	public static class ValidatedTarget {

		@NotNull
		private String foo;

	}
}
