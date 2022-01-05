/** 
 * Test method for {@link org.apache.dubbo.registry.support.AbstractRegistry#unregister(URL)}.
 * @throws Exception
 */
@Test public void testUnregister() throws Exception {
  URL url=new URL("dubbo","192.168.0.1",2200);
  abstractRegistry.getRegistered().add(url);
  abstractRegistry.unregister(url);
  Assert.assertThat(false,Matchers.equalTo(abstractRegistry.getRegistered().contains(url)));
  abstractRegistry.getRegistered().clear();
  List<URL> urlList=getList();
  for (  URL urlSub : urlList) {
    abstractRegistry.getRegistered().add(urlSub);
  }
  for (  URL urlSub : urlList) {
    abstractRegistry.unregister(urlSub);
  }
  Assert.assertThat(0,Matchers.equalTo(abstractRegistry.getRegistered().size()));
}
