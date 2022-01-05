/** 
 * Test method for {@link org.apache.dubbo.registry.support.AbstractRegistry#subscribe(URL,NotifyListener)}.
 * @throws Exception
 */
@Test public void testSubscribe() throws Exception {
  try {
    abstractRegistry.subscribe(testUrl,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof IllegalArgumentException);
  }
  try {
    abstractRegistry.subscribe(null,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof IllegalArgumentException);
  }
  Assert.assertNull(abstractRegistry.getSubscribed().get(testUrl));
  abstractRegistry.subscribe(testUrl,listener);
  Assert.assertNotNull(abstractRegistry.getSubscribed().get(testUrl));
  Assert.assertTrue(abstractRegistry.getSubscribed().get(testUrl).contains(listener));
}
