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
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.Header;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.stargate.client.Client;
import org.apache.hadoop.hbase.stargate.client.Cluster;
import org.apache.hadoop.hbase.stargate.client.Response;
import org.apache.hadoop.hbase.stargate.model.CellModel;
import org.apache.hadoop.hbase.stargate.model.CellSetModel;
import org.apache.hadoop.hbase.stargate.model.RowModel;
import org.apache.hadoop.hbase.util.Bytes;

public class TestRowResource extends MiniClusterTestBase {
  private static final String TABLE = "TestRowResource";
  private static final String COLUMN_1 = "a:";
  private static final String COLUMN_2 = "b:";
  private static final String ROW_1 = "testrow1";
  private static final String VALUE_1 = "testvalue1";
  private static final String ROW_2 = "testrow2";
  private static final String VALUE_2 = "testvalue2";
  private static final String ROW_3 = "testrow3";
  private static final String VALUE_3 = "testvalue3";
  private static final String ROW_4 = "testrow4";
  private static final String VALUE_4 = "testvalue4";

  private Client client;
  private JAXBContext context;
  private Marshaller marshaller;
  private Unmarshaller unmarshaller;
  private HBaseAdmin admin;

  private Response deleteRow(String table, String row) throws IOException {
    StringBuilder path = new StringBuilder();
    path.append('/');
    path.append(table);
    path.append('/');
    path.append(row);
    Response response = client.delete(path.toString());
    Thread.yield();
    return response;
  }

  private Response deleteValue(String table, String row, String column)
      throws IOException {
    StringBuilder path = new StringBuilder();
    path.append('/');
    path.append(table);
    path.append('/');
    path.append(row);
    path.append('/');
    path.append(column);
    Response response = client.delete(path.toString());
    Thread.yield();
    return response;
  }

  private Response getValueXML(String table, String row, String column)
      throws IOException {
    StringBuilder path = new StringBuilder();
    path.append('/');
    path.append(table);
    path.append('/');
    path.append(row);
    path.append('/');
    path.append(column);
    Response response = client.get(path.toString(), MIMETYPE_XML);
    return response;
  }

  private Response getValuePB(String table, String row, String column) 
      throws IOException {
    StringBuilder path = new StringBuilder();
    path.append('/');
    path.append(table);
    path.append('/');
    path.append(row);
    path.append('/');
    path.append(column);
    Response response = client.get(path.toString(), MIMETYPE_PROTOBUF); 
    return response;
  }

  private Response putValueXML(String table, String row, String column,
      String value) throws IOException, JAXBException {
    StringBuilder path = new StringBuilder();
    path.append('/');
    path.append(table);
    path.append('/');
    path.append(row);
    path.append('/');
    path.append(column);
    RowModel rowModel = new RowModel(row);
    rowModel.addCell(new CellModel(Bytes.toBytes(column), Bytes.toBytes(value)));
    CellSetModel cellSetModel = new CellSetModel();
    cellSetModel.addRow(rowModel);
    StringWriter writer = new StringWriter();
    marshaller.marshal(cellSetModel, writer);
    Response response = client.put(path.toString(), MIMETYPE_XML,
      Bytes.toBytes(writer.toString()));
    Thread.yield();
    return response;
  }

  private void checkValueXML(String table, String row, String column, 
      String value) throws IOException, JAXBException {
    Response response = getValueXML(table, row, column);
    assertEquals(response.getCode(), 200);
    CellSetModel cellSet = (CellSetModel)
      unmarshaller.unmarshal(new ByteArrayInputStream(response.getBody()));
    RowModel rowModel = cellSet.getRows().get(0);
    CellModel cell = rowModel.getCells().get(0);
    assertEquals(Bytes.toString(cell.getColumn()), column);
    assertEquals(Bytes.toString(cell.getValue()), value);
  }

  private Response putValuePB(String table, String row, String column,
      String value) throws IOException {
    StringBuilder path = new StringBuilder();
    path.append('/');
    path.append(table);
    path.append('/');
    path.append(row);
    path.append('/');
    path.append(column);
    RowModel rowModel = new RowModel(row);
    rowModel.addCell(new CellModel(Bytes.toBytes(column), Bytes.toBytes(value)));
    CellSetModel cellSetModel = new CellSetModel();
    cellSetModel.addRow(rowModel);
    Response response = client.put(path.toString(), MIMETYPE_PROTOBUF,
      cellSetModel.createProtobufOutput());
    Thread.yield();
    return response;
  }

  private void checkValuePB(String table, String row, String column, 
      String value) throws IOException {
    Response response = getValuePB(table, row, column);
    assertEquals(response.getCode(), 200);
    CellSetModel cellSet = new CellSetModel();
    cellSet.getObjectFromMessage(response.getBody());
    RowModel rowModel = cellSet.getRows().get(0);
    CellModel cell = rowModel.getCells().get(0);
    assertEquals(Bytes.toString(cell.getColumn()), column);
    assertEquals(Bytes.toString(cell.getValue()), value);
  }

  public void testMultiCellGetPutXML() throws IOException, JAXBException {
	String path = "/" + TABLE + "/fakerow";
	CellSetModel cellSetModel = new CellSetModel();
	RowModel rowModel = new RowModel(ROW_1);
	rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_1), Bytes.toBytes(VALUE_1)));
	rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_2), Bytes.toBytes(VALUE_2)));
	cellSetModel.addRow(rowModel);
	rowModel = new RowModel(ROW_2);
	rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_1), Bytes.toBytes(VALUE_3)));
	rowModel.addCell(new CellModel(Bytes.toBytes(COLUMN_2), Bytes.toBytes(VALUE_4)));
	cellSetModel.addRow(rowModel);
	StringWriter writer = new StringWriter();
	marshaller.marshal(cellSetModel, writer);
	Response response = client.put(path, MIMETYPE_XML, Bytes.toBytes(writer.toString()));
	Thread.yield();
	response = client.get(path, MIMETYPE_XML);
	assertEquals(response.getCode(), 404);
	checkValueXML(TABLE, ROW_1, COLUMN_1, VALUE_1);
	checkValueXML(TABLE, ROW_1, COLUMN_2, VALUE_2);
	checkValueXML(TABLE, ROW_2, COLUMN_1, VALUE_3);
	checkValueXML(TABLE, ROW_2, COLUMN_2, VALUE_4);
	response = deleteRow(TABLE, ROW_1);
	assertEquals(response.getCode(), 200);
	response = deleteRow(TABLE, ROW_2);
	assertEquals(response.getCode(), 200);
}
}
