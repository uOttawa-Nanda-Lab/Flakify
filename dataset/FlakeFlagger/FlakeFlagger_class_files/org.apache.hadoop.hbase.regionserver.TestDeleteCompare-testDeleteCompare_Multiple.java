/*
 * Copyright 2009 The Apache Software Foundation
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

package org.apache.hadoop.hbase.regionserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.KeyValueTestUtil;
import org.apache.hadoop.hbase.regionserver.DeleteCompare.DeleteCode;
import org.apache.hadoop.hbase.util.Bytes;

import junit.framework.TestCase;

public class TestDeleteCompare extends TestCase {

  public void testDeleteCompare_Multiple(){Set<KeyValue> memstore=new TreeSet<KeyValue>(KeyValue.COMPARATOR);memstore.add(KeyValueTestUtil.create("row11","fam","col1",1,"d-c"));memstore.add(KeyValueTestUtil.create("row21","fam","col1",4,"d-c"));memstore.add(KeyValueTestUtil.create("row21","fam","col1",3,"d-c"));memstore.add(KeyValueTestUtil.create("row21","fam","col1",2,"d-c"));memstore.add(KeyValueTestUtil.create("row21","fam","col1",1,KeyValue.Type.Delete,"dont-care"));memstore.add(KeyValueTestUtil.create("row31","fam","col1",1,"dont-care"));List<DeleteCode> expected=new ArrayList<DeleteCode>();expected.add(DeleteCode.SKIP);expected.add(DeleteCode.DELETE);expected.add(DeleteCode.DELETE);expected.add(DeleteCode.DELETE);expected.add(DeleteCode.DELETE);expected.add(DeleteCode.DONE);KeyValue delete=KeyValueTestUtil.create("row21","fam","col1",5,KeyValue.Type.DeleteColumn,"dont-care");byte[] deleteBuffer=delete.getBuffer();int deleteRowOffset=delete.getRowOffset();short deleteRowLen=delete.getRowLength();int deleteQualifierOffset=delete.getQualifierOffset();int deleteQualifierLen=delete.getQualifierLength();int deleteTimestampOffset=deleteQualifierOffset + deleteQualifierLen;byte deleteType=deleteBuffer[deleteTimestampOffset + Bytes.SIZEOF_LONG];List<DeleteCode> actual=new ArrayList<DeleteCode>();for (KeyValue mem:memstore){actual.add(DeleteCompare.deleteCompare(mem,deleteBuffer,deleteRowOffset,deleteRowLen,deleteQualifierOffset,deleteQualifierLen,deleteTimestampOffset,deleteType,KeyValue.KEY_COMPARATOR));}assertEquals(expected.size(),actual.size());for (int i=0;i < expected.size();i++){assertEquals(expected.get(i),actual.get(i));}}
}
