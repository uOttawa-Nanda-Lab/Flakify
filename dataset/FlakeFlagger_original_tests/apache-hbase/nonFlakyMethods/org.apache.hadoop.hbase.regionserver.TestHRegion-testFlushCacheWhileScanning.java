/** 
 * Flushes the cache in a thread while scanning. The tests verify that the scan is coherent - e.g. the returned results are always of the same or later update as the previous results.
 * @throws IOException scan / compact
 * @throws InterruptedException thread join
 */
public void testFlushCacheWhileScanning() throws IOException, InterruptedException {
  byte[] tableName=Bytes.toBytes("testFlushCacheWhileScanning");
  byte[] family=Bytes.toBytes("family");
  int numRows=1000;
  int flushAndScanInterval=10;
  int compactInterval=10 * flushAndScanInterval;
  String method="testFlushCacheWhileScanning";
  initHRegion(tableName,method,family);
  FlushThread flushThread=new FlushThread();
  flushThread.start();
  Scan scan=new Scan();
  scan.addFamily(family);
  scan.setFilter(new SingleColumnValueFilter(family,qual1,CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(5L))));
  int expectedCount=0;
  List<KeyValue> res=new ArrayList<KeyValue>();
  boolean toggle=true;
  for (long i=0; i < numRows; i++) {
    Put put=new Put(Bytes.toBytes(i));
    put.add(family,qual1,Bytes.toBytes(i % 10));
    region.put(put);
    if (i != 0 && i % compactInterval == 0) {
      region.compactStores(true);
    }
    if (i % 10 == 5L) {
      expectedCount++;
    }
    if (i != 0 && i % flushAndScanInterval == 0) {
      res.clear();
      InternalScanner scanner=region.getScanner(scan);
      if (toggle) {
        flushThread.flush();
      }
      while (scanner.next(res))       ;
      if (!toggle) {
        flushThread.flush();
      }
      Assert.assertEquals("i=" + i,expectedCount,res.size());
      toggle=!toggle;
    }
  }
  flushThread.done();
  flushThread.join();
  flushThread.checkNoError();
}
