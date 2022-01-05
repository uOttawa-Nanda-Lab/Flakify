/** 
 * Ensures that the use of thenRun, thenCompose, whenDone, etc with a response object won't block the reception of new messages.
 */
@Test public void blockingReceptionTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage client=createClient();
  IBlockingResponder blockingResponder=client.getReference(IBlockingResponder.class,"1");
  IBlockingResponder responder=client.getReference(IBlockingResponder.class,"free");
  final Task blockedRes=blockingResponder.blockOnReceiving(0);
  final Task res=responder.receiveAndRespond();
  assertEquals("hello",res.join());
  assertFalse(blockedRes.isDone());
  semaphores[0].release(1);
  blockedRes.join();
  assertFalse(blockedRes.isCompletedExceptionally());
}
