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
package org.apache.dubbo.common.beanutil;

import org.apache.dubbo.common.model.person.BigPerson;
import org.apache.dubbo.common.model.person.FullAddress;
import org.apache.dubbo.common.model.person.PersonInfo;
import org.apache.dubbo.common.model.person.PersonStatus;
import org.apache.dubbo.common.model.person.Phone;
import org.apache.dubbo.common.utils.PojoUtilsTest;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JavaBeanSerializeUtilTest {

    @Test public void testSerialize_Array() throws Exception{int[] array={1,2,3,4,5,6,7,8,9};JavaBeanDescriptor descriptor=JavaBeanSerializeUtil.serialize(array,JavaBeanAccessor.METHOD);Assert.assertTrue(descriptor.isArrayType());Assert.assertEquals(int.class.getName(),descriptor.getClassName());for (int i=0;i < array.length;i++){Assert.assertEquals(array[i],((JavaBeanDescriptor)descriptor.getProperty(i)).getPrimitiveProperty());}Integer[] integers=new Integer[]{1,2,3,4,null,null,null};descriptor=JavaBeanSerializeUtil.serialize(integers,JavaBeanAccessor.METHOD);Assert.assertTrue(descriptor.isArrayType());Assert.assertEquals(Integer.class.getName(),descriptor.getClassName());Assert.assertEquals(integers.length,descriptor.propertySize());for (int i=0;i < integers.length;i++){if (integers[i] == null){Assert.assertTrue(integers[i] == descriptor.getProperty(i));} else {Assert.assertEquals(integers[i],((JavaBeanDescriptor)descriptor.getProperty(i)).getPrimitiveProperty());}}int[][] second={{1,2},{3,4}};descriptor=JavaBeanSerializeUtil.serialize(second,JavaBeanAccessor.METHOD);Assert.assertTrue(descriptor.isArrayType());Assert.assertEquals(int[].class.getName(),descriptor.getClassName());for (int i=0;i < second.length;i++){for (int j=0;j < second[i].length;j++){JavaBeanDescriptor item=(((JavaBeanDescriptor)descriptor.getProperty(i)));Assert.assertTrue(item.isArrayType());Assert.assertEquals(int.class.getName(),item.getClassName());Assert.assertEquals(second[i][j],((JavaBeanDescriptor)item.getProperty(j)).getPrimitiveProperty());}}BigPerson[] persons=new BigPerson[]{createBigPerson(),createBigPerson()};descriptor=JavaBeanSerializeUtil.serialize(persons);Assert.assertTrue(descriptor.isArrayType());Assert.assertEquals(BigPerson.class.getName(),descriptor.getClassName());for (int i=0;i < persons.length;i++){assertEqualsBigPerson(persons[i],descriptor.getProperty(i));}}

    static void assertEqualsEnum(Enum<?> expected, Object obj) {
        JavaBeanDescriptor descriptor = (JavaBeanDescriptor) obj;
        Assert.assertTrue(descriptor.isEnumType());
        Assert.assertEquals(expected.getClass().getName(), descriptor.getClassName());
        Assert.assertEquals(expected.name(), descriptor.getEnumPropertyName());
    }

    static void assertEqualsPrimitive(Object expected, Object obj) {
        if (expected == null) {
            return;
        }
        JavaBeanDescriptor descriptor = (JavaBeanDescriptor) obj;
        Assert.assertTrue(descriptor.isPrimitiveType());
        Assert.assertEquals(expected, descriptor.getPrimitiveProperty());
    }

    static void assertEqualsBigPerson(BigPerson person, Object obj) {
        JavaBeanDescriptor descriptor = (JavaBeanDescriptor) obj;
        Assert.assertTrue(descriptor.isBeanType());
        assertEqualsPrimitive(person.getPersonId(), descriptor.getProperty("personId"));
        assertEqualsPrimitive(person.getLoginName(), descriptor.getProperty("loginName"));
        assertEqualsEnum(person.getStatus(), descriptor.getProperty("status"));
        assertEqualsPrimitive(person.getEmail(), descriptor.getProperty("email"));
        assertEqualsPrimitive(person.getPenName(), descriptor.getProperty("penName"));

        JavaBeanDescriptor infoProfile = (JavaBeanDescriptor) descriptor.getProperty("infoProfile");
        Assert.assertTrue(infoProfile.isBeanType());
        JavaBeanDescriptor phones = (JavaBeanDescriptor) infoProfile.getProperty("phones");
        Assert.assertTrue(phones.isCollectionType());
        assertEqualsPhone(person.getInfoProfile().getPhones().get(0), phones.getProperty(0));
        assertEqualsPhone(person.getInfoProfile().getPhones().get(1), phones.getProperty(1));
        assertEqualsPhone(person.getInfoProfile().getFax(), infoProfile.getProperty("fax"));
        assertEqualsFullAddress(person.getInfoProfile().getFullAddress(), infoProfile.getProperty("fullAddress"));
        assertEqualsPrimitive(person.getInfoProfile().getMobileNo(), infoProfile.getProperty("mobileNo"));
        assertEqualsPrimitive(person.getInfoProfile().getName(), infoProfile.getProperty("name"));
        assertEqualsPrimitive(person.getInfoProfile().getDepartment(), infoProfile.getProperty("department"));
        assertEqualsPrimitive(person.getInfoProfile().getJobTitle(), infoProfile.getProperty("jobTitle"));
        assertEqualsPrimitive(person.getInfoProfile().getHomepageUrl(), infoProfile.getProperty("homepageUrl"));
        assertEqualsPrimitive(person.getInfoProfile().isFemale(), infoProfile.getProperty("female"));
        assertEqualsPrimitive(person.getInfoProfile().isMale(), infoProfile.getProperty("male"));
    }

    static void assertEqualsPhone(Phone excpected, Object obj) {
        JavaBeanDescriptor descriptor = (JavaBeanDescriptor) obj;
        Assert.assertTrue(descriptor.isBeanType());
        if (excpected.getArea() != null) {
            assertEqualsPrimitive(excpected.getArea(), descriptor.getProperty("area"));
        }
        if (excpected.getCountry() != null) {
            assertEqualsPrimitive(excpected.getCountry(), descriptor.getProperty("country"));
        }
        if (excpected.getExtensionNumber() != null) {
            assertEqualsPrimitive(excpected.getExtensionNumber(), descriptor.getProperty("extensionNumber"));
        }
        if (excpected.getNumber() != null) {
            assertEqualsPrimitive(excpected.getNumber(), descriptor.getProperty("number"));
        }
    }

    static void assertEqualsFullAddress(FullAddress expected, Object obj) {
        JavaBeanDescriptor descriptor = (JavaBeanDescriptor) obj;
        Assert.assertTrue(descriptor.isBeanType());
        if (expected.getCityId() != null) {
            assertEqualsPrimitive(expected.getCityId(), descriptor.getProperty("cityId"));
        }
        if (expected.getCityName() != null) {
            assertEqualsPrimitive(expected.getCityName(), descriptor.getProperty("cityName"));
        }
        if (expected.getCountryId() != null) {
            assertEqualsPrimitive(expected.getCountryId(), descriptor.getProperty("countryId"));
        }
        if (expected.getCountryName() != null) {
            assertEqualsPrimitive(expected.getCountryName(), descriptor.getProperty("countryName"));
        }
        if (expected.getProvinceName() != null) {
            assertEqualsPrimitive(expected.getProvinceName(), descriptor.getProperty("provinceName"));
        }
        if (expected.getStreetAddress() != null) {
            assertEqualsPrimitive(expected.getStreetAddress(), descriptor.getProperty("streetAddress"));
        }
        if (expected.getZipCode() != null) {
            assertEqualsPrimitive(expected.getZipCode(), descriptor.getProperty("zipCode"));
        }
    }

    static BigPerson createBigPerson() {
        BigPerson bigPerson;
        bigPerson = new BigPerson();
        bigPerson.setPersonId("superman111");
        bigPerson.setLoginName("superman");
        bigPerson.setStatus(PersonStatus.ENABLED);
        bigPerson.setEmail("sm@1.com");
        bigPerson.setPenName("pname");

        ArrayList<Phone> phones = new ArrayList<Phone>();
        Phone phone1 = new Phone("86", "0571", "87654321", "001");
        Phone phone2 = new Phone("86", "0571", "87654322", "002");
        phones.add(phone1);
        phones.add(phone2);

        PersonInfo pi = new PersonInfo();
        pi.setPhones(phones);
        Phone fax = new Phone("86", "0571", "87654321", null);
        pi.setFax(fax);
        FullAddress addr = new FullAddress("CN", "zj", "3480", "wensanlu", "315000");
        pi.setFullAddress(addr);
        pi.setMobileNo("13584652131");
        pi.setMale(true);
        pi.setDepartment("b2b");
        pi.setHomepageUrl("www.capcom.com");
        pi.setJobTitle("qa");
        pi.setName("superman");

        bigPerson.setInfoProfile(pi);
        return bigPerson;
    }
}
