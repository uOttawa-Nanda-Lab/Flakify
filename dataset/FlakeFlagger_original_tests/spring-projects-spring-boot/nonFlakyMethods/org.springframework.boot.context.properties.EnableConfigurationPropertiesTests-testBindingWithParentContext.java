@Test public void testBindingWithParentContext(){
  AnnotationConfigApplicationContext parent=new AnnotationConfigApplicationContext();
  parent.register(TestConfiguration.class);
  parent.refresh();
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo");
  this.context.setParent(parent);
  this.context.register(TestConfiguration.class,TestConsumer.class);
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(TestProperties.class).length);
  assertEquals(1,parent.getBeanNamesForType(TestProperties.class).length);
  assertEquals("foo",this.context.getBean(TestConsumer.class).getName());
}
