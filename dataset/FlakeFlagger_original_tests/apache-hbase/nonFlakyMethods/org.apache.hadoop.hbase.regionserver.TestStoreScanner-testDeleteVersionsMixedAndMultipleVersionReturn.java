public void testDeleteVersionsMixedAndMultipleVersionReturn() throws IOException {
  long now=System.currentTimeMillis();
  KeyValue[] kvs1=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",now,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",now,KeyValue.Type.Delete,"dont-care")};
  KeyValue[] kvs2=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",now - 500,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",now + 500,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",now,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","z",now,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs1),new KeyValueScanFixture(KeyValue.COMPARATOR,kvs2)};
  Scan scanSpec=new Scan(Bytes.toBytes("R1")).setMaxVersions(2);
  StoreScanner scan=new StoreScanner(scanSpec,CF,Long.MAX_VALUE,KeyValue.COMPARATOR,getCols("a"),scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(2,results.size());
  assertEquals(kvs2[1],results.get(0));
  assertEquals(kvs2[0],results.get(1));
}
