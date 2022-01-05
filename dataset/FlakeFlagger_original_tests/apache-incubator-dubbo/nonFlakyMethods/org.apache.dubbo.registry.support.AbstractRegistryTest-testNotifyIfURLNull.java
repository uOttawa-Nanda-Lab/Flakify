@Test(expected=IllegalArgumentException.class) public void testNotifyIfURLNull() throws Exception {
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
  abstractRegistry.notify(null,listner1,urls);
  Assert.fail("notify url == null");
}
