@Test public void testSuperSimple() throws Exception {
  byte[] TABLE=Bytes.toBytes("testSuperSimple");
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY);
  Put put=new Put(ROW);
  put.add(FAMILY,QUALIFIER,VALUE);
  ht.put(put);
  Scan scan=new Scan();
  scan.addColumn(FAMILY,TABLE);
  ResultScanner scanner=ht.getScanner(scan);
  Result result=scanner.next();
  assertTrue("Expected null result",result == null);
  scanner.close();
  System.out.println("Done.");
}
