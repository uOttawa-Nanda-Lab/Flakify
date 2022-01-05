@Test public void testSize() throws InterruptedException {
  final InternalThreadLocal<Integer> internalThreadLocal=new InternalThreadLocal<Integer>();
  internalThreadLocal.set(1);
  Assert.assertTrue("size method is wrong!",InternalThreadLocal.size() == 1);
  final InternalThreadLocal<String> internalThreadLocalString=new InternalThreadLocal<String>();
  internalThreadLocalString.set("value");
  Assert.assertTrue("size method is wrong!",InternalThreadLocal.size() == 2);
}
