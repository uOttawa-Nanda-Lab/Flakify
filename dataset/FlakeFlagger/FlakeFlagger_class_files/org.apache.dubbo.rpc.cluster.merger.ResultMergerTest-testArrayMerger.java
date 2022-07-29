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
	 * ArrayMerger test
	 * @throws Exception
	 */@Test public void testArrayMerger() throws Exception{String[] stringArray1={"1","2","3"};String[] stringArray2={"4","5","6"};String[] stringArray3={};Object result=ArrayMerger.INSTANCE.merge(stringArray1,stringArray2,stringArray3);Assert.assertTrue(result.getClass().isArray());Assert.assertEquals(6,Array.getLength(result));Assert.assertTrue(String.class.isInstance(Array.get(result,0)));for (int i=0;i < 6;i++){Assert.assertEquals(String.valueOf(i + 1),Array.get(result,i));}int[] intArray1={1,2,3};int[] intArray2={4,5,6};int[] intArray3={7};result=MergerFactory.getMerger(int[].class).merge(intArray1,intArray2,intArray3);Assert.assertTrue(result.getClass().isArray());Assert.assertEquals(7,Array.getLength(result));Assert.assertTrue(int.class == result.getClass().getComponentType());for (int i=0;i < 7;i++){Assert.assertEquals(i + 1,Array.get(result,i));}}
}
