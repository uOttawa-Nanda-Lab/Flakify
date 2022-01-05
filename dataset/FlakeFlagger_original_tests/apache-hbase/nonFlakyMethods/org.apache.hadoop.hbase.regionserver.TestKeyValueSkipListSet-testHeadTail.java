public void testHeadTail() throws Exception {
  byte[] bytes=Bytes.toBytes(getName());
  byte[] value1=Bytes.toBytes("1");
  byte[] value2=Bytes.toBytes("2");
  final int total=3;
  KeyValue splitter=null;
  for (int i=0; i < total; i++) {
    KeyValue kv=new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value1);
    if (i == 1)     splitter=kv;
    this.kvsls.add(kv);
  }
  SortedSet<KeyValue> tail=this.kvsls.tailSet(splitter);
  assertEquals(2,tail.size());
  SortedSet<KeyValue> head=this.kvsls.headSet(splitter);
  assertEquals(1,head.size());
  for (int i=0; i < total; i++) {
    this.kvsls.add(new KeyValue(bytes,bytes,Bytes.toBytes("" + i),value2));
  }
  tail=this.kvsls.tailSet(splitter);
  assertTrue(Bytes.equals(tail.first().getValue(),value2));
  head=this.kvsls.headSet(splitter);
  assertTrue(Bytes.equals(head.first().getValue(),value2));
}
