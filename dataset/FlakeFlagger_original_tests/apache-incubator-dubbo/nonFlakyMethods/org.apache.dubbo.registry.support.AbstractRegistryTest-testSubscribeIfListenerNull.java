@Test(expected=IllegalArgumentException.class) public void testSubscribeIfListenerNull() throws Exception {
  final AtomicReference<Boolean> notified=new AtomicReference<Boolean>(false);
  NotifyListener listener=urls -> notified.set(Boolean.TRUE);
  URL url=new URL("dubbo","192.168.0.1",2200);
  abstractRegistry.subscribe(url,null);
  Assert.fail("listener url == null");
}
