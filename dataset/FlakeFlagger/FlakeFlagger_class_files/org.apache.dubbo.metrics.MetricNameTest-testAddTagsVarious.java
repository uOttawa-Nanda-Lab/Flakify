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
package org.apache.dubbo.metrics;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class MetricNameTest {

    @Test public void testAddTagsVarious(){final Map<String, String> refTags=new HashMap<String, String>();refTags.put("foo","bar");final MetricName test=MetricName.EMPTY.tag("foo","bar");final MetricName test2=MetricName.EMPTY.tag(refTags);Assert.assertEquals(test,new MetricName(null,refTags));Assert.assertEquals(test.getTags(),refTags);Assert.assertEquals(test2,new MetricName(null,refTags));Assert.assertEquals(test2.getTags(),refTags);}
}
