/** 
 * Writes very wide records and gets the latest row every time.. Flushes and compacts the region every now and then to keep things realistic.
 * @throws IOException          by flush / scan / compaction
 * @throws InterruptedException when joining threads
 */
public void testWritesWhileGetting() throws IOException, InterruptedException {
  byte[] tableName=Bytes.toBytes("testWritesWhileScanning");
  int testCount=200;
  int numRows=1;
  int numFamilies=10;
  int numQualifiers=100;
  int flushInterval=10;
  int compactInterval=10 * flushInterval;
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
  Get get=new Get(Bytes.toBytes("row0"));
  Result result=null;
  int expectedCount=numFamilies * numQualifiers;
  long prevTimestamp=0L;
  for (int i=0; i < testCount; i++) {
    if (i != 0 && i % compactInterval == 0) {
      region.compactStores(true);
    }
    if (i != 0 && i % flushInterval == 0) {
      flushThread.flush();
    }
    boolean previousEmpty=result == null || result.isEmpty();
    result=region.get(get,null);
    if (!result.isEmpty() || !previousEmpty || i > compactInterval) {
      Assert.assertEquals("i=" + i,expectedCount,result.size());
      long timestamp=0;
      for (      KeyValue kv : result.sorted()) {
        if (Bytes.equals(kv.getFamily(),families[0]) && Bytes.equals(kv.getQualifier(),qualifiers[0])) {
          timestamp=kv.getTimestamp();
        }
      }
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
