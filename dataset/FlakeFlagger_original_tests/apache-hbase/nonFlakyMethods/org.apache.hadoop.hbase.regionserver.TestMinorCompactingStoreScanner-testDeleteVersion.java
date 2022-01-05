public void testDeleteVersion() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",15,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",10,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R1","cf","a",10,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  InternalScanner scan=new MinorCompactingStoreScanner("cf",KeyValue.COMPARATOR,scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertFalse(scan.next(results));
  assertEquals(3,results.size());
  assertEquals(kvs[0],results.get(0));
  assertEquals(kvs[1],results.get(1));
  assertEquals(kvs[2],results.get(2));
}
