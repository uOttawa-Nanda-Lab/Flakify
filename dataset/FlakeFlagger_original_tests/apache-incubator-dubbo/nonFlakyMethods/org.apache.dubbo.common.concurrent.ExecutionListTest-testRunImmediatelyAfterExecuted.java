@Test public void testRunImmediatelyAfterExecuted(){
  Executor mockedExecutor=mock(Executor.class);
  this.executionList.add(mock(Runnable.class),mockedExecutor);
  this.executionList.execute();
  this.executionList.add(mock(Runnable.class),mockedExecutor);
  verify(mockedExecutor,times(2)).execute(any(Runnable.class));
}
