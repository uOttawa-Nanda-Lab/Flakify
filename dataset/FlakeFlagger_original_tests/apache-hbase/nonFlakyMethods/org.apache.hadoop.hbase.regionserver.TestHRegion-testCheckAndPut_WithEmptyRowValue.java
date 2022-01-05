public void testCheckAndPut_WithEmptyRowValue() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] qf1=Bytes.toBytes("qualifier");
  byte[] emptyVal=new byte[]{};
  byte[] val1=Bytes.toBytes("value1");
  byte[] val2=Bytes.toBytes("value2");
  Integer lockId=null;
  String method=this.getName();
  initHRegion(tableName,method,fam1);
  Put put=new Put(row1);
  put.add(fam1,qf1,val1);
  boolean res=region.checkAndPut(row1,fam1,qf1,emptyVal,put,lockId,true);
  assertTrue(res);
  res=region.checkAndPut(row1,fam1,qf1,emptyVal,put,lockId,true);
  assertFalse(res);
  put=new Put(row1);
  put.add(fam1,qf1,val2);
  res=region.checkAndPut(row1,fam1,qf1,val1,put,lockId,true);
  assertTrue(res);
}
