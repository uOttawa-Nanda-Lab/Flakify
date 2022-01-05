/** 
 * Ensures that the use of thenRun, thenCompose, whenDone, etc with a response object won't block the reception of new messages.
 */
@Test public void blockingReceptionTestWithABunch() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage client=createClient();
  IBlockingResponder blockingResponder2=client.getReference(IBlockingResponder.class,"free");
  ArrayList<Task> blocked=new ArrayList<>();
  for (int i=0; i < 20; i++) {
    IBlockingResponder blockingResponder1=client.getReference(IBlockingResponder.class,"100" + i);
    blocked.add(blockingResponder1.blockOnReceiving(1));
  }
  Thread.sleep(5);
  final Task res2=blockingResponder2.receiveAndRespond();
  long start=System.currentTimeMillis();
  assertEquals("hello",res2.join());
  assertTrue(System.currentTimeMillis() - start < 30000);
  final Task<Object> all=Task.anyOf(blocked);
  assertFalse(all.isDone());
  semaphores[1].release(20);
  all.join();
  assertTrue(all.isDone());
  assertFalse(all.isCompletedExceptionally());
}
