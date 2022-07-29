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
import java.util.Iterator;

import org.apache.http.Header;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link HeaderGroup}.
 *
 */
public class TestHeaderGroup {

    @Test public void testUpdateHeader(){HeaderGroup headergroup=new HeaderGroup();Header header1=new BasicHeader("name1","value1");Header header2=new BasicHeader("name2","value2");Header header3=new BasicHeader("name3","value3");headergroup.addHeader(header1);headergroup.addHeader(header2);headergroup.addHeader(header3);headergroup.updateHeader(new BasicHeader("name2","newvalue"));headergroup.updateHeader(new BasicHeader("name4","value4"));headergroup.updateHeader(null);Assert.assertEquals(4,headergroup.getAllHeaders().length);Assert.assertEquals("newvalue",headergroup.getFirstHeader("name2").getValue());}

}
