@Test public void testCustomExecutor(){
  Executor mockedExecutor=mock(Executor.class);
  CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync(() -> {
    return 0;
  }
);
  completableFuture.thenRunAsync(mock(Runnable.class),verify(mockedExecutor));
}
