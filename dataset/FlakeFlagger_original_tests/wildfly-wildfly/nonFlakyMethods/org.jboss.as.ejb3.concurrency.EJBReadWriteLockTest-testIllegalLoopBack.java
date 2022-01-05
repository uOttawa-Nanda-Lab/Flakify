/** 
 * Test that a  {@link javax.ejb.IllegalLoopbackException} is thrown when the thread owning a read locktries to obtain a write lock
 * @throws Exception
 */
@Test public void testIllegalLoopBack() throws Exception {
  Lock readLock=this.ejbReadWriteLock.readLock();
  readLock.lock();
  Lock writeLock=this.ejbReadWriteLock.writeLock();
  try {
    writeLock.lock();
    writeLock.unlock();
    Assert.fail("Unexpected acquired write lock");
  }
 catch (  IllegalLoopbackException ilbe) {
  }
 finally {
    readLock.unlock();
  }
}
