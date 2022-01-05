@Test public void test(){
  AutoCloseableCommandDispatcherFactory factory=mock(AutoCloseableCommandDispatcherFactory.class);
  try (AutoCloseableCommandDispatcherFactory subject=new ManagedCommandDispatcherFactory(factory)){
    String context="context";
    CommandDispatcher<String> dispatcher=mock(CommandDispatcher.class);
    when(factory.createCommandDispatcher("foo",context)).thenReturn(dispatcher);
    when(dispatcher.getContext()).thenReturn(context);
    try (CommandDispatcher<String> dispatcher1=subject.createCommandDispatcher("foo",context)){
      assertSame(context,dispatcher1.getContext());
      try (CommandDispatcher<String> dispatcher2=subject.createCommandDispatcher("foo",context)){
        assertSame(dispatcher1,dispatcher2);
        String otherContext="unexpected";
        try {
          subject.createCommandDispatcher("foo",otherContext);
          fail();
        }
 catch (        IllegalArgumentException e) {
          verify(factory,never()).createCommandDispatcher("foo",otherContext);
        }
      }
       verify(dispatcher,never()).close();
    }
     verify(dispatcher).close();
  }
   verify(factory).close();
}
