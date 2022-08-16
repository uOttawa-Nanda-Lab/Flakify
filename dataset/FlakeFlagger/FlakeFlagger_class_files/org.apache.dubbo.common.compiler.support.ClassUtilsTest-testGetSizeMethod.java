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

    @Test public void testGetSizeMethod(){Assert.assertEquals("getLength()",ClassUtils.getSizeMethod(GenericClass3.class));}

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
