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

import org.apache.dubbo.common.model.Person;
import org.apache.dubbo.common.model.SerializablePerson;
import org.apache.dubbo.common.model.person.BigPerson;
import org.apache.dubbo.common.model.person.FullAddress;
import org.apache.dubbo.common.model.person.PersonInfo;
import org.apache.dubbo.common.model.person.PersonStatus;
import org.apache.dubbo.common.model.person.Phone;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PojoUtilsTest {

    BigPerson bigPerson;

    {
        bigPerson = new BigPerson();
        bigPerson.setPersonId("id1");
        bigPerson.setLoginName("name1");
        bigPerson.setStatus(PersonStatus.ENABLED);
        bigPerson.setEmail("abc@123.com");
        bigPerson.setPenName("pname");

        ArrayList<Phone> phones = new ArrayList<Phone>();
        Phone phone1 = new Phone("86", "0571", "11223344", "001");
        Phone phone2 = new Phone("86", "0571", "11223344", "002");
        phones.add(phone1);
        phones.add(phone2);

        PersonInfo pi = new PersonInfo();
        pi.setPhones(phones);
        Phone fax = new Phone("86", "0571", "11223344", null);
        pi.setFax(fax);
        FullAddress addr = new FullAddress("CN", "zj", "1234", "Road1", "333444");
        pi.setFullAddress(addr);
        pi.setMobileNo("1122334455");
        pi.setMale(true);
        pi.setDepartment("b2b");
        pi.setHomepageUrl("www.abc.com");
        pi.setJobTitle("dev");
        pi.setName("name2");

        bigPerson.setInfoProfile(pi);
    }

    private static Child newChild(String name, int age) {
        Child result = new Child();
        result.setName(name);
        result.setAge(age);
        return result;
    }

    @Test public void test_total() throws Exception{Object generalize=PojoUtils.generalize(bigPerson);Type gtype=getType("returnBigPersonMethod");Object realize=PojoUtils.realize(generalize,BigPerson.class,gtype);assertEquals(bigPerson,realize);}

    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    public static class Parent {
        public String gender;
        public String email;
        String name;
        int age;
        Child child;
        private String securityEmail;

        public static Parent getNewParent() {
            return new Parent();
        }

        public String getEmail() {
            return this.securityEmail;
        }

        public void setEmail(String email) {
            this.securityEmail = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Child getChild() {
            return child;
        }

        public void setChild(Child child) {
            this.child = child;
        }
    }

    public static class Child {
        public String gender;
        public int age;
        String toy;
        Parent parent;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getToy() {
            return toy;
        }

        public void setToy(String toy) {
            this.toy = toy;
        }

        public Parent getParent() {
            return parent;
        }

        public void setParent(Parent parent) {
            this.parent = parent;
        }
    }

    public static class TestData {
        private Map<String, Child> children = new HashMap<String, Child>();
        private List<Child> list = new ArrayList<Child>();

        public List<Child> getList() {
            return list;
        }

        public void setList(List<Child> list) {
            if (list != null && !list.isEmpty()) {
                this.list.addAll(list);
            }
        }

        public Map<String, Child> getChildren() {
            return children;
        }

        public void setChildren(Map<String, Child> children) {
            if (children != null && !children.isEmpty()) {
                this.children.putAll(children);
            }
        }

        public void addChild(Child child) {
            this.children.put(child.getName(), child);
        }
    }

    public static class InnerPojo<T> {
        private List<T> list;

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
    }

    public static class ListResult<T> {
        List<T> result;

        public List<T> getResult() {
            return result;
        }

        public void setResult(List<T> result) {
            this.result = result;
        }
    }

    interface Message {
        String getContent();

        String getFrom();

        boolean isUrgent();
    }
}