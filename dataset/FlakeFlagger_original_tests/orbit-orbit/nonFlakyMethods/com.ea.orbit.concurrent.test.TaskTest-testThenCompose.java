@Test public void testThenCompose(){
  CTask<Integer> t1=new CTask();
  Task<String> t2=t1.thenCompose(x -> CTask.fromValue(x + "a"));
  Task<String> t3=t1.thenCompose(x -> CompletableFuture.completedFuture(x + "a"));
  assertFalse(t1.isDone());
  t1.complete(1);
  assertTrue(t2.isDone());
  assertEquals("1a",t2.join());
  assertEquals("1a",t3.join());
}
