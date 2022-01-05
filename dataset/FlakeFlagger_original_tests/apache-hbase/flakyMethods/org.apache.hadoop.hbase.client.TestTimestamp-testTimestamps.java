/** 
 * Basic test of timestamps. Do the above tests from client side.
 * @throws IOException
 */
public void testTimestamps() throws IOException {
  HTable t=createTable();
  Incommon incommon=new HTableIncommon(t);
  TimestampTestBase.doTestDelete(incommon,new FlushCache(){
    public void flushcache() throws IOException {
      cluster.flushcache();
    }
  }
);
  TimestampTestBase.doTestTimestampScanning(incommon,new FlushCache(){
    public void flushcache() throws IOException {
      cluster.flushcache();
    }
  }
);
}
