@Test public void testGetAndJoinWithWrappedForkJoinTaskAndTimeout() throws ExecutionException, InterruptedException {
  final ForkJoinTask<String> task=ForkJoinTask.adapt(() -> "bla");
  final Task<String> t1=Task.fromFuture(task);
  assertFalse(t1.isDone());
  Thread.sleep(10);
  task.fork();
  assertEquals("bla",t1.join());
  assertTrue(t1.isDone());
  assertEquals("bla",t1.join());
  assertEquals("bla",t1.get());
}
