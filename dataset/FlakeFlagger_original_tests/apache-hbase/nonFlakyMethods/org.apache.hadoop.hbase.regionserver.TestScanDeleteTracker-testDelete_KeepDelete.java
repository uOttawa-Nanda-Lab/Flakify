public void testDelete_KeepDelete(){
  byte[] qualifier=Bytes.toBytes("qualifier");
  deleteType=KeyValue.Type.Delete.getCode();
  sdt.add(qualifier,0,qualifier.length,timestamp,deleteType);
  sdt.isDeleted(qualifier,0,qualifier.length,timestamp);
  assertEquals(false,sdt.isEmpty());
}
