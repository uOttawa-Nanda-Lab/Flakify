public void testTokenBucket() throws Exception {
  HTableTokenBucket tb=new HTableTokenBucket(conf,TABLE,Bytes.toBytes(USER_TOKEN));
  int last=0;
  for (int i=0; i <= 5 && last < 10; i++) {
    int avail=tb.available();
    assertTrue("bucket did not increment",avail > last);
    assertTrue("bucket updated too quickly",avail - last < 3);
    last=avail;
    Thread.sleep(2000);
  }
  assertTrue("bucket did not fill",last >= 10);
  assertTrue("bucket overfilled",last == 10);
}
