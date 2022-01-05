public void testCheckAndPut_ThatPutWasWritten() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] fam2=Bytes.toBytes("fam2");
  byte[] qf1=Bytes.toBytes("qualifier");
  byte[] val1=Bytes.toBytes("value1");
  byte[] val2=Bytes.toBytes("value2");
  Integer lockId=null;
  byte[][] families={fam1,fam2};
  String method=this.getName();
  initHRegion(tableName,method,families);
  Put put=new Put(row1);
  put.add(fam1,qf1,val1);
  region.put(put);
  long ts=System.currentTimeMillis();
  KeyValue kv=new KeyValue(row1,fam2,qf1,ts,KeyValue.Type.Put,val2);
  put=new Put(row1);
  put.add(kv);
  Store store=region.getStore(fam1);
  store.memstore.kvset.size();
  boolean res=region.checkAndPut(row1,fam1,qf1,val1,put,lockId,true);
  assertEquals(true,res);
  store.memstore.kvset.size();
  Get get=new Get(row1);
  get.addColumn(fam2,qf1);
  KeyValue[] actual=region.get(get,null).raw();
  KeyValue[] expected={kv};
  assertEquals(expected.length,actual.length);
  for (int i=0; i < actual.length; i++) {
    assertEquals(expected[i],actual[i]);
  }
}
