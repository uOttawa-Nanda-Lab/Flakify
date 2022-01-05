public void testIterator() throws Exception {
  byte[] bytes=Bytes.toBytes(getName());
  byte[] value1=Bytes.toBytes("1");
  byte[] value2=Bytes.toBytes("2");
  final int total=3;
  for (int i=0; i < total; i++) {
    this.kvsls.add(new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value1));
  }
  int count=0;
  for (  KeyValue kv : this.kvsls) {
    assertEquals("" + count,Bytes.toString(kv.getQualifier()));
    assertTrue(Bytes.equals(kv.getValue(),value1));
    count++;
  }
  assertEquals(total,count);
  for (int i=0; i < total; i++) {
    this.kvsls.add(new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value2));
  }
  count=0;
  for (  KeyValue kv : this.kvsls) {
    assertEquals("" + count,Bytes.toString(kv.getQualifier()));
    assertTrue(Bytes.equals(kv.getValue(),value2));
    count++;
  }
  assertEquals(total,count);
}
