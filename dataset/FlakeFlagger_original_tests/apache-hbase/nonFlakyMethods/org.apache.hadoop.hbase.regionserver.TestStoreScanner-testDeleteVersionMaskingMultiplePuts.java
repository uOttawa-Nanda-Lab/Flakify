public void testDeleteVersionMaskingMultiplePuts() throws IOException {
  long now=System.currentTimeMillis();
  KeyValue[] kvs1=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",now,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",now,KeyValue.Type.Delete,"dont-care")};
  KeyValue[] kvs2=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",now - 500,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",now - 100,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",now,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs1),new KeyValueScanFixture(KeyValue.COMPARATOR,kvs2)};
  StoreScanner scan=new StoreScanner(new Scan(Bytes.toBytes("R1")),CF,Long.MAX_VALUE,KeyValue.COMPARATOR,getCols("a"),scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(1,results.size());
  assertEquals(kvs2[1],results.get(0));
}
