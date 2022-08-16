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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport.ConditionAndOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport.ConditionAndOutcomes;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Import;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ConditionEvaluationReport}.
 *
 * @author Greg Turnquist
 * @author Phillip Webb
 */
public class AutoConfigurationReportTests {

	private DefaultListableBeanFactory beanFactory;

	private ConditionEvaluationReport report;

	@Mock
	private Condition condition1;

	@Mock
	private Condition condition2;

	@Mock
	private Condition condition3;

	private ConditionOutcome outcome1;

	private ConditionOutcome outcome2;

	private ConditionOutcome outcome3;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.beanFactory = new DefaultListableBeanFactory();
		this.report = ConditionEvaluationReport.get(this.beanFactory);
	}

	@Test public void recordConditionEvaluations() throws Exception{this.outcome1=new ConditionOutcome(false,"m1");this.outcome2=new ConditionOutcome(false,"m2");this.outcome3=new ConditionOutcome(false,"m3");this.report.recordConditionEvaluation("a",this.condition1,this.outcome1);this.report.recordConditionEvaluation("a",this.condition2,this.outcome2);this.report.recordConditionEvaluation("b",this.condition3,this.outcome3);Map<String, ConditionAndOutcomes> map=this.report.getConditionAndOutcomesBySource();assertThat(map.size(),equalTo(2));Iterator<ConditionAndOutcome> iterator=map.get("a").iterator();ConditionAndOutcome conditionAndOutcome=iterator.next();assertThat(conditionAndOutcome.getCondition(),equalTo(this.condition1));assertThat(conditionAndOutcome.getOutcome(),equalTo(this.outcome1));conditionAndOutcome=iterator.next();assertThat(conditionAndOutcome.getCondition(),equalTo(this.condition2));assertThat(conditionAndOutcome.getOutcome(),equalTo(this.outcome2));assertThat(iterator.hasNext(),equalTo(false));iterator=map.get("b").iterator();conditionAndOutcome=iterator.next();assertThat(conditionAndOutcome.getCondition(),equalTo(this.condition3));assertThat(conditionAndOutcome.getOutcome(),equalTo(this.outcome3));assertThat(iterator.hasNext(),equalTo(false));}

	private void prepareMatches(boolean m1, boolean m2, boolean m3) {
		this.outcome1 = new ConditionOutcome(m1, "m1");
		this.outcome2 = new ConditionOutcome(m2, "m2");
		this.outcome3 = new ConditionOutcome(m3, "m3");
		this.report.recordConditionEvaluation("a", this.condition1, this.outcome1);
		this.report.recordConditionEvaluation("a", this.condition2, this.outcome2);
		this.report.recordConditionEvaluation("a", this.condition3, this.outcome3);
	}

	private int getNumberOfOutcomes(ConditionAndOutcomes outcomes) {
		Iterator<ConditionAndOutcome> iterator = outcomes.iterator();
		int numberOfOutcomesAdded = 0;
		while (iterator.hasNext()) {
			numberOfOutcomesAdded++;
			iterator.next();
		}
		return numberOfOutcomesAdded;
	}

	@Configurable
	@Import(WebMvcAutoConfiguration.class)
	static class Config {

	}

	@Configurable
	@Import(MultipartAutoConfiguration.class)
	static class DuplicateConfig {

	}

}
