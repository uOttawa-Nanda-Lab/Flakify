@Test public void testRemoveAll() throws InterruptedException {
  final InternalThreadLocal<Integer> internalThreadLocal=new InternalThreadLocal<Integer>();
  internalThreadLocal.set(1);
  Assert.assertTrue("set failed",internalThreadLocal.get() == 1);
  final InternalThreadLocal<String> internalThreadLocalString=new InternalThreadLocal<String>();
  internalThreadLocalString.set("value");
  Assert.assertTrue("set failed","value".equals(internalThreadLocalString.get()));
  InternalThreadLocal.removeAll();
  Assert.assertTrue("removeAll failed!",internalThreadLocal.get() == null);
  Assert.assertTrue("removeAll failed!",internalThreadLocalString.get() == null);
}
