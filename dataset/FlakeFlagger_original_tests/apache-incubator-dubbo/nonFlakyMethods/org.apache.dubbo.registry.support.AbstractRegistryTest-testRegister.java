/** 
 * Test method for {@link org.apache.dubbo.registry.support.AbstractRegistry#register(URL)}.
 * @throws Exception
 */
@Test public void testRegister() throws Exception {
  abstractRegistry.register(mockUrl);
  assert abstractRegistry.getRegistered().contains(mockUrl);
  abstractRegistry.getRegistered().clear();
  List<URL> urlList=getList();
  for (  URL url : urlList) {
    abstractRegistry.register(url);
  }
  Assert.assertThat(abstractRegistry.getRegistered().size(),Matchers.equalTo(urlList.size()));
}
