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
	 * DoubleArrayMerger test
	 * @throws Exception
	 */@Test public void testDoubleArrayMerger() throws Exception{double[] arrayOne={1.2d,3.5d};double[] arrayTwo={2d,34d};double[] result=MergerFactory.getMerger(double[].class).merge(arrayOne,arrayTwo);Assert.assertEquals(4,result.length);double[] mergedResult={1.2d,3.5d,2d,34d};for (int i=0;i < mergedResult.length;i++){Assert.assertTrue(mergedResult[i] == result[i]);}}
}
