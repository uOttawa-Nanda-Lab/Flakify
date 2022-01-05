public void testDeletedBy_DeleteFamily(){
  byte[] qualifier=Bytes.toBytes("qualifier");
  deleteType=KeyValue.Type.DeleteFamily.getCode();
  sdt.add(qualifier,0,qualifier.length,timestamp,deleteType);
  timestamp-=5;
  boolean ret=sdt.isDeleted(qualifier,0,qualifier.length,timestamp);
  assertEquals(true,ret);
}
