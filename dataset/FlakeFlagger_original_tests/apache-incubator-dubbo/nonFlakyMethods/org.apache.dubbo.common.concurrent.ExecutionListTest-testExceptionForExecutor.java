@Test public void testExceptionForExecutor(){
  Executor mockedExecutor=mock(Executor.class);
  doThrow(new RuntimeException()).when(mockedExecutor).execute(any(Runnable.class));
  this.executionList.add(mock(Runnable.class),mockedExecutor);
  this.executionList.execute();
}
