@Test public void destroyTest() throws Exception {
  abstractRegistry.register(testUrl);
  abstractRegistry.subscribe(testUrl,listener);
  Assert.assertEquals(1,abstractRegistry.getRegistered().size());
  Assert.assertEquals(1,abstractRegistry.getSubscribed().get(testUrl).size());
  abstractRegistry.destroy();
  Assert.assertEquals(0,abstractRegistry.getRegistered().size());
  Assert.assertEquals(0,abstractRegistry.getSubscribed().get(testUrl).size());
}
