@Test public void testExecuteOnNode() throws CommandDispatcherException {
  CommandDispatcher<Void> dispatcher=mock(CommandDispatcher.class);
  try (CommandDispatcher<Void> subject=new TestCommandDispatcher<>(dispatcher)){
    Command<Object,Object> command=mock(Command.class);
    Node member=mock(Node.class);
    Object result=new Object();
    CompletableFuture<Object> future=new CompletableFuture<>();
    when(dispatcher.executeOnMember(same(command),same(member))).thenReturn(future);
    future.complete(result);
    CommandResponse<Object> response=subject.executeOnNode(command,member);
    try {
      assertSame(result,response.get());
    }
 catch (    ExecutionException e) {
      fail(e.getMessage());
    }
    Exception exception=new Exception();
    when(dispatcher.executeOnMember(same(command),same(member))).thenReturn(future);
    future.obtrudeException(exception);
    try {
      subject.executeOnNode(command,member).get();
      fail("Expected exception");
    }
 catch (    ExecutionException e) {
      assertSame(exception,e.getCause());
    }
    exception=new CancellationException();
    future.obtrudeException(exception);
    try {
      subject.executeOnNode(command,member).get();
      fail("Expected exception");
    }
 catch (    CancellationException e) {
      assertSame(exception,e);
    }
catch (    ExecutionException e) {
      fail(e.getMessage());
    }
  }
   verify(dispatcher).close();
}
