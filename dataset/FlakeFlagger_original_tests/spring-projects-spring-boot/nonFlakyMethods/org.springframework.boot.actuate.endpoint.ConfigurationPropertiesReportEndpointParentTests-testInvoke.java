@Test public void testInvoke() throws Exception {
  AnnotationConfigApplicationContext parent=new AnnotationConfigApplicationContext();
  parent.register(Parent.class);
  parent.refresh();
  this.context=new AnnotationConfigApplicationContext();
  this.context.setParent(parent);
  this.context.register(Config.class);
  this.context.refresh();
  ConfigurationPropertiesReportEndpoint endpoint=this.context.getBean(ConfigurationPropertiesReportEndpoint.class);
  Map<String,Object> result=endpoint.invoke();
  assertTrue(result.containsKey("parent"));
  assertEquals(3,result.size());
}
