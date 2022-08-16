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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.stargate.client.Client;
import org.apache.hadoop.hbase.stargate.client.Cluster;
import org.apache.hadoop.hbase.stargate.client.Response;
import org.apache.hadoop.hbase.stargate.model.ColumnSchemaModel;
import org.apache.hadoop.hbase.stargate.model.TableSchemaModel;
import org.apache.hadoop.hbase.stargate.model.TestTableSchemaModel;
import org.apache.hadoop.hbase.util.Bytes;

public class TestSchemaResource extends MiniClusterTestBase {
  private Client client;
  private JAXBContext context;
  private HBaseAdmin admin;

  private static String TABLE1 = "TestSchemaResource1";
  private static String TABLE2 = "TestSchemaResource2";

  private byte[] toXML(TableSchemaModel model) throws JAXBException {
    StringWriter writer = new StringWriter();
    context.createMarshaller().marshal(model, writer);
    return Bytes.toBytes(writer.toString());
  }

  private TableSchemaModel fromXML(byte[] content) throws JAXBException {
    return (TableSchemaModel) context.createUnmarshaller()
      .unmarshal(new ByteArrayInputStream(content));
  }

  public void testTableCreateAndDeletePB() throws IOException, JAXBException {
	String schemaPath = "/" + TABLE2 + "/schema";
	TableSchemaModel model;
	Response response;
	assertFalse(admin.tableExists(TABLE2));
	model = TestTableSchemaModel.buildTestModel(TABLE2);
	TestTableSchemaModel.checkModel(model, TABLE2);
	response = client.put(schemaPath, Constants.MIMETYPE_PROTOBUF, model.createProtobufOutput());
	assertEquals(response.getCode(), 201);
	admin.enableTable(TABLE2);
	response = client.get(schemaPath, Constants.MIMETYPE_PROTOBUF);
	assertEquals(response.getCode(), 200);
	model = new TableSchemaModel();
	model.getObjectFromMessage(response.getBody());
	TestTableSchemaModel.checkModel(model, TABLE2);
	client.delete(schemaPath);
	assertFalse(admin.tableExists(TABLE2));
}
}
