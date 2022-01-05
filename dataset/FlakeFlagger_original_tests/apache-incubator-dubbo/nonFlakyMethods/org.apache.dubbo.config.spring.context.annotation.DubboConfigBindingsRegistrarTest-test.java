@Test public void test(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  context.register(TestConfig.class);
  context.refresh();
  ApplicationConfig applicationConfig=context.getBean("applicationBean",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application",applicationConfig.getName());
  Assert.assertEquals(2,context.getBeansOfType(ApplicationConfig.class).size());
}
