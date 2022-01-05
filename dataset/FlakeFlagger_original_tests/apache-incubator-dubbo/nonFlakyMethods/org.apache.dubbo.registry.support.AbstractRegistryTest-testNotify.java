/** 
 * Test method for {@link org.apache.dubbo.registry.support.AbstractRegistry#notify(List)}.
 */
@Test public void testNotify() throws Exception {
  final AtomicReference<Boolean> notified=new AtomicReference<Boolean>(false);
  NotifyListener listner1=urls -> notified.set(Boolean.TRUE);
  URL url1=new URL("dubbo","192.168.0.1",2200,parametersConsumer);
  abstractRegistry.subscribe(url1,listner1);
  NotifyListener listner2=urls -> notified.set(Boolean.TRUE);
  URL url2=new URL("dubbo","192.168.0.2",2201,parametersConsumer);
  abstractRegistry.subscribe(url2,listner2);
  NotifyListener listner3=urls -> notified.set(Boolean.TRUE);
  URL url3=new URL("dubbo","192.168.0.3",2202,parametersConsumer);
  abstractRegistry.subscribe(url3,listner3);
  List<URL> urls=new ArrayList<>();
  urls.add(url1);
  urls.add(url2);
  urls.add(url3);
  abstractRegistry.notify(url1,listner1,urls);
  Map<URL,Map<String,List<URL>>> map=abstractRegistry.getNotified();
  Assert.assertThat(true,Matchers.equalTo(map.containsKey(url1)));
  Assert.assertThat(false,Matchers.equalTo(map.containsKey(url2)));
  Assert.assertThat(false,Matchers.equalTo(map.containsKey(url3)));
}
