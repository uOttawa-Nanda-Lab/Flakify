@Test public void testSubmitOnCluster() throws CommandDispatcherException {
  CommandDispatcher<Void> dispatcher=mock(CommandDispatcher.class);
  try (CommandDispatcher<Void> subject=new TestCommandDispatcher<>(dispatcher)){
    Command<Object,Object> command=mock(Command.class);
    Node completedMember=mock(Node.class);
    Node exceptionMember=mock(Node.class);
    Node cancelledMember=mock(Node.class);
    Object result=new Object();
    Exception exception=new Exception();
    CompletableFuture<Object> completedFuture=CompletableFuture.completedFuture(result);
    CompletableFuture<Object> exceptionFuture=new CompletableFuture<>();
    exceptionFuture.completeExceptionally(exception);
    CompletableFuture<Object> cancelledFuture=new CompletableFuture<>();
    cancelledFuture.cancel(false);
    Map<Node,CompletionStage<Object>> futures=new HashMap<>();
    futures.put(completedMember,completedFuture);
    futures.put(exceptionMember,exceptionFuture);
    futures.put(cancelledMember,cancelledFuture);
    when(dispatcher.executeOnGroup(same(command))).thenReturn(futures);
    Map<Node,Future<Object>> responses=subject.submitOnCluster(command);
    assertSame(completedFuture,responses.get(completedMember));
    assertSame(exceptionFuture,responses.get(exceptionMember));
    assertSame(cancelledFuture,responses.get(cancelledMember));
  }
   verify(dispatcher).close();
}
