/** 
 * Test that LogbackMDCAdapter copies its hashmap when a child thread inherits it.
 * @throws InterruptedException
 */
@Test public void copyOnInheritenceTest() throws InterruptedException {
  CountDownLatch countDownLatch=new CountDownLatch(1);
  String firstKey="x" + diff;
  String secondKey="o" + diff;
  mdcAdapter.put(firstKey,firstKey + A_SUFFIX);
  ChildThread childThread=new ChildThread(mdcAdapter,firstKey,secondKey,countDownLatch);
  childThread.start();
  countDownLatch.await();
  mdcAdapter.put(firstKey,firstKey + B_SUFFIX);
  childThread.join();
  assertNull(mdcAdapter.get(secondKey));
  assertTrue(childThread.successful);
  Map<String,String> parentHM=getMapFromMDCAdapter(mdcAdapter);
  assertTrue(parentHM != childThread.childHM);
  HashMap<String,String> parentHMWitness=new HashMap<String,String>();
  parentHMWitness.put(firstKey,firstKey + B_SUFFIX);
  assertEquals(parentHMWitness,parentHM);
  HashMap<String,String> childHMWitness=new HashMap<String,String>();
  childHMWitness.put(firstKey,firstKey + A_SUFFIX);
  childHMWitness.put(secondKey,secondKey + A_SUFFIX);
  assertEquals(childHMWitness,childThread.childHM);
}
