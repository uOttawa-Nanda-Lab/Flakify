@Test public void testNotRunSameRunnableTwice(){
  Executor mockedExecutor=mock(Executor.class);
  this.executionList.add(mock(Runnable.class),mockedExecutor);
  this.executionList.execute();
  this.executionList.execute();
  verify(mockedExecutor).execute(any(Runnable.class));
}
