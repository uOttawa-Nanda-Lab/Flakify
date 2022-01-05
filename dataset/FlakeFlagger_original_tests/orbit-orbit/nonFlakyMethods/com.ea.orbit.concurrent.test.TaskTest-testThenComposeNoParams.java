@Test public void testThenComposeNoParams(){
  CTask<Integer> t1=new CTask();
  Task<String> t2=t1.thenCompose(() -> Task.fromValue("b"));
  Task<String> t3=t1.thenCompose(() -> CompletableFuture.completedFuture("c"));
  assertFalse(t1.isDone());
  t1.complete(1);
  assertTrue(t2.isDone());
  assertEquals("b",t2.join());
  assertEquals("c",t3.join());
}
