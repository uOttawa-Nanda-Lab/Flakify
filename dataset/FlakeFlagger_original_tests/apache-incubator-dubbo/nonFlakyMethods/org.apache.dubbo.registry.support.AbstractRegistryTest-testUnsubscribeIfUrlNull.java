@Test(expected=IllegalArgumentException.class) public void testUnsubscribeIfUrlNull() throws Exception {
  final AtomicReference<Boolean> notified=new AtomicReference<Boolean>(false);
  NotifyListener listener=urls -> notified.set(Boolean.TRUE);
  abstractRegistry.unsubscribe(null,listener);
  Assert.fail("unsubscribe url == null");
}
