public void testGet_Empty() throws IOException {
  byte[] tableName=Bytes.toBytes("emptytable");
  byte[] row=Bytes.toBytes("row");
  byte[] fam=Bytes.toBytes("fam");
  String method=this.getName();
  initHRegion(tableName,method,fam);
  Get get=new Get(row);
  get.addFamily(fam);
  Result r=region.get(get,null);
  assertTrue(r.isEmpty());
}
