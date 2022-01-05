@Test public void allTest() throws Exception {
  List<URL> urls=new ArrayList<>();
  urls.add(testUrl);
  abstractRegistry.register(testUrl);
  Assert.assertTrue(abstractRegistry.getRegistered().contains(testUrl));
  abstractRegistry.subscribe(testUrl,listener);
  Assert.assertTrue(abstractRegistry.getSubscribed().containsKey(testUrl));
  Assert.assertFalse(notifySuccess);
  abstractRegistry.notify(urls);
  Assert.assertTrue(notifySuccess);
  abstractRegistry.unsubscribe(testUrl,listener);
  Assert.assertFalse(abstractRegistry.getSubscribed().containsKey(listener));
  abstractRegistry.unregister(testUrl);
  Assert.assertFalse(abstractRegistry.getRegistered().contains(testUrl));
}
