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
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.Header;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.stargate.client.Client;
import org.apache.hadoop.hbase.stargate.client.Cluster;
import org.apache.hadoop.hbase.stargate.client.Response;
import org.apache.hadoop.hbase.stargate.model.CellModel;
import org.apache.hadoop.hbase.stargate.model.CellSetModel;
import org.apache.hadoop.hbase.stargate.model.RowModel;
import org.apache.hadoop.hbase.stargate.model.ScannerModel;
import org.apache.hadoop.hbase.util.Bytes;

public class TestScannerResource extends MiniClusterTestBase {
  private static final String TABLE = "TestScannerResource";
  private static final String COLUMN_1 = "a:";
  private static final String COLUMN_2 = "b:";

  private static int expectedRows1;
  private static int expectedRows2;

  private Client client;
  private JAXBContext context;
  private Marshaller marshaller;
  private Unmarshaller unmarshaller;
  private HBaseAdmin admin;

  private int insertData(String tableName, String column, double prob)
      throws IOException {
    Random rng = new Random();
    int count = 0;
    HTable table = new HTable(conf, tableName);
    byte[] k = new byte[3];
    byte [][] famAndQf = KeyValue.parseColumn(Bytes.toBytes(column));
    for (byte b1 = 'a'; b1 < 'z'; b1++) {
      for (byte b2 = 'a'; b2 < 'z'; b2++) {
        for (byte b3 = 'a'; b3 < 'z'; b3++) {
          if (rng.nextDouble() < prob) {
            k[0] = b1;
            k[1] = b2;
            k[2] = b3;
            Put put = new Put(k);
            if(famAndQf.length == 1) {
              put.add(famAndQf[0], new byte[0], k);
            } else {
              put.add(famAndQf[0], famAndQf[1], k);
            }
            table.put(put);
            count++;
          }
        }
      }
    }
    table.flushCommits();
    return count;
  }

  private int countCellSet(CellSetModel model) {
    int count = 0;
    Iterator<RowModel> rows = model.getRows().iterator();
    while (rows.hasNext()) {
      RowModel row = rows.next();
      Iterator<CellModel> cells = row.getCells().iterator();
      while (cells.hasNext()) {
        cells.next();
        count++;
      }
    }
    return count;
  }

  public void testSimpleScannerPB() throws IOException {
	final int BATCH_SIZE = 10;
	ScannerModel model = new ScannerModel();
	model.setBatch(BATCH_SIZE);
	model.addColumn(Bytes.toBytes(COLUMN_1));
	Response response = client.put("/" + TABLE + "/scanner", MIMETYPE_PROTOBUF, model.createProtobufOutput());
	assertEquals(response.getCode(), 201);
	String scannerURI = response.getLocation();
	assertNotNull(scannerURI);
	response = client.get(scannerURI, MIMETYPE_PROTOBUF);
	assertEquals(response.getCode(), 200);
	CellSetModel cellSet = new CellSetModel();
	cellSet.getObjectFromMessage(response.getBody());
	assertEquals(countCellSet(cellSet), BATCH_SIZE);
	response = client.delete(scannerURI);
	assertEquals(response.getCode(), 200);
}

  private int fullTableScan(ScannerModel model) throws IOException {
    model.setBatch(100);
    Response response = client.put("/" + TABLE + "/scanner",
        MIMETYPE_PROTOBUF, model.createProtobufOutput());
    assertEquals(response.getCode(), 201);
    String scannerURI = response.getLocation();
    assertNotNull(scannerURI);
    int count = 0;
    while (true) {
      response = client.get(scannerURI, MIMETYPE_PROTOBUF);
      assertTrue(response.getCode() == 200 || response.getCode() == 204);
      if (response.getCode() == 200) {
        CellSetModel cellSet = new CellSetModel();
        cellSet.getObjectFromMessage(response.getBody());
        Iterator<RowModel> rows = cellSet.getRows().iterator();
        while (rows.hasNext()) {
          RowModel row = rows.next();
          Iterator<CellModel> cells = row.getCells().iterator();
          while (cells.hasNext()) {
            cells.next();
            count++;
          }
        }
      } else {
        break;
      }
    }
    // delete the scanner
    response = client.delete(scannerURI);
    assertEquals(response.getCode(), 200);
    return count;
  }
}
