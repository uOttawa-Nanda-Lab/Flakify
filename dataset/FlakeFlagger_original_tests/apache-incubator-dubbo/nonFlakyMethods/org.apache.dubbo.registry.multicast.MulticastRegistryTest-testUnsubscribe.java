/** 
 * Test method for  {@link org.apache.dubbo.registry.multicast.MulticastRegistry#unsubscribe(URL,NotifyListener)}
 */
@Test public void testUnsubscribe(){
  registry.subscribe(consumerUrl,new NotifyListener(){
    @Override public void notify(    List<URL> urls){
    }
  }
);
  registry.unsubscribe(consumerUrl,new NotifyListener(){
    @Override public void notify(    List<URL> urls){
      Map<URL,Set<NotifyListener>> subscribed=registry.getSubscribed();
      Set<NotifyListener> listeners=subscribed.get(consumerUrl);
      assertTrue(listeners.isEmpty());
      Map<URL,Set<URL>> received=registry.getReceived();
      assertTrue(received.get(consumerUrl).isEmpty());
    }
  }
);
}
