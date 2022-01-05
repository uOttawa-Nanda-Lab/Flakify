@Test public void test() throws CommandDispatcherException {
  CommandDispatcher<String> dispatcher=mock(CommandDispatcher.class);
  Runnable closeTask=mock(Runnable.class);
  String context="context";
  try (CommandDispatcher<String> subject=new ManagedCommandDispatcher<>(dispatcher,closeTask)){
    when(dispatcher.getContext()).thenReturn(context);
    assertSame(context,subject.getContext());
    Command<Void,String> command=mock(Command.class);
    Node node=mock(Node.class);
    Node[] nodes=new Node[]{node};
    CompletionStage<Void> stage=mock(CompletionStage.class);
    Map<Node,CompletionStage<Void>> stages=Collections.singletonMap(node,stage);
    when(dispatcher.executeOnGroup(command,nodes)).thenReturn(stages);
    assertSame(stages,subject.executeOnGroup(command,nodes));
    when(dispatcher.executeOnMember(command,node)).thenReturn(stage);
    assertSame(stage,subject.executeOnMember(command,node));
  }
   verify(dispatcher,never()).close();
  verify(closeTask).run();
}
