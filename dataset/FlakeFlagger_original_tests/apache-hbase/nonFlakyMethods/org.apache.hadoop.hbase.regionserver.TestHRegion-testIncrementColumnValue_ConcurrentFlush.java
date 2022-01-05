public void testIncrementColumnValue_ConcurrentFlush() throws IOException {
  initHRegion(tableName,getName(),fam1);
  long value=1L;
  long amount=3L;
  Put put=new Put(row);
  put.add(fam1,qual1,Bytes.toBytes(value));
  region.put(put);
  Thread t=new Thread(){
    public void run(){
      try {
        region.flushcache();
      }
 catch (      IOException e) {
        LOG.info("test ICV, got IOE during flushcache()");
      }
    }
  }
;
  t.start();
  long r=region.incrementColumnValue(row,fam1,qual1,amount,true);
  assertEquals(value + amount,r);
  assertICV(row,fam1,qual1,value + amount);
}
