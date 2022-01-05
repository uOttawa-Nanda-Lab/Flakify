public void testDeleteFamily() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",100,KeyValue.Type.DeleteFamily,"dont-care"),KeyValueTestUtil.create("R1","cf","b",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","e",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","e",11,KeyValue.Type.DeleteColumn,"dont-care"),KeyValueTestUtil.create("R1","cf","f",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","g",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","g",11,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R1","cf","h",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","i",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","a",11,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  StoreScanner scan=new StoreScanner(new Scan().setMaxVersions(Integer.MAX_VALUE),CF,Long.MAX_VALUE,KeyValue.COMPARATOR,null,scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(0,results.size());
  assertEquals(true,scan.next(results));
  assertEquals(1,results.size());
  assertEquals(kvs[kvs.length - 1],results.get(0));
  assertEquals(false,scan.next(results));
}
