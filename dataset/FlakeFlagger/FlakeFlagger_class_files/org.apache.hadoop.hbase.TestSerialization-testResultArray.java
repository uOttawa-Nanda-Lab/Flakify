/**
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
package org.apache.hadoop.hbase;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.RowLock;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.io.HbaseMapWritable;
import org.apache.hadoop.hbase.io.TimeRange;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Writables;
import org.apache.hadoop.io.DataInputBuffer;

/**
 * Test HBase Writables serializations
 */
public class TestSerialization extends HBaseTestCase {

  public void testResultArray() throws Exception {
	byte[] rowA = Bytes.toBytes("rowA");
	byte[] famA = Bytes.toBytes("famA");
	byte[] qfA = Bytes.toBytes("qfA");
	byte[] valueA = Bytes.toBytes("valueA");
	byte[] rowB = Bytes.toBytes("rowB");
	byte[] famB = Bytes.toBytes("famB");
	byte[] qfB = Bytes.toBytes("qfB");
	byte[] valueB = Bytes.toBytes("valueB");
	KeyValue kvA = new KeyValue(rowA, famA, qfA, valueA);
	KeyValue kvB = new KeyValue(rowB, famB, qfB, valueB);
	Result result1 = new Result(new KeyValue[] { kvA, kvB });
	Result result2 = new Result(new KeyValue[] { kvB });
	Result result3 = new Result(new KeyValue[] { kvB });
	Result[] results = new Result[] { result1, result2, result3 };
	ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	DataOutputStream out = new DataOutputStream(byteStream);
	Result.writeArray(out, results);
	byte[] rb = byteStream.toByteArray();
	DataInputBuffer in = new DataInputBuffer();
	in.reset(rb, 0, rb.length);
	Result[] deResults = Result.readArray(in);
	assertTrue(results.length == deResults.length);
	for (int i = 0; i < results.length; i++) {
		KeyValue[] keysA = results[i].sorted();
		KeyValue[] keysB = deResults[i].sorted();
		assertTrue(keysA.length == keysB.length);
		for (int j = 0; j < keysA.length; j++) {
			assertTrue(
					"Expected equivalent keys but found:\n" + "KeyA : " + keysA[j].toString() + "\n" + "KeyB : "
							+ keysB[j].toString() + "\n" + keysA.length + " total keys, " + i + "th so far",
					keysA[j].equals(keysB[j]));
		}
	}
}
}