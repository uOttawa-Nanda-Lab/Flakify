/**
 * Copyright 2007 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparator;
import org.junit.Assert;

public class TestHbaseObjectWritable extends TestCase {

  @SuppressWarnings("boxing") public void testReadObjectDataInputConfiguration() throws IOException{HBaseConfiguration conf=new HBaseConfiguration();final int COUNT=101;assertTrue(doType(conf,COUNT,int.class).equals(COUNT));final byte[] testing="testing".getBytes();byte[] result=(byte[])doType(conf,testing,testing.getClass());assertTrue(WritableComparator.compareBytes(testing,0,testing.length,result,0,result.length) == 0);boolean exception=false;try {doType(conf,new File("a"),File.class);} catch (UnsupportedOperationException uoe){exception=true;}assertTrue(exception);final byte A='A';byte[] bytes=new byte[1];bytes[0]=A;Object obj=doType(conf,bytes,byte[].class);assertTrue(((byte[])obj)[0] == A);obj=doType(conf,new Text(""),Text.class);assertTrue(obj instanceof Text);List<String> list=new ArrayList<String>();list.add("hello");list.add("world");list.add("universe");obj=doType(conf,list,List.class);assertTrue(obj instanceof List);Assert.assertArrayEquals(list.toArray(),((List)obj).toArray());ArrayList<String> arr=new ArrayList<String>();arr.add("hello");arr.add("world");arr.add("universe");obj=doType(conf,arr,ArrayList.class);assertTrue(obj instanceof ArrayList);Assert.assertArrayEquals(list.toArray(),((ArrayList)obj).toArray());obj=doType(conf,new PrefixFilter(HConstants.EMPTY_BYTE_ARRAY),PrefixFilter.class);assertTrue(obj instanceof PrefixFilter);}

  private Object doType(final HBaseConfiguration conf, final Object value,
      final Class<?> clazz)
  throws IOException {
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(byteStream);
    HbaseObjectWritable.writeObject(out, value, clazz, conf);
    out.close();
    ByteArrayInputStream bais =
      new ByteArrayInputStream(byteStream.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    Object product = HbaseObjectWritable.readObject(dis, conf);
    dis.close();
    return product;
  }

}