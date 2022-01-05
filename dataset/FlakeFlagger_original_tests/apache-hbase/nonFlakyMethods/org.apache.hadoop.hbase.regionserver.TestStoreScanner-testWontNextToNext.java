/** 
 * Test test shows exactly how the matcher's return codes confuses the StoreScanner and prevent it from doing the right thing.  Seeking once, then nexting twice should return R1, then R2, but in this case it doesnt.
 * @throws IOException
 */
public void testWontNextToNext() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",2,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","a",1,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  Scan scanSpec=new Scan(Bytes.toBytes("R1"));
  StoreScanner scan=new StoreScanner(scanSpec,CF,Long.MAX_VALUE,KeyValue.COMPARATOR,getCols("a"),scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  scan.next(results);
  assertEquals(1,results.size());
  assertEquals(kvs[0],results.get(0));
  results.clear();
  scan.next(results);
  assertEquals(1,results.size());
  assertEquals(kvs[2],results.get(0));
  results.clear();
  scan.next(results);
  assertEquals(0,results.size());
}
