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

package org.springframework.boot.bind;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.boot.bind.RelaxedDataBinderTests.TargetWithNestedObject;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dave Syer
 */
public class BindingPreparationTests {

	@Test public void testAutoGrowNewNestedMapOfMaps() throws Exception{TargetWithNestedMap target=new TargetWithNestedMap();BeanWrapperImpl wrapper=new BeanWrapperImpl(target);wrapper.setAutoGrowNestedPaths(true);RelaxedDataBinder binder=new RelaxedDataBinder(target);String result=binder.normalizePath(wrapper,"nested[foo][bar]");assertNotNull(wrapper.getPropertyValue("nested"));assertEquals("nested[foo][bar]",result);assertNotNull(wrapper.getPropertyValue("nested[foo][bar]"));}

	public static class TargetWithNestedMap {
		private Map<String, Object> nested;

		public Map<String, Object> getNested() {
			return this.nested;
		}

		public void setNested(Map<String, Object> nested) {
			this.nested = nested;
		}
	}

	public static class TargetWithNestedMapOfListOfString {
		private Map<String, List<String>> nested;

		public Map<String, List<String>> getNested() {
			return this.nested;
		}

		public void setNested(Map<String, List<String>> nested) {
			this.nested = nested;
		}
	}

	public static class TargetWithNestedListOfMaps {
		private List<Map<String, String>> nested;

		public List<Map<String, String>> getNested() {
			return this.nested;
		}

		public void setNested(List<Map<String, String>> nested) {
			this.nested = nested;
		}
	}

	public static class TargetWithNestedListOfLists {
		private List<List<String>> nested;

		public List<List<String>> getNested() {
			return this.nested;
		}

		public void setNested(List<List<String>> nested) {
			this.nested = nested;
		}
	}

	public static class TargetWithNestedMapOfBean {
		private Map<String, VanillaTarget> nested;

		public Map<String, VanillaTarget> getNested() {
			return this.nested;
		}

		public void setNested(Map<String, VanillaTarget> nested) {
			this.nested = nested;
		}
	}

	public static class VanillaTarget {

		private String foo;

		public String getFoo() {
			return this.foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}
	}
}
