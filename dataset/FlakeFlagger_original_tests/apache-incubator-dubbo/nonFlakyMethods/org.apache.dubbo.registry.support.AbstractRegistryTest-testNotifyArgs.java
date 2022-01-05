/** 
 * Test method for {@link org.apache.dubbo.registry.support.AbstractRegistry#notify(URL,NotifyListener,List)}.
 * @throws Exception
 */
@Test public void testNotifyArgs() throws Exception {
  try {
    abstractRegistry.notify(null,null,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof IllegalArgumentException);
  }
  try {
    abstractRegistry.notify(testUrl,null,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof IllegalArgumentException);
  }
  try {
    abstractRegistry.notify(null,listener,null);
    Assert.fail();
  }
 catch (  Exception e) {
    Assert.assertTrue(e instanceof IllegalArgumentException);
  }
  Assert.assertFalse(notifySuccess);
  abstractRegistry.notify(testUrl,listener,null);
  Assert.assertFalse(notifySuccess);
  List<URL> urls=new ArrayList<>();
  urls.add(testUrl);
  Assert.assertFalse(notifySuccess);
  abstractRegistry.notify(testUrl,listener,urls);
  Assert.assertTrue(notifySuccess);
}
