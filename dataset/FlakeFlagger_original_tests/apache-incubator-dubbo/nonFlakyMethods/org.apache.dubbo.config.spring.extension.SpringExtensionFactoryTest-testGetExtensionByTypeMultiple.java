@Test public void testGetExtensionByTypeMultiple(){
  try {
    springExtensionFactory.getExtension(DemoService.class,"beanname-not-exist");
  }
 catch (  Exception e) {
    e.printStackTrace();
    Assert.assertTrue(e instanceof NoUniqueBeanDefinitionException);
  }
}
