/** 
 * Writes very wide records and scans for the latest every time.. Flushes and compacts the region every now and then to keep things realistic.
 * @throws IOException          by flush / scan / compaction
 * @throws InterruptedException when joining threads
 */
public void testWritesWhileScanning() throws IOException, InterruptedException {
  byte[] tableName=Bytes.toBytes("testWritesWhileScanning");
  int testCount=100;
  int numRows=1;
  int numFamilies=10;
  int numQualifiers=100;
  int flushInterval=7;
  int compactInterval=5 * flushInterval;
  byte[][] families=new byte[numFamilies][];
  for (int i=0; i < numFamilies; i++) {
    families[i]=Bytes.toBytes("family" + i);
  }
  byte[][] qualifiers=new byte[numQualifiers][];
  for (int i=0; i < numQualifiers; i++) {
    qualifiers[i]=Bytes.toBytes("qual" + i);
  }
  String method="testWritesWhileScanning";
  initHRegion(tableName,method,families);
  PutThread putThread=new PutThread(numRows,families,qualifiers);
  putThread.start();
  FlushThread flushThread=new FlushThread();
  flushThread.start();
  Scan scan=new Scan();
  scan.setFilter(new RowFilter(CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("row0"))));
  int expectedCount=numFamilies * numQualifiers;
  List<KeyValue> res=new ArrayList<KeyValue>();
  long prevTimestamp=0L;
  for (int i=0; i < testCount; i++) {
    if (i != 0 && i % compactInterval == 0) {
      region.compactStores(true);
    }
    if (i != 0 && i % flushInterval == 0) {
      flushThread.flush();
    }
    boolean previousEmpty=res.isEmpty();
    res.clear();
    InternalScanner scanner=region.getScanner(scan);
    while (scanner.next(res))     ;
    if (!res.isEmpty() || !previousEmpty || i > compactInterval) {
      Assert.assertEquals("i=" + i,expectedCount,res.size());
      long timestamp=res.get(0).getTimestamp();
      Assert.assertTrue(timestamp >= prevTimestamp);
      prevTimestamp=timestamp;
    }
  }
  putThread.done();
  putThread.join();
  putThread.checkNoError();
  flushThread.done();
  flushThread.join();
  flushThread.checkNoError();
}
