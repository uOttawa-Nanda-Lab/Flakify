@Test public void testAttributes() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  Map<String,Object> attributes=lifeCycle.getContext().getAttributes();
  assertTrue(attributes.containsKey("spring.version"));
  assertTrue(attributes.containsKey("spring.beanfactory"));
  assertEquals(this.context.getBeanFactory(),attributes.get("spring.beanfactory"));
}
