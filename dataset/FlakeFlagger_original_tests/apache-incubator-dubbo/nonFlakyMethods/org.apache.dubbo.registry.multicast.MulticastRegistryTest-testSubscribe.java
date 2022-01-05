/** 
 * Test method for {@link org.apache.dubbo.registry.multicast.MulticastRegistry#subscribe(URL url, org.apache.dubbo.registry.NotifyListener)}.
 */
@Test public void testSubscribe(){
  registry.subscribe(consumerUrl,new NotifyListener(){
    @Override public void notify(    List<URL> urls){
      assertEquals(serviceUrl.toFullString(),urls.get(0).toFullString());
      Map<URL,Set<NotifyListener>> subscribed=registry.getSubscribed();
      assertEquals(consumerUrl,subscribed.keySet().iterator().next());
    }
  }
);
}
