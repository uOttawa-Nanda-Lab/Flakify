@Test public void testSubscribe(){
  NotifyListener listener=mock(NotifyListener.class);
  zookeeperRegistry.subscribe(serviceUrl,listener);
  Map<URL,Set<NotifyListener>> subscribed=zookeeperRegistry.getSubscribed();
  assertThat(subscribed.size(),is(1));
  assertThat(subscribed.get(serviceUrl).size(),is(1));
  zookeeperRegistry.unsubscribe(serviceUrl,listener);
  subscribed=zookeeperRegistry.getSubscribed();
  assertThat(subscribed.size(),is(1));
  assertThat(subscribed.get(serviceUrl).size(),is(0));
}
