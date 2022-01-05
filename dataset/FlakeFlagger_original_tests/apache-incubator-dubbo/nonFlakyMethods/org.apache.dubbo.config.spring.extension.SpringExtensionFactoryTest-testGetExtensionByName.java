@Test public void testGetExtensionByName(){
  DemoService bean=springExtensionFactory.getExtension(DemoService.class,"bean1");
  Assert.assertNotNull(bean);
}
