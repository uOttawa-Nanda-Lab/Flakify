public void testSplitRegion() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] qualifier=Bytes.toBytes("qualifier");
  HBaseConfiguration hc=initSplit();
  int numRows=10;
  byte[][] families={fam1,fam3};
  String method=this.getName();
  initHRegion(tableName,method,hc,families);
  int startRow=100;
  putData(startRow,numRows,qualifier,families);
  int splitRow=startRow + numRows;
  putData(splitRow,numRows,qualifier,families);
  region.flushcache();
  HRegion[] regions=null;
  try {
    regions=region.splitRegion(Bytes.toBytes("" + splitRow));
    for (int i=0; i < regions.length; i++) {
      regions[i]=openClosedRegion(regions[i]);
    }
    assertEquals(2,regions.length);
    verifyData(regions[0],startRow,numRows,qualifier,families);
    verifyData(regions[1],splitRow,numRows,qualifier,families);
  }
  finally {
    if (region != null) {
      region.close();
      region.getLog().closeAndDelete();
    }
  }
}
