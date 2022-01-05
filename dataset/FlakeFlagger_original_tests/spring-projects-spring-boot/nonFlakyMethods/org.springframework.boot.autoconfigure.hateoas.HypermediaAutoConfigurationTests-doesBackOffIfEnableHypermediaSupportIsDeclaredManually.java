@Test public void doesBackOffIfEnableHypermediaSupportIsDeclaredManually(){
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(SampleConfig.class,HypermediaAutoConfiguration.class);
  this.context.refresh();
  this.context.getBean(LinkDiscoverers.class);
}
