/** 
 * Test the case where there is a delete row 'in front of' the next row, the scanner will move to the next row.
 */
public void testDeletedRowThenGoodRow() throws IOException {
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","a",1,KeyValue.Type.Delete,"dont-care"),KeyValueTestUtil.create("R2","cf","a",20,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  Scan scanSpec=new Scan(Bytes.toBytes("R1"));
  StoreScanner scan=new StoreScanner(scanSpec,CF,Long.MAX_VALUE,KeyValue.COMPARATOR,getCols("a"),scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scan.next(results));
  assertEquals(0,results.size());
  assertEquals(true,scan.next(results));
  assertEquals(1,results.size());
  assertEquals(kvs[2],results.get(0));
  assertEquals(false,scan.next(results));
}
