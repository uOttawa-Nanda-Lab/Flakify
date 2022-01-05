public void testKeyValueScanFixture(){
  KeyValue kvs[]=new KeyValue[]{KeyValueTestUtil.create("RowA","family","qf1",1,KeyValue.Type.Put,"value-1"),KeyValueTestUtil.create("RowA","family","qf2",1,KeyValue.Type.Put,"value-2"),KeyValueTestUtil.create("RowB","family","qf1",10,KeyValue.Type.Put,"value-10")};
  KeyValueScanner scan=new KeyValueScanFixture(KeyValue.COMPARATOR,kvs);
  assertNull(scan.peek());
  KeyValue kv=KeyValue.createFirstOnRow(Bytes.toBytes("RowA"));
  assertTrue(scan.seek(kv));
  KeyValue res=scan.peek();
  assertEquals(kvs[0],res);
  kv=KeyValue.createFirstOnRow(Bytes.toBytes("RowB"));
  assertTrue(scan.seek(kv));
  res=scan.peek();
  assertEquals(kvs[2],res);
  kv=KeyValue.createFirstOnRow(Bytes.toBytes("RowA"));
  assertTrue(scan.seek(kv));
  assertEquals(kvs[0],scan.peek());
  assertEquals(kvs[0],scan.next());
  assertEquals(kvs[1],scan.peek());
  assertEquals(kvs[1],scan.next());
  assertEquals(kvs[2],scan.peek());
  assertEquals(kvs[2],scan.next());
  assertEquals(null,scan.peek());
  assertEquals(null,scan.next());
}
