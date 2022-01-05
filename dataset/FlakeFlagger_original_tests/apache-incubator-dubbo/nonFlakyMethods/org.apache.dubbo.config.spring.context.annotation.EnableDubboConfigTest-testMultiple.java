@Test public void testMultiple(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  context.register(TestMultipleConfig.class);
  context.refresh();
  ApplicationConfig applicationConfig=context.getBean("applicationBean",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application",applicationConfig.getName());
  ApplicationConfig applicationBean2=context.getBean("applicationBean2",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application2",applicationBean2.getName());
  ApplicationConfig applicationBean3=context.getBean("applicationBean3",ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application3",applicationBean3.getName());
}
