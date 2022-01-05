public void testDelete_DeleteColumn(){
  byte[] qualifier=Bytes.toBytes("qualifier");
  deleteType=KeyValue.Type.Delete.getCode();
  sdt.add(qualifier,0,qualifier.length,timestamp,deleteType);
  timestamp-=5;
  deleteType=KeyValue.Type.DeleteColumn.getCode();
  sdt.add(qualifier,0,qualifier.length,timestamp,deleteType);
  timestamp-=5;
  boolean ret=sdt.isDeleted(qualifier,0,qualifier.length,timestamp);
  assertEquals(true,ret);
}
