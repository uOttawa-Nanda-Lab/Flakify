/** 
 * test subscribe and unsubscribe
 */
@Test public void testSubscribeAndUnsubscribe() throws Exception {
  final AtomicReference<Boolean> notified=new AtomicReference<Boolean>(false);
  NotifyListener listener=urls -> notified.set(Boolean.TRUE);
  URL url=new URL("dubbo","192.168.0.1",2200);
  abstractRegistry.subscribe(url,listener);
  Set<NotifyListener> subscribeListeners=abstractRegistry.getSubscribed().get(url);
  Assert.assertThat(true,Matchers.equalTo(subscribeListeners.contains(listener)));
  abstractRegistry.unsubscribe(url,listener);
  Set<NotifyListener> unsubscribeListeners=abstractRegistry.getSubscribed().get(url);
  Assert.assertThat(false,Matchers.equalTo(unsubscribeListeners.contains(listener)));
}
