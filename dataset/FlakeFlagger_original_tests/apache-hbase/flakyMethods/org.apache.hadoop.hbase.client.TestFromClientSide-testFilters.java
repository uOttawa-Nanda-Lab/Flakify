@Test public void testFilters() throws Exception {
  byte[] TABLE=Bytes.toBytes("testFilters");
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY);
  byte[][] ROWS=makeN(ROW,10);
  byte[][] QUALIFIERS={Bytes.toBytes("col0-<d2v1>-<d3v2>"),Bytes.toBytes("col1-<d2v1>-<d3v2>"),Bytes.toBytes("col2-<d2v1>-<d3v2>"),Bytes.toBytes("col3-<d2v1>-<d3v2>"),Bytes.toBytes("col4-<d2v1>-<d3v2>"),Bytes.toBytes("col5-<d2v1>-<d3v2>"),Bytes.toBytes("col6-<d2v1>-<d3v2>"),Bytes.toBytes("col7-<d2v1>-<d3v2>"),Bytes.toBytes("col8-<d2v1>-<d3v2>"),Bytes.toBytes("col9-<d2v1>-<d3v2>")};
  for (int i=0; i < 10; i++) {
    Put put=new Put(ROWS[i]);
    put.add(FAMILY,QUALIFIERS[i],VALUE);
    ht.put(put);
  }
  Scan scan=new Scan();
  scan.addFamily(FAMILY);
  Filter filter=new QualifierFilter(CompareOp.EQUAL,new RegexStringComparator("col[1-5]"));
  scan.setFilter(filter);
  ResultScanner scanner=ht.getScanner(scan);
  int expectedIndex=1;
  for (  Result result : ht.getScanner(scan)) {
    assertEquals(result.size(),1);
    assertTrue(Bytes.equals(result.raw()[0].getRow(),ROWS[expectedIndex]));
    assertTrue(Bytes.equals(result.raw()[0].getQualifier(),QUALIFIERS[expectedIndex]));
    expectedIndex++;
  }
  assertEquals(expectedIndex,6);
  scanner.close();
}
