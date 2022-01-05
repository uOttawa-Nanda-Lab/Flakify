public void testSimpleScannerBinary() throws IOException {
  ScannerModel model=new ScannerModel();
  model.setBatch(1);
  model.addColumn(Bytes.toBytes(COLUMN_1));
  Response response=client.put("/" + TABLE + "/scanner",MIMETYPE_PROTOBUF,model.createProtobufOutput());
  assertEquals(response.getCode(),201);
  String scannerURI=response.getLocation();
  assertNotNull(scannerURI);
  response=client.get(scannerURI,MIMETYPE_BINARY);
  assertEquals(response.getCode(),200);
  assertTrue(response.getBody().length > 0);
  boolean foundRowHeader=false, foundColumnHeader=false, foundTimestampHeader=false;
  for (  Header header : response.getHeaders()) {
    if (header.getName().equals("X-Row")) {
      foundRowHeader=true;
    }
 else     if (header.getName().equals("X-Column")) {
      foundColumnHeader=true;
    }
 else     if (header.getName().equals("X-Timestamp")) {
      foundTimestampHeader=true;
    }
  }
  assertTrue(foundRowHeader);
  assertTrue(foundColumnHeader);
  assertTrue(foundTimestampHeader);
  response=client.delete(scannerURI);
  assertEquals(response.getCode(),200);
}
