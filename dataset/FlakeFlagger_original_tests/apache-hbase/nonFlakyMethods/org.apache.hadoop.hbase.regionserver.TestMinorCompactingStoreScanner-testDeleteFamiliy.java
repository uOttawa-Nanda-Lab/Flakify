public void testDeleteFamiliy() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",100,KeyValue.Type.DeleteFamily,"dont-care"),KeyValueTestUtil.create("R1","cf","b",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","e",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","e",11,KeyValue.Type.DeleteColumn,"dont-care"),KeyValueTestUtil.create("R1","cf","f",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","g",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","g",11,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R1","cf","h",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","i",11,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","a",11,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  InternalScanner scan=new MinorCompactingStoreScanner("cf",KeyValue.COMPARATOR,scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertTrue(scan.next(results));
  assertEquals(11,results.size());
  assertEquals(kvs[0],results.get(0));
  assertEquals(kvs[1],results.get(1));
  assertEquals(kvs[2],results.get(2));
  assertEquals(kvs[3],results.get(3));
  assertEquals(kvs[5],results.get(4));
  assertEquals(kvs[4],results.get(5));
  assertEquals(kvs[6],results.get(6));
  assertEquals(kvs[8],results.get(7));
  assertEquals(kvs[7],results.get(8));
  assertEquals(kvs[9],results.get(9));
  assertEquals(kvs[10],results.get(10));
  results.clear();
  assertFalse(scan.next(results));
  assertEquals(1,results.size());
  assertEquals(kvs[kvs.length - 1],results.get(0));
}
