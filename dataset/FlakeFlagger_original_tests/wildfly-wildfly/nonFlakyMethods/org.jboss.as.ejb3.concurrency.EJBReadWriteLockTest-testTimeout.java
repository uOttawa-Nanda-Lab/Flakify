/** 
 * Test that when a thread tries to obtain a read lock when another thread holds a write lock, fails to acquire the lock, if the write lock is not released within the timeout specified
 * @throws Exception
 */
@Test public void testTimeout() throws Exception {
  CountDownLatch latch=new CountDownLatch(2);
  Lock writeLock=this.ejbReadWriteLock.writeLock();
  Thread threadHoldingWriteLock=new Thread(new ThreadHoldingWriteLock(latch,writeLock,5000));
  Lock readLock=this.ejbReadWriteLock.readLock();
  threadHoldingWriteLock.start();
  Thread.sleep(500);
  try {
    boolean readLockAcquired=readLock.tryLock(2,TimeUnit.SECONDS);
    Assert.assertFalse("Unexpected obtained a read lock",readLockAcquired);
  }
 catch (  ConcurrentAccessTimeoutException cate) {
  }
 finally {
    latch.countDown();
    latch.await();
  }
}
