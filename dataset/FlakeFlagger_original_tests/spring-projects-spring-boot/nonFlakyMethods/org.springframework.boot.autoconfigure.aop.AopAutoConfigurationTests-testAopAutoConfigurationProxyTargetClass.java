@Test public void testAopAutoConfigurationProxyTargetClass(){
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfiguration.class,AopAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"spring.aop.proxyTargetClass:true");
  this.context.refresh();
  TestAspect aspect=this.context.getBean(TestAspect.class);
  assertFalse(aspect.isCalled());
  TestBean bean=this.context.getBean(TestBean.class);
  bean.foo();
  assertTrue(aspect.isCalled());
}
