@Test public void hierarchyConsidered() throws Exception {
  this.context.register(FooConfiguration.class);
  this.context.refresh();
  AnnotationConfigApplicationContext childContext=new AnnotationConfigApplicationContext();
  childContext.setParent(this.context);
  childContext.register(HierarchyConsidered.class);
  childContext.refresh();
  assertFalse(childContext.containsLocalBean("bar"));
}
