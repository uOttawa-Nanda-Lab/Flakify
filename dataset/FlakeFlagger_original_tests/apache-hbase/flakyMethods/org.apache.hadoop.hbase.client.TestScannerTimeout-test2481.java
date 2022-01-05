/** 
 * Test that we do get a ScannerTimeoutException
 * @throws Exception
 */
@Test public void test2481() throws Exception {
  int initialCount=10;
  HTable t=TEST_UTIL.createTable(Bytes.toBytes("t"),someBytes);
  for (int i=0; i < initialCount; i++) {
    Put put=new Put(Bytes.toBytes(i));
    put.add(someBytes,someBytes,someBytes);
    t.put(put);
  }
  Scan scan=new Scan();
  ResultScanner r=t.getScanner(scan);
  int count=0;
  try {
    Result res=r.next();
    while (res != null) {
      count++;
      if (count == 5) {
        Thread.sleep(1500);
      }
      res=r.next();
    }
  }
 catch (  ScannerTimeoutException e) {
    LOG.info("Got the timeout " + e.getMessage(),e);
    return;
  }
  fail("We should be timing out");
}
