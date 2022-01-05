public void testDeleteColumn_Delete(){
  byte[] qualifier=Bytes.toBytes("qualifier");
  deleteType=KeyValue.Type.DeleteColumn.getCode();
  sdt.add(qualifier,0,qualifier.length,timestamp,deleteType);
  qualifier=Bytes.toBytes("qualifier1");
  deleteType=KeyValue.Type.Delete.getCode();
  sdt.add(qualifier,0,qualifier.length,timestamp,deleteType);
  boolean ret=sdt.isDeleted(qualifier,0,qualifier.length,timestamp);
  assertEquals(true,ret);
}
