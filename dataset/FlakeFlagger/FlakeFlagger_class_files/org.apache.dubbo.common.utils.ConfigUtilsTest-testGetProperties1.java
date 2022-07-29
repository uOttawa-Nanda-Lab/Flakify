/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.common.utils;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.threadpool.ThreadPool;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ConfigUtilsTest {
    @Before
    public void setUp() throws Exception {
        ConfigUtils.setProperties(null);
    }

    @Test public void testGetProperties1() throws Exception{try {System.setProperty(Constants.DUBBO_PROPERTIES_KEY,"properties.load");Properties p=ConfigUtils.getProperties();assertThat((String)p.get("a"),equalTo("12"));assertThat((String)p.get("b"),equalTo("34"));assertThat((String)p.get("c"),equalTo("56"));}  finally {System.clearProperty(Constants.DUBBO_PROPERTIES_KEY);}}
}