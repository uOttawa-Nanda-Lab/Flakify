/** 
 * More threads than the pool size.
 */
@Test public void testMultiThread() throws Exception {
  MockBean.reset();
  StatelessObjectFactory<MockBean> factory=new MockFactory();
  final Pool<MockBean> pool=new StrictMaxPool<MockBean>(factory,10,60,TimeUnit.SECONDS);
  pool.start();
  final CountDownLatch in=new CountDownLatch(1);
  final CountDownLatch ready=new CountDownLatch(10);
  Callable<Void> task=new Callable<Void>(){
    public Void call() throws Exception {
      MockBean bean=pool.get();
      ready.countDown();
      in.await();
      pool.release(bean);
      bean=null;
      used.incrementAndGet();
      return null;
    }
  }
;
  ExecutorService service=Executors.newFixedThreadPool(20);
  Future<?>[] results=new Future<?>[20];
  for (int i=0; i < results.length; i++) {
    results[i]=service.submit(task);
  }
  ready.await(120,TimeUnit.SECONDS);
  in.countDown();
  for (  Future<?> result : results) {
    result.get(5,TimeUnit.SECONDS);
  }
  service.shutdown();
  pool.stop();
  assertEquals(20,used.intValue());
  assertEquals(10,MockBean.getPostConstructs());
  assertEquals(10,MockBean.getPreDestroys());
}
