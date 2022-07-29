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

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ReflectUtilsTest {
    @Test public void testGetName() throws Exception{assertEquals("boolean",ReflectUtils.getName(boolean.class));assertEquals("int[][][]",ReflectUtils.getName(int[][][].class));assertEquals("java.lang.Object[][]",ReflectUtils.getName(Object[][].class));assertEquals("Z",ReflectUtils.getDesc(boolean.class));assertEquals("[[[I",ReflectUtils.getDesc(int[][][].class));assertEquals("[[Ljava/lang/Object;",ReflectUtils.getDesc(Object[][].class));assertEquals("Z",ReflectUtils.name2desc(ReflectUtils.getName(boolean.class)));assertEquals("[[[I",ReflectUtils.name2desc(ReflectUtils.getName(int[][][].class)));assertEquals("[[Ljava/lang/Object;",ReflectUtils.name2desc(ReflectUtils.getName(Object[][].class)));assertEquals("short[]",ReflectUtils.desc2name(ReflectUtils.getDesc(short[].class)));assertEquals("boolean[]",ReflectUtils.desc2name(ReflectUtils.getDesc(boolean[].class)));assertEquals("byte[]",ReflectUtils.desc2name(ReflectUtils.getDesc(byte[].class)));assertEquals("char[]",ReflectUtils.desc2name(ReflectUtils.getDesc(char[].class)));assertEquals("double[]",ReflectUtils.desc2name(ReflectUtils.getDesc(double[].class)));assertEquals("float[]",ReflectUtils.desc2name(ReflectUtils.getDesc(float[].class)));assertEquals("int[]",ReflectUtils.desc2name(ReflectUtils.getDesc(int[].class)));assertEquals("long[]",ReflectUtils.desc2name(ReflectUtils.getDesc(long[].class)));assertEquals("int",ReflectUtils.desc2name(ReflectUtils.getDesc(int.class)));assertEquals("void",ReflectUtils.desc2name(ReflectUtils.getDesc(void.class)));assertEquals("java.lang.Object[][]",ReflectUtils.desc2name(ReflectUtils.getDesc(Object[][].class)));}

    protected void assertSame(Class<?>[] cs1, Class<?>[] cs2) throws Exception {
        assertEquals(cs1.length, cs2.length);
        for (int i = 0; i < cs1.length; i++)
            assertEquals(cs1[i], cs2[i]);
    }

    public static class EmptyClass {
        private EmptyProperty property;
        public boolean set;
        public static String s;
        private transient int i;

        public EmptyProperty getProperty() {
            return property;
        }

        public EmptyProperty getPropertyIndex(int i) {
            return property;
        }

        public static EmptyProperty getProperties() {
            return null;
        }

        public void isProperty() {

        }

        public boolean isSet() {
            return set;
        }

        public void setProperty(EmptyProperty property) {
            this.property = property;
        }

        public void setSet(boolean set) {
            this.set = set;
        }
    }

    public static class EmptyProperty {
    }

    static class TestedClass {
        public void method1(int x) {
        }

        public void overrideMethod(int x) {
        }

        public void overrideMethod(Integer x) {
        }

        public void overrideMethod(String s) {
        }

        public void overrideMethod(String s1, String s2) {
        }
    }


    interface Foo<A, B> {
        A hello(B b);
    }

    static class Foo1 implements Foo<String, Integer> {
        @Override
        public String hello(Integer integer) {
            return null;
        }
    }

    static class Foo2 implements Foo<List<String>, int[]> {
        public Foo2(List<String> list, int[] ints) {
        }

        @Override
        public List<String> hello(int[] ints) {
            return null;
        }
    }

    static class Foo3 implements Foo<Foo1, Foo2> {
        public Foo3(Foo foo) {
        }

        @Override
        public Foo1 hello(Foo2 foo2) {
            return null;
        }
    }


}