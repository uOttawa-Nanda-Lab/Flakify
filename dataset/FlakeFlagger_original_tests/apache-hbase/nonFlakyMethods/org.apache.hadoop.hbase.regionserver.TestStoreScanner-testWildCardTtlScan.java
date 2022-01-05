/** 
 * Test expiration of KeyValues in combination with a configured TTL for a column family (as should be triggered in a major compaction).
 */
public void testWildCardTtlScan() throws IOException {
  long now=System.currentTimeMillis();
  KeyValue[] kvs=new KeyValue[]{KeyValueTestUtil.create("R1","cf","a",now - 1000,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","b",now - 10,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","c",now - 200,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R1","cf","d",now - 10000,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","a",now,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","b",now - 10,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","c",now - 200,KeyValue.Type.Put,"dont-care"),KeyValueTestUtil.create("R2","cf","c",now - 1000,KeyValue.Type.Put,"dont-care")};
  KeyValueScanner[] scanners=new KeyValueScanner[]{new KeyValueScanFixture(KeyValue.COMPARATOR,kvs)};
  Scan scan=new Scan();
  scan.setMaxVersions(1);
  StoreScanner scanner=new StoreScanner(scan,CF,500,KeyValue.COMPARATOR,null,scanners);
  List<KeyValue> results=new ArrayList<KeyValue>();
  assertEquals(true,scanner.next(results));
  assertEquals(2,results.size());
  assertEquals(kvs[1],results.get(0));
  assertEquals(kvs[2],results.get(1));
  results.clear();
  assertEquals(true,scanner.next(results));
  assertEquals(3,results.size());
  assertEquals(kvs[4],results.get(0));
  assertEquals(kvs[5],results.get(1));
  assertEquals(kvs[6],results.get(2));
  results.clear();
  assertEquals(false,scanner.next(results));
}
