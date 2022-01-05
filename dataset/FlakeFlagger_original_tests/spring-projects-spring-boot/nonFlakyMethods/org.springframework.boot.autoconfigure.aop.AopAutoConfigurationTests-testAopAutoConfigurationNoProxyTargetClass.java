@Test public void testAopAutoConfigurationNoProxyTargetClass(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfiguration.class,AopAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.aop.proxyTargetClass:false");
  this.context.refresh();
  TestAspect aspect=this.context.getBean(TestAspect.class);
  assertFalse(aspect.isCalled());
  TestInterface bean=this.context.getBean(TestInterface.class);
  bean.foo();
  assertTrue(aspect.isCalled());
}
