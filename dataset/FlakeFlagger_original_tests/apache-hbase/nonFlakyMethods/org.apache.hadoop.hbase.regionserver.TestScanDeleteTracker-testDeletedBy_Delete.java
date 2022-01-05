public void testDeletedBy_Delete(){
  byte[] qualifier=Bytes.toBytes("qualifier");
  deleteType=KeyValue.Type.Delete.getCode();
  sdt.add(qualifier,0,qualifier.length,timestamp,deleteType);
  boolean ret=sdt.isDeleted(qualifier,0,qualifier.length,timestamp);
  assertEquals(true,ret);
}
