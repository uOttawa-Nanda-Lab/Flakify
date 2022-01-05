@Test public void testCommandResolution(){
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  int count=0;
  Iterator<Resource> resources=lifeCycle.getContext().loadResources("login",ResourceKind.LIFECYCLE).iterator();
  while (resources.hasNext()) {
    count++;
    resources.next();
  }
  assertEquals(1,count);
  count=0;
  resources=lifeCycle.getContext().loadResources("sleep.groovy",ResourceKind.COMMAND).iterator();
  while (resources.hasNext()) {
    count++;
    resources.next();
  }
  assertEquals(1,count);
}
