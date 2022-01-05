/** 
 * Tests forcing split from client and having scanners successfully ride over split.
 * @throws Exception
 * @throws IOException
 */
@Test public void testForceSplit() throws Exception {
  byte[] familyName=HConstants.CATALOG_FAMILY;
  byte[] tableName=Bytes.toBytes("testForceSplit");
  final HTable table=TEST_UTIL.createTable(tableName,familyName);
  byte[] k=new byte[3];
  int rowCount=0;
  for (byte b1='a'; b1 < 'z'; b1++) {
    for (byte b2='a'; b2 < 'z'; b2++) {
      for (byte b3='a'; b3 < 'z'; b3++) {
        k[0]=b1;
        k[1]=b2;
        k[2]=b3;
        Put put=new Put(k);
        put.add(familyName,new byte[0],k);
        table.put(put);
        rowCount++;
      }
    }
  }
  Map<HRegionInfo,HServerAddress> m=table.getRegionsInfo();
  System.out.println("Initial regions (" + m.size() + "): "+ m);
  assertTrue(m.size() == 1);
  Scan scan=new Scan();
  ResultScanner scanner=table.getScanner(scan);
  int rows=0;
  for (  @SuppressWarnings("unused") Result result : scanner) {
    rows++;
  }
  scanner.close();
  assertEquals(rowCount,rows);
  scan=new Scan();
  scanner=table.getScanner(scan);
  scanner.next();
  final AtomicInteger count=new AtomicInteger(0);
  Thread t=new Thread("CheckForSplit"){
    public void run(){
      for (int i=0; i < 20; i++) {
        try {
          sleep(1000);
        }
 catch (        InterruptedException e) {
          continue;
        }
        Map<HRegionInfo,HServerAddress> regions=null;
        try {
          regions=table.getRegionsInfo();
        }
 catch (        IOException e) {
          e.printStackTrace();
        }
        if (regions == null)         continue;
        count.set(regions.size());
        if (count.get() >= 2)         break;
        LOG.debug("Cycle waiting on split");
      }
    }
  }
;
  t.start();
  admin.split(Bytes.toString(tableName));
  t.join();
  rows=1;
  for (  @SuppressWarnings("unused") Result result : scanner) {
    rows++;
    if (rows > rowCount) {
      scanner.close();
      assertTrue("Scanned more than expected (" + rowCount + ")",false);
    }
  }
  scanner.close();
  assertEquals(rowCount,rows);
}
