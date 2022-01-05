@Test public void testSubscribeAndUnsubscribe(){
  NotifyListener listener=new NotifyListener(){
    @Override public void notify(    List<URL> urls){
    }
  }
;
  redisRegistry.subscribe(serviceUrl,listener);
  Map<URL,Set<NotifyListener>> subscribed=redisRegistry.getSubscribed();
  assertThat(subscribed.size(),is(1));
  assertThat(subscribed.get(serviceUrl).size(),is(1));
  redisRegistry.unsubscribe(serviceUrl,listener);
  subscribed=redisRegistry.getSubscribed();
  assertThat(subscribed.get(serviceUrl).size(),is(0));
}
