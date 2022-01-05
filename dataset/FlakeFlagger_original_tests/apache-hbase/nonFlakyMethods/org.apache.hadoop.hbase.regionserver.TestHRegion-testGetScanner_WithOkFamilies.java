public void testGetScanner_WithOkFamilies() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] fam2=Bytes.toBytes("fam2");
  byte[][] families={fam1,fam2};
  String method=this.getName();
  initHRegion(tableName,method,families);
  Scan scan=new Scan();
  scan.addFamily(fam1);
  scan.addFamily(fam2);
  try {
    region.getScanner(scan);
  }
 catch (  Exception e) {
    assertTrue("Families could not be found in Region",false);
  }
}
