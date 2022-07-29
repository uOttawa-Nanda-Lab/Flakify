/*
 * Copyright 2010 The Apache Software Foundation
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

package org.apache.hadoop.hbase.stargate;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.InclusiveStopFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SkipFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.stargate.client.Client;
import org.apache.hadoop.hbase.stargate.client.Cluster;
import org.apache.hadoop.hbase.stargate.client.Response;
import org.apache.hadoop.hbase.stargate.model.CellModel;
import org.apache.hadoop.hbase.stargate.model.CellSetModel;
import org.apache.hadoop.hbase.stargate.model.RowModel;
import org.apache.hadoop.hbase.stargate.model.ScannerModel;
import org.apache.hadoop.hbase.util.Bytes;

public class TestScannersWithFilters extends MiniClusterTestBase {

  private static final Log LOG =
    LogFactory.getLog(TestScannersWithFilters.class);

  private Client client;
  private JAXBContext context;
  private Marshaller marshaller;
  private Unmarshaller unmarshaller;

  private static final byte [][] ROWS_ONE = {
    Bytes.toBytes("testRowOne-0"), Bytes.toBytes("testRowOne-1"),
    Bytes.toBytes("testRowOne-2"), Bytes.toBytes("testRowOne-3")
  };

  private static final byte [][] ROWS_TWO = {
    Bytes.toBytes("testRowTwo-0"), Bytes.toBytes("testRowTwo-1"),
    Bytes.toBytes("testRowTwo-2"), Bytes.toBytes("testRowTwo-3")
  };

  private static final byte [][] FAMILIES = {
    Bytes.toBytes("testFamilyOne"), Bytes.toBytes("testFamilyTwo")
  };

  private static final byte [][] QUALIFIERS_ONE = {
    Bytes.toBytes("testQualifierOne-0"), Bytes.toBytes("testQualifierOne-1"),
    Bytes.toBytes("testQualifierOne-2"), Bytes.toBytes("testQualifierOne-3")
  };

  private static final byte [][] QUALIFIERS_TWO = {
    Bytes.toBytes("testQualifierTwo-0"), Bytes.toBytes("testQualifierTwo-1"),
    Bytes.toBytes("testQualifierTwo-2"), Bytes.toBytes("testQualifierTwo-3")
  };

  private static final byte [][] VALUES = {
    Bytes.toBytes("testValueOne"), Bytes.toBytes("testValueTwo")
  };

  private long numRows = ROWS_ONE.length + ROWS_TWO.length;
  private long colsPerRow = FAMILIES.length * QUALIFIERS_ONE.length;

  private void verifyScan(Scan s, long expectedRows, long expectedKeys) 
      throws Exception {
    ScannerModel model = ScannerModel.fromScan(s);
    model.setBatch(Integer.MAX_VALUE); // fetch it all at once
    StringWriter writer = new StringWriter();
    marshaller.marshal(model, writer);
    LOG.debug(writer.toString());
    byte[] body = Bytes.toBytes(writer.toString());
    Response response = client.put("/" + getName() + "/scanner", MIMETYPE_XML,
      body);
    assertEquals(response.getCode(), 201);
    String scannerURI = response.getLocation();
    assertNotNull(scannerURI);

    // get a cell set
    response = client.get(scannerURI, MIMETYPE_XML);
    assertEquals(response.getCode(), 200);
    CellSetModel cells = (CellSetModel)
      unmarshaller.unmarshal(new ByteArrayInputStream(response.getBody()));

    int rows = cells.getRows().size();
    assertTrue("Scanned too many rows! Only expected " + expectedRows + 
        " total but scanned " + rows, expectedRows == rows);
    for (RowModel row: cells.getRows()) {
      int count = row.getCells().size();
      assertEquals("Expected " + expectedKeys + " keys per row but " +
        "returned " + count, expectedKeys, count);
    }

    // delete the scanner
    response = client.delete(scannerURI);
    assertEquals(response.getCode(), 200);
  }

  private void verifyScanFull(Scan s, KeyValue [] kvs) throws Exception {
    ScannerModel model = ScannerModel.fromScan(s);
    model.setBatch(Integer.MAX_VALUE); // fetch it all at once
    StringWriter writer = new StringWriter();
    marshaller.marshal(model, writer);
    LOG.debug(writer.toString());
    byte[] body = Bytes.toBytes(writer.toString());
    Response response = client.put("/" + getName() + "/scanner", MIMETYPE_XML,
      body);
    assertEquals(response.getCode(), 201);
    String scannerURI = response.getLocation();
    assertNotNull(scannerURI);

    // get a cell set
    response = client.get(scannerURI, MIMETYPE_XML);
    assertEquals(response.getCode(), 200);
    CellSetModel cellSet = (CellSetModel)
      unmarshaller.unmarshal(new ByteArrayInputStream(response.getBody()));

    // delete the scanner
    response = client.delete(scannerURI);
    assertEquals(response.getCode(), 200);

    int row = 0;
    int idx = 0;
    Iterator<RowModel> i = cellSet.getRows().iterator();
    for (boolean done = true; done; row++) {
      done = i.hasNext();
      if (!done) break;
      RowModel rowModel = i.next();
      List<CellModel> cells = rowModel.getCells();
      if (cells.isEmpty()) break;
      assertTrue("Scanned too many keys! Only expected " + kvs.length + 
        " total but already scanned " + (cells.size() + idx), 
        kvs.length >= idx + cells.size());
      for (CellModel cell: cells) {
        assertTrue("Row mismatch", 
            Bytes.equals(rowModel.getKey(), kvs[idx].getRow()));
        byte[][] split = KeyValue.parseColumn(cell.getColumn());
        assertTrue("Family mismatch", 
            Bytes.equals(split[0], kvs[idx].getFamily()));
        assertTrue("Qualifier mismatch", 
            Bytes.equals(split[1], kvs[idx].getQualifier()));
        assertTrue("Value mismatch", 
            Bytes.equals(cell.getValue(), kvs[idx].getValue()));
        idx++;
      }
    }
    assertEquals("Expected " + kvs.length + " total keys but scanned " + idx,
      kvs.length, idx);
  }

  private void verifyScanNoEarlyOut(Scan s, long expectedRows, long expectedKeys) 
      throws Exception {
    ScannerModel model = ScannerModel.fromScan(s);
    model.setBatch(Integer.MAX_VALUE); // fetch it all at once
    StringWriter writer = new StringWriter();
    marshaller.marshal(model, writer);
    LOG.debug(writer.toString());
    byte[] body = Bytes.toBytes(writer.toString());
    Response response = client.put("/" + getName() + "/scanner", MIMETYPE_XML,
      body);
    assertEquals(response.getCode(), 201);
    String scannerURI = response.getLocation();
    assertNotNull(scannerURI);

    // get a cell set
    response = client.get(scannerURI, MIMETYPE_XML);
    assertEquals(response.getCode(), 200);
    CellSetModel cellSet = (CellSetModel)
      unmarshaller.unmarshal(new ByteArrayInputStream(response.getBody()));

    // delete the scanner
    response = client.delete(scannerURI);
    assertEquals(response.getCode(), 200);

    Iterator<RowModel> i = cellSet.getRows().iterator();
    int j = 0;
    for (boolean done = true; done; j++) {
      done = i.hasNext();
      if (!done) break;
      RowModel rowModel = i.next();
      List<CellModel> cells = rowModel.getCells();
      if (cells.isEmpty()) break;
      assertTrue("Scanned too many rows! Only expected " + expectedRows + 
        " total but already scanned " + (j+1), expectedRows > j);
      assertEquals("Expected " + expectedKeys + " keys per row but " +
        "returned " + cells.size(), expectedKeys, cells.size());
    }
    assertEquals("Expected " + expectedRows + " rows but scanned " + j +
      " rows", expectedRows, j);
  }

  public void testPageFilter() throws Exception {
	KeyValue[] expectedKVs = { new KeyValue(ROWS_ONE[0], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
			new KeyValue(ROWS_ONE[0], FAMILIES[0], QUALIFIERS_ONE[2], VALUES[0]),
			new KeyValue(ROWS_ONE[0], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
			new KeyValue(ROWS_ONE[0], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
			new KeyValue(ROWS_ONE[0], FAMILIES[1], QUALIFIERS_ONE[2], VALUES[0]),
			new KeyValue(ROWS_ONE[0], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
			new KeyValue(ROWS_ONE[2], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
			new KeyValue(ROWS_ONE[2], FAMILIES[0], QUALIFIERS_ONE[2], VALUES[0]),
			new KeyValue(ROWS_ONE[2], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
			new KeyValue(ROWS_ONE[2], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
			new KeyValue(ROWS_ONE[2], FAMILIES[1], QUALIFIERS_ONE[2], VALUES[0]),
			new KeyValue(ROWS_ONE[2], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
			new KeyValue(ROWS_ONE[3], FAMILIES[0], QUALIFIERS_ONE[0], VALUES[0]),
			new KeyValue(ROWS_ONE[3], FAMILIES[0], QUALIFIERS_ONE[2], VALUES[0]),
			new KeyValue(ROWS_ONE[3], FAMILIES[0], QUALIFIERS_ONE[3], VALUES[0]),
			new KeyValue(ROWS_ONE[3], FAMILIES[1], QUALIFIERS_ONE[0], VALUES[0]),
			new KeyValue(ROWS_ONE[3], FAMILIES[1], QUALIFIERS_ONE[2], VALUES[0]),
			new KeyValue(ROWS_ONE[3], FAMILIES[1], QUALIFIERS_ONE[3], VALUES[0]),
			new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
			new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[2], VALUES[1]),
			new KeyValue(ROWS_TWO[0], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
			new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
			new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[2], VALUES[1]),
			new KeyValue(ROWS_TWO[0], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
			new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
			new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[2], VALUES[1]),
			new KeyValue(ROWS_TWO[2], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
			new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
			new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[2], VALUES[1]),
			new KeyValue(ROWS_TWO[2], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]),
			new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[0], VALUES[1]),
			new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[2], VALUES[1]),
			new KeyValue(ROWS_TWO[3], FAMILIES[0], QUALIFIERS_TWO[3], VALUES[1]),
			new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[0], VALUES[1]),
			new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[2], VALUES[1]),
			new KeyValue(ROWS_TWO[3], FAMILIES[1], QUALIFIERS_TWO[3], VALUES[1]) };
	long expectedRows = 6;
	long expectedKeys = this.colsPerRow;
	Scan s = new Scan();
	s.setFilter(new PageFilter(expectedRows));
	verifyScan(s, expectedRows, expectedKeys);
	s.setFilter(new PageFilter(expectedRows));
	verifyScanFull(s, expectedKVs);
	expectedRows = 4;
	expectedKeys = this.colsPerRow;
	s = new Scan();
	s.setFilter(new PageFilter(expectedRows));
	verifyScan(s, expectedRows, expectedKeys);
	s.setFilter(new PageFilter(expectedRows));
	verifyScanFull(s, Arrays.copyOf(expectedKVs, 24));
	expectedRows = 2;
	expectedKeys = this.colsPerRow;
	s = new Scan();
	s.setFilter(new PageFilter(expectedRows));
	verifyScan(s, expectedRows, expectedKeys);
	s.setFilter(new PageFilter(expectedRows));
	verifyScanFull(s, Arrays.copyOf(expectedKVs, 12));
	expectedRows = 1;
	expectedKeys = this.colsPerRow;
	s = new Scan();
	s.setFilter(new PageFilter(expectedRows));
	verifyScan(s, expectedRows, expectedKeys);
	s.setFilter(new PageFilter(expectedRows));
	verifyScanFull(s, Arrays.copyOf(expectedKVs, 6));
}
  
}
