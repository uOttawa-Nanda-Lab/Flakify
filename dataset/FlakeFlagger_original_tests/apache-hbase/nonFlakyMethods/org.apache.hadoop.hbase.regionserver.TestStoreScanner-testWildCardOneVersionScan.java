public void testWildCardOneVersionScan() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",2,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","b",1,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.DeleteColumn,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  StoreScanner scan=new StoreScanner(new Scan(Bytes.toBytes("R1")),CF,Long.MAX_VALUE,KeyValue.COMPARATOR,null,scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(2,results.size());
  assertEquals(kvs[0],results.get(0));
  assertEquals(kvs[1],results.get(1));
}
