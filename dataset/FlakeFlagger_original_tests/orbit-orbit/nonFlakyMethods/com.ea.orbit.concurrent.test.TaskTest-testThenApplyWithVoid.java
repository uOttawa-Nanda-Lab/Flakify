@Test public void testThenApplyWithVoid(){
  CTask<Void> t1=new CTask();
  Task<String> t2=t1.thenApply(x -> "a");
  assertFalse(t1.isDone());
  t1.complete(null);
  assertTrue(t2.isDone());
  assertEquals("a",t2.join());
}
