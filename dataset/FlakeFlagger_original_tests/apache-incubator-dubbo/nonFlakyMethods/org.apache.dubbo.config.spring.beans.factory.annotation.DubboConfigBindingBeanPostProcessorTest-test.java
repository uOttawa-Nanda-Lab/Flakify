@Test public void test(){
  final AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
  applicationContext.register(getClass());
  Class<?> processorClass=DubboConfigBindingBeanPostProcessor.class;
  applicationContext.registerBeanDefinition("DubboConfigBindingBeanPostProcessor",rootBeanDefinition(processorClass).addConstructorArgValue("dubbo.application").addConstructorArgValue("applicationBean").getBeanDefinition());
  applicationContext.refresh();
  ApplicationConfig applicationConfig=applicationContext.getBean(ApplicationConfig.class);
  Assert.assertEquals("dubbo-demo-application",applicationConfig.getName());
}
