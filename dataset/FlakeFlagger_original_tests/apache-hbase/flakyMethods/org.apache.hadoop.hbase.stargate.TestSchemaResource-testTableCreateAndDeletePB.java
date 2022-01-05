public void testTableCreateAndDeletePB() throws IOException, JAXBException {
  String schemaPath="/" + TABLE2 + "/schema";
  TableSchemaModel model;
  Response response;
  assertFalse(admin.tableExists(TABLE2));
  model=TestTableSchemaModel.buildTestModel(TABLE2);
  TestTableSchemaModel.checkModel(model,TABLE2);
  response=client.put(schemaPath,Constants.MIMETYPE_PROTOBUF,model.createProtobufOutput());
  assertEquals(response.getCode(),201);
  admin.enableTable(TABLE2);
  response=client.get(schemaPath,Constants.MIMETYPE_PROTOBUF);
  assertEquals(response.getCode(),200);
  model=new TableSchemaModel();
  model.getObjectFromMessage(response.getBody());
  TestTableSchemaModel.checkModel(model,TABLE2);
  client.delete(schemaPath);
  assertFalse(admin.tableExists(TABLE2));
}
