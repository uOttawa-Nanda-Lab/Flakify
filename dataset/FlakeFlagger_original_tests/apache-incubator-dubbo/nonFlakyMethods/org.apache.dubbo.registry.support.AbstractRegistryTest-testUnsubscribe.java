/** 
 * Test method for {@link org.apache.dubbo.registry.support.AbstractRegistry#unsubscribe(URL,NotifyListener)}.
 * @throws Exception
 */
@Test public void testUnsubscribe() throws Exception {
  try {
    abstractRegistry.unsubscribe(testUrl,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof IllegalArgumentException);
  }
  try {
    abstractRegistry.unsubscribe(null,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof IllegalArgumentException);
  }
  Assert.assertNull(abstractRegistry.getSubscribed().get(testUrl));
  abstractRegistry.subscribe(testUrl,listener);
  abstractRegistry.unsubscribe(testUrl,listener);
  Assert.assertNotNull(abstractRegistry.getSubscribed().get(testUrl));
  Assert.assertFalse(abstractRegistry.getSubscribed().get(testUrl).contains(listener));
}
