/** 
 * Test memstore snapshot happening while scanning.
 * @throws IOException
 */
public void testScanAcrossSnapshot() throws IOException {
  int rowCount=addRows(this.memstore);
  KeyValueScanner[] memstorescanners=this.memstore.getScanners();
  Scan scan=new Scan();
  List<KeyValue> result=new ArrayList<KeyValue>();
  StoreScanner s=new StoreScanner(scan,null,HConstants.LATEST_TIMESTAMP,this.memstore.comparator,null,memstorescanners);
  int count=0;
  try {
    while (s.next(result)) {
      LOG.info(result);
      count++;
      assertEquals(rowCount,result.size());
      result.clear();
    }
  }
  finally {
    s.close();
  }
  assertEquals(rowCount,count);
  for (int i=0; i < memstorescanners.length; i++) {
    memstorescanners[0].close();
  }
  memstorescanners=this.memstore.getScanners();
  s=new StoreScanner(scan,null,HConstants.LATEST_TIMESTAMP,this.memstore.comparator,null,memstorescanners);
  count=0;
  try {
    while (s.next(result)) {
      LOG.info(result);
      assertTrue(Bytes.compareTo(Bytes.toBytes(count),result.get(0).getRow()) == 0);
      count++;
      assertEquals(rowCount,result.size());
      if (count == 2) {
        this.memstore.snapshot();
        LOG.info("Snapshotted");
      }
      result.clear();
    }
  }
  finally {
    s.close();
  }
  assertEquals(rowCount,count);
  for (int i=0; i < memstorescanners.length; i++) {
    memstorescanners[0].close();
  }
  memstorescanners=this.memstore.getScanners();
  long ts=System.currentTimeMillis();
  s=new StoreScanner(scan,null,HConstants.LATEST_TIMESTAMP,this.memstore.comparator,null,memstorescanners);
  count=0;
  int snapshotIndex=5;
  try {
    while (s.next(result)) {
      LOG.info(result);
      assertTrue(Bytes.compareTo(Bytes.toBytes(count),result.get(0).getRow()) == 0);
      assertEquals("count=" + count + ", result="+ result,rowCount,result.size());
      count++;
      if (count == snapshotIndex) {
        this.memstore.snapshot();
        this.memstore.clearSnapshot(this.memstore.getSnapshot());
        addRows(this.memstore,ts);
        LOG.info("Snapshotted, cleared it and then added values");
      }
      result.clear();
    }
  }
  finally {
    s.close();
  }
  assertEquals(rowCount,count);
}
