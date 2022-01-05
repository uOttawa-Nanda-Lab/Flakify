@Test public void testPut() throws IOException {
  final byte[] CONTENTS_FAMILY=Bytes.toBytes("contents");
  final byte[] SMALL_FAMILY=Bytes.toBytes("smallfam");
  final byte[] row1=Bytes.toBytes("row1");
  final byte[] row2=Bytes.toBytes("row2");
  final byte[] value=Bytes.toBytes("abcd");
  HTable table=TEST_UTIL.createTable(Bytes.toBytes("testPut"),new byte[][]{CONTENTS_FAMILY,SMALL_FAMILY});
  Put put=new Put(row1);
  put.add(CONTENTS_FAMILY,null,value);
  table.put(put);
  put=new Put(row2);
  put.add(CONTENTS_FAMILY,null,value);
  assertEquals(put.size(),1);
  assertEquals(put.getFamilyMap().get(CONTENTS_FAMILY).size(),1);
  KeyValue kv=put.getFamilyMap().get(CONTENTS_FAMILY).get(0);
  assertTrue(Bytes.equals(kv.getFamily(),CONTENTS_FAMILY));
  assertTrue(Bytes.equals(kv.getQualifier(),new byte[0]));
  assertTrue(Bytes.equals(kv.getValue(),value));
  table.put(put);
  Scan scan=new Scan();
  scan.addColumn(CONTENTS_FAMILY,null);
  ResultScanner scanner=table.getScanner(scan);
  for (  Result r : scanner) {
    for (    KeyValue key : r.sorted()) {
      System.out.println(Bytes.toString(r.getRow()) + ": " + key.toString());
    }
  }
}
