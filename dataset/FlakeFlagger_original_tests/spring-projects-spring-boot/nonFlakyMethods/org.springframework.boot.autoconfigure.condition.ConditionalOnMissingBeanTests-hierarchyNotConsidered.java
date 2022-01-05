@Test public void hierarchyNotConsidered() throws Exception {
  this.context.register(FooConfiguration.class);
  this.context.refresh();
  AnnotationConfigApplicationContext childContext=new AnnotationConfigApplicationContext();
  childContext.setParent(this.context);
  childContext.register(HierarchyNotConsidered.class);
  childContext.refresh();
  assertTrue(childContext.containsLocalBean("bar"));
}
