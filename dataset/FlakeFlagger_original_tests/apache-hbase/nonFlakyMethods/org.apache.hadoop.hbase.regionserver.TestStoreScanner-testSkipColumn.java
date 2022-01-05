public void testSkipColumn() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","b",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","e",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","f",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","g",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","h",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","i",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","a",11,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  StoreScanner scan=new StoreScanner(new Scan(),CF,Long.MAX_VALUE,KeyValue.COMPARATOR,getCols("a","d"),scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(2,results.size());
  assertEquals(kvs[0],results.get(0));
  assertEquals(kvs[3],results.get(1));
  results.clear();
  assertEquals(true,scan.next(results));
  assertEquals(1,results.size());
  assertEquals(kvs[kvs.length - 1],results.get(0));
  results.clear();
  assertEquals(false,scan.next(results));
}
