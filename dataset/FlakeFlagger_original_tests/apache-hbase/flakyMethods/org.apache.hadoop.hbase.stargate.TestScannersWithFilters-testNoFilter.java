public void testNoFilter() throws Exception {
  long expectedRows=this.numRows;
  long expectedKeys=this.colsPerRow;
  Scan s=new Scan();
  verifyScan(s,expectedRows,expectedKeys);
  s=new Scan();
  s.addFamily(FAMILIES[0]);
  verifyScan(s,expectedRows,expectedKeys / 2);
}
