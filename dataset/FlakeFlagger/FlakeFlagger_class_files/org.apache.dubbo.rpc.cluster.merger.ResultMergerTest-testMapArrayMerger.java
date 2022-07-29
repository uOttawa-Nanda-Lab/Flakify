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
	 * LongArrayMerger test
	 * @throws Exception
	 */@Test public void testMapArrayMerger() throws Exception{Map<Object, Object> mapOne=new HashMap(){{put("11",222);put("223",11);}};Map<Object, Object> mapTwo=new HashMap(){{put("3333",3232);put("444",2323);}};Map<Object, Object> result=MergerFactory.getMerger(Map.class).merge(mapOne,mapTwo);Assert.assertEquals(4,result.size());Map<Object, Object> mergedResult=new HashMap(){{put("11",222);put("223",11);put("3333",3232);put("444",2323);}};Assert.assertEquals(mergedResult,result);}
}
