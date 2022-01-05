@Test public void testThenReturnWithException(){
  CTask<Integer> t1=new CTask();
  Task<String> t2=t1.thenReturn(() -> "a");
  assertFalse(t1.isDone());
  t1.completeExceptionally(new RuntimeException());
  assertTrue(t2.isDone());
  assertTrue(t2.isCompletedExceptionally());
}
