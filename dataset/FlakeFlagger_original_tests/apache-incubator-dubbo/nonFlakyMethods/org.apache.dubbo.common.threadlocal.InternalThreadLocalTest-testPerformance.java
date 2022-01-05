/** 
 * print take[14]ms <p></p> This test is based on a Machine with 4 core and 16g memory.
 */
@Test public void testPerformance(){
  final InternalThreadLocal<String>[] caches=new InternalThreadLocal[PERFORMANCE_THREAD_COUNT];
  final Thread mainThread=Thread.currentThread();
  for (int i=0; i < PERFORMANCE_THREAD_COUNT; i++) {
    caches[i]=new InternalThreadLocal<String>();
  }
  Thread t=new InternalThread(new Runnable(){
    @Override public void run(){
      for (int i=0; i < PERFORMANCE_THREAD_COUNT; i++) {
        caches[i].set("float.lu");
      }
      long start=System.nanoTime();
      for (int i=0; i < PERFORMANCE_THREAD_COUNT; i++) {
        for (int j=0; j < GET_COUNT; j++) {
          caches[i].get();
        }
      }
      long end=System.nanoTime();
      System.out.println("take[" + TimeUnit.NANOSECONDS.toMillis(end - start) + "]ms");
      LockSupport.unpark(mainThread);
    }
  }
);
  t.start();
  LockSupport.park(mainThread);
}
