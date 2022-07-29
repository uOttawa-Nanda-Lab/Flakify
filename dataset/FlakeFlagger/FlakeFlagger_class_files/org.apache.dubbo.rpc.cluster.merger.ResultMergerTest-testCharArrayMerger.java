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
package org.apache.dubbo.rpc.cluster.merger;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultMergerTest {
    /**
	 * CharArrayMerger test
	 * @throws Exception
	 */@Test public void testCharArrayMerger() throws Exception{char[] arrayOne="hello".toCharArray();char[] arrayTwo="world".toCharArray();char[] result=MergerFactory.getMerger(char[].class).merge(arrayOne,arrayTwo);Assert.assertEquals(10,result.length);char[] mergedResult="helloworld".toCharArray();for (int i=0;i < mergedResult.length;i++){Assert.assertEquals(mergedResult[i],result[i]);}}
}
