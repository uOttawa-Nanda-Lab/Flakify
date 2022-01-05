@Test public void test() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TestBean.class);
  TestBean testBean=context.getBean(TestBean.class);
  Assert.assertNotNull(testBean.getDemoServiceFromAncestor());
  Assert.assertNotNull(testBean.getDemoServiceFromParent());
  Assert.assertNotNull(testBean.getDemoService());
  Assert.assertEquals(testBean.getDemoServiceFromAncestor(),testBean.getDemoServiceFromParent());
  Assert.assertEquals(testBean.getDemoService(),testBean.getDemoServiceFromParent());
  DemoService demoService=testBean.getDemoService();
  Assert.assertEquals("annotation:Mercy",demoService.sayName("Mercy"));
  context.close();
}
