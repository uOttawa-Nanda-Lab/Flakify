public void testPrefixFilter() throws Exception {
  long expectedRows=this.numRows / 2;
  long expectedKeys=this.colsPerRow;
  Scan s=new Scan();
  s.setFilter(new PrefixFilter(Bytes.toBytes("testRowOne")));
  verifyScan(s,expectedRows,expectedKeys);
}
