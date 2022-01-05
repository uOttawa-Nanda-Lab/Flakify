@Test public void testRegisterBeanDefinitionsForMultiple(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  context.register(TestMultipleApplicationConfig.class);
  context.refresh();
  ApplicationConfig applicationConfig=context.getBean("applicationBean",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application",applicationConfig.getName());
  applicationConfig=context.getBean("applicationBean2",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application2",applicationConfig.getName());
  applicationConfig=context.getBean("applicationBean3",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application3",applicationConfig.getName());
}
