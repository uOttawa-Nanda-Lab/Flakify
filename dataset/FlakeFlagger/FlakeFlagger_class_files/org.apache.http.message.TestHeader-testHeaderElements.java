/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Header}.
 */
public class TestHeader {

    @Test
    public void testHeaderElements() {
        Header header = new BasicHeader("name", "element1 = value1, element2; param1 = value1, element3");
        HeaderElement[] elements = header.getElements();
        Assert.assertNotNull(elements);
        Assert.assertEquals(3, elements.length);
        Assert.assertEquals("element1", elements[0].getName());
        Assert.assertEquals("value1", elements[0].getValue());
        Assert.assertEquals("element2", elements[1].getName());
        Assert.assertEquals(null, elements[1].getValue());
        Assert.assertEquals("element3", elements[2].getName());
        Assert.assertEquals(null, elements[2].getValue());
        Assert.assertEquals(1, elements[1].getParameters().length);

        header = new BasicHeader("name", null);
        elements = header.getElements();
        Assert.assertNotNull(elements);
        Assert.assertEquals(0, elements.length);
    }

}

