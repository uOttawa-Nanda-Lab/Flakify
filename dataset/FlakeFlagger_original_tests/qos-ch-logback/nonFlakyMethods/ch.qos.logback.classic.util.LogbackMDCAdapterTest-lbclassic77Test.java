/** 
 * Test that CopyOnInheritThreadLocal does not barf when the MDC hashmap is null
 * @throws InterruptedException
 */
@Test public void lbclassic77Test() throws InterruptedException {
  Map<String,String> parentHM=getMapFromMDCAdapter(mdcAdapter);
  assertNull(parentHM);
  ChildThreadForMDCAdapter childThread=new ChildThreadForMDCAdapter(mdcAdapter);
  childThread.start();
  childThread.join();
  assertTrue(childThread.successul);
  assertNull(childThread.childHM);
}
