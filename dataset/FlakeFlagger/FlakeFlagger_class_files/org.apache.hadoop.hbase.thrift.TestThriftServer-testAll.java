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
package org.apache.hadoop.hbase.thrift;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HBaseClusterTestCase;
import org.apache.hadoop.hbase.thrift.generated.BatchMutation;
import org.apache.hadoop.hbase.thrift.generated.ColumnDescriptor;
import org.apache.hadoop.hbase.thrift.generated.Mutation;
import org.apache.hadoop.hbase.thrift.generated.TCell;
import org.apache.hadoop.hbase.thrift.generated.TRowResult;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Unit testing for ThriftServer.HBaseHandler, a part of the
 * org.apache.hadoop.hbase.thrift package.
 */
public class TestThriftServer extends HBaseClusterTestCase {

  // Static names for tables, columns, rows, and values
  private static byte[] tableAname = Bytes.toBytes("tableA");
  private static byte[] tableBname = Bytes.toBytes("tableB");
  private static byte[] columnAname = Bytes.toBytes("columnA:");
  private static byte[] columnBname = Bytes.toBytes("columnB:");
  private static byte[] rowAname = Bytes.toBytes("rowA");
  private static byte[] rowBname = Bytes.toBytes("rowB");
  private static byte[] valueAname = Bytes.toBytes("valueA");
  private static byte[] valueBname = Bytes.toBytes("valueB");
  private static byte[] valueCname = Bytes.toBytes("valueC");
  private static byte[] valueDname = Bytes.toBytes("valueD");

  /**
 * Runs all of the tests under a single JUnit test method.  We consolidate all testing to one method because HBaseClusterTestCase is prone to OutOfMemoryExceptions when there are three or more JUnit test methods.
 * @throws Exception
 */
public void testAll() throws Exception {
	doTestTableCreateDrop();
	doTestTableMutations();
	doTestTableTimestampsAndColumns();
	doTestTableScanners();
}

  /**
   *
   * @return a List of ColumnDescriptors for use in creating a table.  Has one
   * default ColumnDescriptor and one ColumnDescriptor with fewer versions
   */
  private List<ColumnDescriptor> getColumnDescriptors() {
    ArrayList<ColumnDescriptor> cDescriptors = new ArrayList<ColumnDescriptor>();

    // A default ColumnDescriptor
    ColumnDescriptor cDescA = new ColumnDescriptor();
    cDescA.name = columnAname;
    cDescriptors.add(cDescA);

    // A slightly customized ColumnDescriptor (only 2 versions)
    ColumnDescriptor cDescB = new ColumnDescriptor(columnBname, 2, "NONE",
        false, "NONE", 0, 0, false, -1);
    cDescriptors.add(cDescB);

    return cDescriptors;
  }

  /**
   *
   * @param includeA whether or not to include columnA
   * @param includeB whether or not to include columnB
   * @return a List of column names for use in retrieving a scanner
   */
  private List<byte[]> getColumnList(boolean includeA, boolean includeB) {
    List<byte[]> columnList = new ArrayList<byte[]>();
    if (includeA) columnList.add(columnAname);
    if (includeB) columnList.add(columnBname);
    return columnList;
  }

  /**
   *
   * @return a List of Mutations for a row, with columnA having valueA
   * and columnB having valueB
   */
  private List<Mutation> getMutations() {
    List<Mutation> mutations = new ArrayList<Mutation>();
    mutations.add(new Mutation(false, columnAname, valueAname));
    mutations.add(new Mutation(false, columnBname, valueBname));
    return mutations;
  }

  /**
   *
   * @return a List of BatchMutations with the following effects:
   * (rowA, columnA): delete
   * (rowA, columnB): place valueC
   * (rowB, columnA): place valueC
   * (rowB, columnB): place valueD
   */
  private List<BatchMutation> getBatchMutations() {
    List<BatchMutation> batchMutations = new ArrayList<BatchMutation>();

    // Mutations to rowA.  You can't mix delete and put anymore.
    List<Mutation> rowAmutations = new ArrayList<Mutation>();
    rowAmutations.add(new Mutation(true, columnAname, null));
    batchMutations.add(new BatchMutation(rowAname, rowAmutations));

    rowAmutations = new ArrayList<Mutation>();
    rowAmutations.add(new Mutation(false, columnBname, valueCname));
    batchMutations.add(new BatchMutation(rowAname, rowAmutations));

    // Mutations to rowB
    List<Mutation> rowBmutations = new ArrayList<Mutation>();
    rowBmutations.add(new Mutation(false, columnAname, valueCname));
    rowBmutations.add(new Mutation(false, columnBname, valueDname));
    batchMutations.add(new BatchMutation(rowBname, rowBmutations));

    return batchMutations;
  }

  /**
   * Asserts that the passed scanner is exhausted, and then closes
   * the scanner.
   *
   * @param scannerId the scanner to close
   * @param handler the HBaseHandler interfacing to HBase
   * @throws Exception
   */
  private void closeScanner(int scannerId, ThriftServer.HBaseHandler handler) throws Exception {
    handler.scannerGet(scannerId);
    handler.scannerClose(scannerId);
  }
}
