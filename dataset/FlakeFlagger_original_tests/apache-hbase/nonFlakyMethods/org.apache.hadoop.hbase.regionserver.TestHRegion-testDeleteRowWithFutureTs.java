public void testDeleteRowWithFutureTs() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] fam=Bytes.toBytes("info");
  byte[][] families={fam};
  String method=this.getName();
  initHRegion(tableName,method,families);
  byte[] row=Bytes.toBytes("table_name");
  byte[] serverinfo=Bytes.toBytes("serverinfo");
  Put put=new Put(row);
  put.add(fam,serverinfo,HConstants.LATEST_TIMESTAMP - 5,Bytes.toBytes("value"));
  region.put(put);
  Delete delete=new Delete(row);
  region.delete(delete,null,true);
  Get get=new Get(row).addColumn(fam,serverinfo);
  Result result=region.get(get,null);
  assertEquals(1,result.size());
  delete=new Delete(row,HConstants.LATEST_TIMESTAMP - 3,null);
  region.delete(delete,null,true);
  get=new Get(row).addColumn(fam,serverinfo);
  result=region.get(get,null);
  assertEquals(0,result.size());
}
