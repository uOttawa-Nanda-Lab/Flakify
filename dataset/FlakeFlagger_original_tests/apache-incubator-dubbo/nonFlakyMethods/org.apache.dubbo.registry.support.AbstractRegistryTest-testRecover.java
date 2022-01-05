/** 
 * Test method for {@link org.apache.dubbo.registry.support.AbstractRegistry#recover()}.
 */
@Test public void testRecover() throws Exception {
  abstractRegistry.recover();
  Assert.assertFalse(abstractRegistry.getRegistered().contains(testUrl));
  Assert.assertNull(abstractRegistry.getSubscribed().get(testUrl));
  abstractRegistry.register(testUrl);
  abstractRegistry.subscribe(testUrl,listener);
  abstractRegistry.recover();
  Assert.assertTrue(abstractRegistry.getRegistered().contains(testUrl));
  Assert.assertNotNull(abstractRegistry.getSubscribed().get(testUrl));
  Assert.assertTrue(abstractRegistry.getSubscribed().get(testUrl).contains(listener));
}
