public void testDelete_CheckTimestampUpdated() throws IOException {
  byte[] row1=Bytes.toBytes("row1");
  byte[] col1=Bytes.toBytes("col1");
  byte[] col2=Bytes.toBytes("col2");
  byte[] col3=Bytes.toBytes("col3");
  String method=this.getName();
  initHRegion(tableName,method,fam1);
  List<KeyValue> kvs=new ArrayList<KeyValue>();
  kvs.add(new KeyValue(row1,fam1,col1,null));
  kvs.add(new KeyValue(row1,fam1,col2,null));
  kvs.add(new KeyValue(row1,fam1,col3,null));
  Map<byte[],List<KeyValue>> deleteMap=new HashMap<byte[],List<KeyValue>>();
  deleteMap.put(fam1,kvs);
  region.delete(deleteMap,true);
  long now=System.currentTimeMillis();
  KeyValue firstKv=region.getStore(fam1).memstore.kvset.first();
  assertTrue(firstKv.getTimestamp() <= now);
  now=firstKv.getTimestamp();
  for (  KeyValue kv : region.getStore(fam1).memstore.kvset) {
    assertTrue(kv.getTimestamp() <= now);
    now=kv.getTimestamp();
  }
}
