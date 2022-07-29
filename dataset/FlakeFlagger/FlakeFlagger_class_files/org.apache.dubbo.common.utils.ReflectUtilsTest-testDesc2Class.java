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
    @Test public void testDesc2Class() throws Exception{assertEquals(void.class,ReflectUtils.desc2class("V"));assertEquals(boolean.class,ReflectUtils.desc2class("Z"));assertEquals(boolean[].class,ReflectUtils.desc2class("[Z"));assertEquals(byte.class,ReflectUtils.desc2class("B"));assertEquals(char.class,ReflectUtils.desc2class("C"));assertEquals(double.class,ReflectUtils.desc2class("D"));assertEquals(float.class,ReflectUtils.desc2class("F"));assertEquals(int.class,ReflectUtils.desc2class("I"));assertEquals(long.class,ReflectUtils.desc2class("J"));assertEquals(short.class,ReflectUtils.desc2class("S"));assertEquals(String.class,ReflectUtils.desc2class("Ljava.lang.String;"));assertEquals(int[][].class,ReflectUtils.desc2class(ReflectUtils.getDesc(int[][].class)));assertEquals(ReflectUtilsTest[].class,ReflectUtils.desc2class(ReflectUtils.getDesc(ReflectUtilsTest[].class)));String desc;Class<?>[] cs;cs=new Class<?>[]{int.class,getClass(),String.class,int[][].class,boolean[].class};desc=ReflectUtils.getDesc(cs);assertSame(cs,ReflectUtils.desc2classArray(desc));cs=new Class<?>[]{};desc=ReflectUtils.getDesc(cs);assertSame(cs,ReflectUtils.desc2classArray(desc));cs=new Class<?>[]{void.class,String[].class,int[][].class,ReflectUtilsTest[][].class};desc=ReflectUtils.getDesc(cs);assertSame(cs,ReflectUtils.desc2classArray(desc));}

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