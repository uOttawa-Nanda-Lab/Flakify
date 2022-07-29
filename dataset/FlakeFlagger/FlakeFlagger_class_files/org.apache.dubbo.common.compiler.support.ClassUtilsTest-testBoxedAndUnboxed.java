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
package org.apache.dubbo.common.compiler.support;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassUtilsTest {

    @Test public void testBoxedAndUnboxed(){Assert.assertEquals(Boolean.valueOf(true),ClassUtils.boxed(true));Assert.assertEquals(Character.valueOf('0'),ClassUtils.boxed('0'));Assert.assertEquals(Byte.valueOf((byte)0),ClassUtils.boxed((byte)0));Assert.assertEquals(Short.valueOf((short)0),ClassUtils.boxed((short)0));Assert.assertEquals(Integer.valueOf((int)0),ClassUtils.boxed((int)0));Assert.assertEquals(Long.valueOf((long)0),ClassUtils.boxed((long)0));Assert.assertEquals(Float.valueOf((float)0),ClassUtils.boxed((float)0));Assert.assertEquals(Double.valueOf((double)0),ClassUtils.boxed((double)0));Assert.assertEquals(true,ClassUtils.unboxed(Boolean.valueOf(true)));Assert.assertEquals('0',ClassUtils.unboxed(Character.valueOf('0')));Assert.assertEquals((byte)0,ClassUtils.unboxed(Byte.valueOf((byte)0)));Assert.assertEquals((short)0,ClassUtils.unboxed(Short.valueOf((short)0)));Assert.assertEquals(0,ClassUtils.unboxed(Integer.valueOf((int)0)));Assert.assertEquals((long)0,ClassUtils.unboxed(Long.valueOf((long)0)));Assert.assertEquals((float)0,ClassUtils.unboxed(Float.valueOf((float)0)),((float)0));Assert.assertEquals((double)0,ClassUtils.unboxed(Double.valueOf((double)0)),((double)0));}

    private interface GenericInterface<T>{
    }

    private class GenericClass<T> implements GenericInterface<T>{
    }

    private class GenericClass0 implements GenericInterface<String>{
    }

    private class GenericClass1 implements GenericInterface<Collection<String>>{
    }

    private class GenericClass2<T> implements GenericInterface<T[]>{
    }

    private class GenericClass3<T> implements GenericInterface<T[][]>{
        public int getLength(){
            return -1;
        }
    }

    private class PrivateHelloServiceImpl implements HelloService {
        private PrivateHelloServiceImpl() {
        }

        @Override
        public String sayHello() {
            return "Hello world!";
        }
    }

}
