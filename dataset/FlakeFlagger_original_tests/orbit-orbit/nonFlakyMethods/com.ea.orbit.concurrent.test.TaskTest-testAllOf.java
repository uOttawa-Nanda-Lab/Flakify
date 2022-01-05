@Test public void testAllOf(){
  CTask t1=new CTask();
  CTask t2=new CTask();
  CTask t3=new CTask();
  Task all=Task.allOf(t1,t2,t3);
  assertFalse(all.isDone());
  t1.complete(1);
  assertFalse(all.isDone());
  t2.complete(2);
  t3.complete(3);
  assertTrue(all.isDone());
}
