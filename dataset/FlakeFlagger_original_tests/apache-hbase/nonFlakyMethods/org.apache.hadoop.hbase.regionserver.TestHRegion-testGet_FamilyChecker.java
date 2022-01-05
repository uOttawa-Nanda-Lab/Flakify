public void testGet_FamilyChecker() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] fam2=Bytes.toBytes("False");
  byte[] col1=Bytes.toBytes("col1");
  String method=this.getName();
  initHRegion(tableName,method,fam1);
  Get get=new Get(row1);
  get.addColumn(fam2,col1);
  try {
    region.get(get,null);
  }
 catch (  NoSuchColumnFamilyException e) {
    assertFalse(false);
    return;
  }
  assertFalse(true);
}
