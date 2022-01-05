@Test public void avoidReinsertionTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  SomeChatObserver observer=new SomeChatObserver();
  ISomeChatObserver ref1=stage1.getObserverReference(ISomeChatObserver.class,observer);
  assertNotNull(ref1);
  ISomeChatObserver ref2=stage1.getObserverReference(ISomeChatObserver.class,observer);
  assertNotNull(ref2);
  assertSame(ref1,ref2);
}
