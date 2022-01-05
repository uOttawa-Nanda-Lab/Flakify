public void testLocks() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[][] families={fam1,fam2,fam3};
  HBaseConfiguration hc=initSplit();
  String method=this.getName();
  initHRegion(tableName,method,hc,families);
  final int threadCount=10;
  final int lockCount=10;
  List<Thread> threads=new ArrayList<Thread>(threadCount);
  for (int i=0; i < threadCount; i++) {
    threads.add(new Thread(Integer.toString(i)){
      @Override public void run(){
        Integer[] lockids=new Integer[lockCount];
        for (int i=0; i < lockCount; i++) {
          try {
            byte[] rowid=Bytes.toBytes(Integer.toString(i));
            lockids[i]=region.obtainRowLock(rowid);
            assertEquals(rowid,region.getRowFromLock(lockids[i]));
            LOG.debug(getName() + " locked " + Bytes.toString(rowid));
          }
 catch (          IOException e) {
            e.printStackTrace();
          }
        }
        LOG.debug(getName() + " set " + Integer.toString(lockCount)+ " locks");
        for (int i=lockCount - 1; i >= 0; i--) {
          region.releaseRowLock(lockids[i]);
          LOG.debug(getName() + " unlocked " + i);
        }
        LOG.debug(getName() + " released " + Integer.toString(lockCount)+ " locks");
      }
    }
);
  }
  for (  Thread t : threads) {
    t.start();
  }
  for (  Thread t : threads) {
    while (t.isAlive()) {
      try {
        Thread.sleep(1);
      }
 catch (      InterruptedException e) {
      }
    }
  }
  LOG.info("locks completed.");
}
