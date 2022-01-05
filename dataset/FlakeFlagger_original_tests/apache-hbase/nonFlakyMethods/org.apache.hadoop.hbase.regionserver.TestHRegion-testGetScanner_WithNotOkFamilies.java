public void testGetScanner_WithNotOkFamilies() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] fam2=Bytes.toBytes("fam2");
  byte[][] families={fam1};
  String method=this.getName();
  initHRegion(tableName,method,families);
  Scan scan=new Scan();
  scan.addFamily(fam2);
  boolean ok=false;
  try {
    region.getScanner(scan);
  }
 catch (  Exception e) {
    ok=true;
  }
  assertTrue("Families could not be found in Region",ok);
}
