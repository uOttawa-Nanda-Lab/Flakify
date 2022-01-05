/** 
 * print take[2689]ms <p></p> This test is based on a Machine with 4 core and 16g memory.
 */
@Test public void testPerformanceTradition(){
  final ThreadLocal<String>[] caches1=new ThreadLocal[PERFORMANCE_THREAD_COUNT];
  final Thread mainThread=Thread.currentThread();
  for (int i=0; i < PERFORMANCE_THREAD_COUNT; i++) {
    caches1[i]=new ThreadLocal<String>();
  }
  Thread t1=new Thread(new Runnable(){
    @Override public void run(){
      for (int i=0; i < PERFORMANCE_THREAD_COUNT; i++) {
        caches1[i].set("float.lu");
      }
      long start=System.nanoTime();
      for (int i=0; i < PERFORMANCE_THREAD_COUNT; i++) {
        for (int j=0; j < GET_COUNT; j++) {
          caches1[i].get();
        }
      }
      long end=System.nanoTime();
      System.out.println("take[" + TimeUnit.NANOSECONDS.toMillis(end - start) + "]ms");
      LockSupport.unpark(mainThread);
    }
  }
);
  t1.start();
  LockSupport.park(mainThread);
}
