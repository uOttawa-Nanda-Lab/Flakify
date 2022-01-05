public void testCheckAndPut_WithWrongValue() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] qf1=Bytes.toBytes("qualifier");
  byte[] val1=Bytes.toBytes("value1");
  byte[] val2=Bytes.toBytes("value2");
  Integer lockId=null;
  String method=this.getName();
  initHRegion(tableName,method,fam1);
  Put put=new Put(row1);
  put.add(fam1,qf1,val1);
  region.put(put);
  boolean res=region.checkAndPut(row1,fam1,qf1,val2,put,lockId,true);
  assertEquals(false,res);
}
