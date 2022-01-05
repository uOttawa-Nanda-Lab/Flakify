@Test public void testMultiThreadSetAndGet() throws InterruptedException {
  final Integer testVal1=10;
  final Integer testVal2=20;
  final InternalThreadLocal<Integer> internalThreadLocal=new InternalThreadLocal<Integer>();
  final CountDownLatch countDownLatch=new CountDownLatch(2);
  Thread t1=new Thread(new Runnable(){
    @Override public void run(){
      internalThreadLocal.set(testVal1);
      Assert.assertTrue("set is not equals get",Objects.equals(testVal1,internalThreadLocal.get()));
      countDownLatch.countDown();
    }
  }
);
  t1.start();
  Thread t2=new Thread(new Runnable(){
    @Override public void run(){
      internalThreadLocal.set(testVal2);
      Assert.assertTrue("set is not equals get",Objects.equals(testVal2,internalThreadLocal.get()));
      countDownLatch.countDown();
    }
  }
);
  t2.start();
  countDownLatch.await();
}
