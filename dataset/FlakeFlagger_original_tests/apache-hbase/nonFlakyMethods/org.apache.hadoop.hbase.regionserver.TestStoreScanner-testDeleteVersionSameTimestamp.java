public void testDeleteVersionSameTimestamp() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.Delete,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  Scan scanSpec=new Scan(Bytes.toBytes("R1"));
  StoreScanner scan=new StoreScanner(scanSpec,CF,Long.MAX_VALUE,KeyValue.COMPARATOR,getCols("a"),scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertFalse(scan.next(results));
  assertEquals(0,results.size());
}
