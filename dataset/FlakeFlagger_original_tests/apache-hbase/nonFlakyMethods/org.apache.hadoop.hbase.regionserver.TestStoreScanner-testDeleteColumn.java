public void testDeleteColumn() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",10,KeyValue.Type.DeleteColumn,"dont-care"),KeyValueTestUtil.create("R1","cf","a",9,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R1","cf","a",8,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","b",5,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  StoreScanner scan=new StoreScanner(new Scan(),CF,Long.MAX_VALUE,KeyValue.COMPARATOR,null,scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(1,results.size());
  assertEquals(kvs[3],results.get(0));
}
