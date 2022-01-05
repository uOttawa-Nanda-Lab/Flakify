@Test public void testRemove(){
  final InternalThreadLocal<Integer> internalThreadLocal=new InternalThreadLocal<Integer>();
  internalThreadLocal.set(1);
  Assert.assertTrue("get method false!",internalThreadLocal.get() == 1);
  internalThreadLocal.remove();
  Assert.assertTrue("remove failed!",internalThreadLocal.get() == null);
}
