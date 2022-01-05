@Test public void testDisabledCommandResolution(){
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(CrshAutoConfiguration.class);
  this.context.refresh();
  PluginLifeCycle lifeCycle=this.context.getBean(PluginLifeCycle.class);
  int count=0;
  Iterator<Resource> resources=lifeCycle.getContext().loadResources("jdbc.groovy",ResourceKind.COMMAND).iterator();
  while (resources.hasNext()) {
    count++;
    resources.next();
  }
  assertEquals(0,count);
}
