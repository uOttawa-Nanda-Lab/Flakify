@Test public void testGetExtensionByType(){
  HelloService bean=springExtensionFactory.getExtension(HelloService.class,"beanname-not-exist");
  Assert.assertNotNull(bean);
}
