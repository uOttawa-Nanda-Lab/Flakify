@Test public void testSubmitOnNode() throws CommandDispatcherException {
  CommandDispatcher<Void> dispatcher=mock(CommandDispatcher.class);
  try (CommandDispatcher<Void> subject=new TestCommandDispatcher<>(dispatcher)){
    Command<Object,Object> command=mock(Command.class);
    Node member=mock(Node.class);
    Object result=new Object();
    CompletableFuture<Object> future=new CompletableFuture<>();
    when(dispatcher.executeOnMember(same(command),same(member))).thenReturn(future);
    future.complete(result);
    Future<Object> response=subject.submitOnNode(command,member);
    assertSame(future,response);
  }
   verify(dispatcher).close();
}
