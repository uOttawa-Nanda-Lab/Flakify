public void testWildCardScannerUnderDeletes() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",2,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.DeleteColumn,"dont-care"),KeyValueTestUtil.create("R1","cf","b",2,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","b",1,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",10,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R1","cf","c",10,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",9,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",10,KeyValue.Type.DeleteColumn,"dont-care"),KeyValueTestUtil.create("R1","cf","d",9,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",8,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  StoreScanner scan=new StoreScanner(new Scan().setMaxVersions(2),CF,Long.MAX_VALUE,KeyValue.COMPARATOR,null,scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(5,results.size());
  assertEquals(kvs[0],results.get(0));
  assertEquals(kvs[2],results.get(1));
  assertEquals(kvs[3],results.get(2));
  assertEquals(kvs[6],results.get(3));
  assertEquals(kvs[7],results.get(4));
}
