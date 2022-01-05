@Test public void customBeanNameGenerator() throws Exception {
  TestSpringApplication application=new TestSpringApplication(ExampleWebConfig.class);
  BeanNameGenerator beanNameGenerator=new DefaultBeanNameGenerator();
  application.setBeanNameGenerator(beanNameGenerator);
  this.context=application.run();
  verify(application.getLoader()).setBeanNameGenerator(beanNameGenerator);
  assertThat(this.context.getBean(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR),sameInstance((Object)beanNameGenerator));
}
