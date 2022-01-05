public void testDeleteCompare_Multiple(){
  Set<KeyValue> memstore=new TreeSet<KeyValue>(KeyValue.COMPARATOR);
  memstore.add(KeyValueTestUtil.create("row11","fam","col1",1,"d-c"));
  memstore.add(KeyValueTestUtil.create("row21","fam","col1",4,"d-c"));
  memstore.add(KeyValueTestUtil.create("row21","fam","col1",3,"d-c"));
  memstore.add(KeyValueTestUtil.create("row21","fam","col1",2,"d-c"));
  memstore.add(KeyValueTestUtil.create("row21","fam","col1",1,KeyValue.Type.Delete,"dont-care"));
  memstore.add(KeyValueTestUtil.create("row31","fam","col1",1,"dont-care"));
  List<DeleteCode> expected=new ArrayList<DeleteCode>();
  expected.add(DeleteCode.SKIP);
  expected.add(DeleteCode.DELETE);
  expected.add(DeleteCode.DELETE);
  expected.add(DeleteCode.DELETE);
  expected.add(DeleteCode.DELETE);
  expected.add(DeleteCode.DONE);
  KeyValue delete=KeyValueTestUtil.create("row21","fam","col1",5,KeyValue.Type.DeleteColumn,"dont-care");
  byte[] deleteBuffer=delete.getBuffer();
  int deleteRowOffset=delete.getRowOffset();
  short deleteRowLen=delete.getRowLength();
  int deleteQualifierOffset=delete.getQualifierOffset();
  int deleteQualifierLen=delete.getQualifierLength();
  int deleteTimestampOffset=deleteQualifierOffset + deleteQualifierLen;
  byte deleteType=deleteBuffer[deleteTimestampOffset + Bytes.SIZEOF_LONG];
  List<DeleteCode> actual=new ArrayList<DeleteCode>();
  for (  KeyValue mem : memstore) {
    actual.add(DeleteCompare.deleteCompare(mem,deleteBuffer,deleteRowOffset,deleteRowLen,deleteQualifierOffset,deleteQualifierLen,deleteTimestampOffset,deleteType,KeyValue.KEY_COMPARATOR));
  }
  assertEquals(expected.size(),actual.size());
  for (int i=0; i < expected.size(); i++) {
    assertEquals(expected.get(i),actual.get(i));
  }
}
