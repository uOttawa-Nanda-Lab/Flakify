public void testTokenBucketConfig() throws Exception {
  HTableTokenBucket tb=new HTableTokenBucket(conf,TABLE,Bytes.toBytes(USER_TOKEN));
  assertEquals(tb.getRate(),RATE);
  assertEquals(tb.getSize(),SIZE);
}
