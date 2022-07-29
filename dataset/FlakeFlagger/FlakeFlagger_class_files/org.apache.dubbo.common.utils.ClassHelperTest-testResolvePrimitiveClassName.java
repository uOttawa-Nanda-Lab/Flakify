/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.common.utils;

import org.junit.Test;
import org.mockito.Mockito;

import static org.apache.dubbo.common.utils.ClassHelper.forName;
import static org.apache.dubbo.common.utils.ClassHelper.getCallerClassLoader;
import static org.apache.dubbo.common.utils.ClassHelper.getClassLoader;
import static org.apache.dubbo.common.utils.ClassHelper.resolvePrimitiveClassName;
import static org.apache.dubbo.common.utils.ClassHelper.toShortString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class ClassHelperTest {
    @Test public void testResolvePrimitiveClassName() throws Exception{assertThat(resolvePrimitiveClassName("boolean") == boolean.class,is(true));assertThat(resolvePrimitiveClassName("byte") == byte.class,is(true));assertThat(resolvePrimitiveClassName("char") == char.class,is(true));assertThat(resolvePrimitiveClassName("double") == double.class,is(true));assertThat(resolvePrimitiveClassName("float") == float.class,is(true));assertThat(resolvePrimitiveClassName("int") == int.class,is(true));assertThat(resolvePrimitiveClassName("long") == long.class,is(true));assertThat(resolvePrimitiveClassName("short") == short.class,is(true));assertThat(resolvePrimitiveClassName("[Z") == boolean[].class,is(true));assertThat(resolvePrimitiveClassName("[B") == byte[].class,is(true));assertThat(resolvePrimitiveClassName("[C") == char[].class,is(true));assertThat(resolvePrimitiveClassName("[D") == double[].class,is(true));assertThat(resolvePrimitiveClassName("[F") == float[].class,is(true));assertThat(resolvePrimitiveClassName("[I") == int[].class,is(true));assertThat(resolvePrimitiveClassName("[J") == long[].class,is(true));assertThat(resolvePrimitiveClassName("[S") == short[].class,is(true));}
}
