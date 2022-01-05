@Test(expected=IllegalArgumentException.class) public void testUnsubscribeIfNotifyNull() throws Exception {
  final AtomicReference<Boolean> notified=new AtomicReference<Boolean>(false);
  URL url=new URL("dubbo","192.168.0.1",2200);
  abstractRegistry.unsubscribe(url,null);
  Assert.fail("unsubscribe listener == null");
}
