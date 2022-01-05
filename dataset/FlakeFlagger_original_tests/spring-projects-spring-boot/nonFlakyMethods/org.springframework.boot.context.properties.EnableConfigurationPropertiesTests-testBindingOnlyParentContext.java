@Test public void testBindingOnlyParentContext(){
  AnnotationConfigApplicationContext parent=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(parent,"name:foo");
  parent.register(TestConfiguration.class);
  parent.refresh();
  this.context.setParent(parent);
  this.context.register(TestConsumer.class);
  this.context.refresh();
  assertEquals(0,this.context.getBeanNamesForType(TestProperties.class).length);
  assertEquals(1,parent.getBeanNamesForType(TestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestConsumer.class).getName());
}
