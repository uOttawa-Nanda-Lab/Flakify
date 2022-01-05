public void testSimpleScannerXML() throws IOException, JAXBException {
  final int BATCH_SIZE=5;
  ScannerModel model=new ScannerModel();
  model.setBatch(BATCH_SIZE);
  model.addColumn(Bytes.toBytes(COLUMN_1));
  StringWriter writer=new StringWriter();
  marshaller.marshal(model,writer);
  byte[] body=Bytes.toBytes(writer.toString());
  Response response=client.put("/" + TABLE + "/scanner",MIMETYPE_XML,body);
  assertEquals(response.getCode(),201);
  String scannerURI=response.getLocation();
  assertNotNull(scannerURI);
  response=client.get(scannerURI,MIMETYPE_XML);
  assertEquals(response.getCode(),200);
  CellSetModel cellSet=(CellSetModel)unmarshaller.unmarshal(new ByteArrayInputStream(response.getBody()));
  assertEquals(countCellSet(cellSet),BATCH_SIZE);
  response=client.delete(scannerURI);
  assertEquals(response.getCode(),200);
}
