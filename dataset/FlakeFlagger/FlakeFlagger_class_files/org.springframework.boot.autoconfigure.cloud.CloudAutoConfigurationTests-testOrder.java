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

package org.springframework.boot.autoconfigure.cloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.autoconfigure.TestAutoConfigurationSorter;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link CloudAutoConfiguration}.
 *
 * @author Phillip Webb
 */
public class CloudAutoConfigurationTests {

	@Test public void testOrder() throws Exception{ResourceLoader loader=new DefaultResourceLoader();TestAutoConfigurationSorter sorter=new TestAutoConfigurationSorter(loader);Collection<String> classNames=new ArrayList<String>();classNames.add(MongoAutoConfiguration.class.getName());classNames.add(DataSourceAutoConfiguration.class.getName());classNames.add(MongoRepositoriesAutoConfiguration.class.getName());classNames.add(JpaRepositoriesAutoConfiguration.class.getName());classNames.add(CloudAutoConfiguration.class.getName());List<String> ordered=sorter.getInPriorityOrder(classNames);assertThat(ordered.get(0),equalTo(CloudAutoConfiguration.class.getName()));}

}
