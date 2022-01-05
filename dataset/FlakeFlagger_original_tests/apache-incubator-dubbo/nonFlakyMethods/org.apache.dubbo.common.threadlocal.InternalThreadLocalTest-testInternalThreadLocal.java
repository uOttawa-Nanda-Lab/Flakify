@Test public void testInternalThreadLocal() throws InterruptedException {
  final AtomicInteger index=new AtomicInteger(0);
  final InternalThreadLocal<Integer> internalThreadLocal=new InternalThreadLocal<Integer>(){
    @Override protected Integer initialValue() throws Exception {
      Integer v=index.getAndIncrement();
      System.out.println("thread : " + Thread.currentThread().getName() + " init value : "+ v);
      return v;
    }
  }
;
  for (int i=0; i < THREADS; i++) {
    Thread t=new Thread(new Runnable(){
      @Override public void run(){
        internalThreadLocal.get();
      }
    }
);
    t.start();
  }
  Thread.sleep(2000);
}
