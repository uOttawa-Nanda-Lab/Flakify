@Test public void entityLinksCreated() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(HypermediaAutoConfiguration.class);
  this.context.refresh();
  EntityLinks discoverers=this.context.getBean(EntityLinks.class);
  assertNotNull(discoverers);
}
