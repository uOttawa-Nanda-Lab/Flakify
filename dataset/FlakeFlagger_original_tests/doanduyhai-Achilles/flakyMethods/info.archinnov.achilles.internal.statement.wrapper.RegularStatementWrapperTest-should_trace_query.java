@Test public void should_trace_query() throws Exception {
  wrapper=new RegularStatementWrapper(Entity1.class,rs,new Object[]{1},ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);
  ExecutionInfo executionInfo=mock(ExecutionInfo.class,RETURNS_DEEP_STUBS);
  QueryTrace.Event event=mock(QueryTrace.Event.class);
  when(resultSet.getAllExecutionInfo()).thenReturn(Arrays.asList(executionInfo));
  when(executionInfo.getAchievedConsistencyLevel()).thenReturn(ConsistencyLevel.ALL);
  when(executionInfo.getQueryTrace().getEvents()).thenReturn(Arrays.asList(event));
  when(event.getDescription()).thenReturn("description");
  when(event.getSource()).thenReturn(InetAddress.getLocalHost());
  when(event.getSourceElapsedMicros()).thenReturn(100);
  when(event.getThreadName()).thenReturn("thread");
  wrapper.tracing(resultSet);
}
