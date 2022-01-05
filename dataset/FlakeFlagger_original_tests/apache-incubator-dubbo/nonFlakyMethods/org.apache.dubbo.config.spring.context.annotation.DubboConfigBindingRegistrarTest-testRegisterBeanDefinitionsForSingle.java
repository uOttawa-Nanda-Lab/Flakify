@Test public void testRegisterBeanDefinitionsForSingle(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  context.register(TestApplicationConfig.class);
  context.refresh();
  ApplicationConfig applicationConfig=context.getBean("applicationBean",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application",applicationConfig.getName());
}
