@Test public void testThenApply(){
  CTask<Integer> t1=new CTask();
  Task<String> t2=t1.thenApply(x -> "a");
  assertFalse(t1.isDone());
  t1.complete(1);
  assertTrue(t2.isDone());
  assertEquals("a",t2.join());
}
