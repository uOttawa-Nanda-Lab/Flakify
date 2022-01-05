public void testTableCreateAndDeleteXML() throws IOException, JAXBException {
  String schemaPath="/" + TABLE1 + "/schema";
  TableSchemaModel model;
  Response response;
  assertFalse(admin.tableExists(TABLE1));
  model=TestTableSchemaModel.buildTestModel(TABLE1);
  TestTableSchemaModel.checkModel(model,TABLE1);
  response=client.put(schemaPath,MIMETYPE_XML,toXML(model));
  assertEquals(response.getCode(),201);
  admin.enableTable(TABLE1);
  response=client.get(schemaPath,MIMETYPE_XML);
  assertEquals(response.getCode(),200);
  model=fromXML(response.getBody());
  TestTableSchemaModel.checkModel(model,TABLE1);
  client.delete(schemaPath);
  assertFalse(admin.tableExists(TABLE1));
}
