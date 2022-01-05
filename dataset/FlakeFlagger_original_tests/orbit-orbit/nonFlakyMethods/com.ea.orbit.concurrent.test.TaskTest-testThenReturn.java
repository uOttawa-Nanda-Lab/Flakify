@Test public void testThenReturn(){
  CTask<Integer> t1=new CTask();
  Task<String> t2=t1.thenReturn(() -> "a");
  assertFalse(t1.isDone());
  t1.complete(1);
  assertTrue(t2.isDone());
  assertEquals("a",t2.join());
}
