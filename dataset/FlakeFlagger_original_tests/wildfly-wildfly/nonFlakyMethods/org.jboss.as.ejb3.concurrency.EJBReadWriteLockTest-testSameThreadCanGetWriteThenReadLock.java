/** 
 * Tests that a thread can first get a write lock and at a later point in time, get a read lock
 * @throws Exception
 */
@Test public void testSameThreadCanGetWriteThenReadLock() throws Exception {
  Lock writeLock=this.ejbReadWriteLock.writeLock();
  writeLock.lock();
  Lock readLock=this.ejbReadWriteLock.readLock();
  try {
    boolean readLockAcquired=readLock.tryLock(2,TimeUnit.SECONDS);
    if (readLockAcquired) {
      readLock.unlock();
    }
    Assert.assertTrue("Could not obtain read lock when write lock was held by the same thread!",readLockAcquired);
  }
  finally {
    writeLock.unlock();
  }
}
