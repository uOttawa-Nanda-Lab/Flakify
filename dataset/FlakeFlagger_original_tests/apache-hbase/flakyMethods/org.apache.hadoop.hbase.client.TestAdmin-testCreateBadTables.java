@Test public void testCreateBadTables() throws IOException {
  String msg=null;
  try {
    this.admin.createTable(HTableDescriptor.ROOT_TABLEDESC);
  }
 catch (  IllegalArgumentException e) {
    msg=e.toString();
  }
  assertTrue("Unexcepted exception message " + msg,msg != null && msg.startsWith(IllegalArgumentException.class.getName()) && msg.contains(HTableDescriptor.ROOT_TABLEDESC.getNameAsString()));
  msg=null;
  try {
    this.admin.createTable(HTableDescriptor.META_TABLEDESC);
  }
 catch (  IllegalArgumentException e) {
    msg=e.toString();
  }
  assertTrue("Unexcepted exception message " + msg,msg != null && msg.startsWith(IllegalArgumentException.class.getName()) && msg.contains(HTableDescriptor.META_TABLEDESC.getNameAsString()));
  final HTableDescriptor threadDesc=new HTableDescriptor("threaded_testCreateBadTables");
  threadDesc.addFamily(new HColumnDescriptor(HConstants.CATALOG_FAMILY));
  int count=10;
  Thread[] threads=new Thread[count];
  final AtomicInteger successes=new AtomicInteger(0);
  final AtomicInteger failures=new AtomicInteger(0);
  final HBaseAdmin localAdmin=this.admin;
  for (int i=0; i < count; i++) {
    threads[i]=new Thread(Integer.toString(i)){
      @Override public void run(){
        try {
          localAdmin.createTable(threadDesc);
          successes.incrementAndGet();
        }
 catch (        TableExistsException e) {
          failures.incrementAndGet();
        }
catch (        IOException e) {
          throw new RuntimeException("Failed threaded create" + getName(),e);
        }
      }
    }
;
  }
  for (int i=0; i < count; i++) {
    threads[i].start();
  }
  for (int i=0; i < count; i++) {
    while (threads[i].isAlive()) {
      try {
        Thread.sleep(1000);
      }
 catch (      InterruptedException e) {
      }
    }
  }
  assertEquals(1,successes.get());
  assertEquals(count - 1,failures.get());
}
