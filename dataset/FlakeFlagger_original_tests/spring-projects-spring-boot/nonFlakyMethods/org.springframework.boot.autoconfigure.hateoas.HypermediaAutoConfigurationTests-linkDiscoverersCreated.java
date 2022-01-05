@Test public void linkDiscoverersCreated() throws Exception {
  this.context=new AnnotationConfigWebApplicationContext();
  this.context.register(HypermediaAutoConfiguration.class);
  this.context.refresh();
  LinkDiscoverers discoverers=this.context.getBean(LinkDiscoverers.class);
  assertNotNull(discoverers);
  LinkDiscoverer discoverer=discoverers.getLinkDiscovererFor(MediaTypes.HAL_JSON);
  assertTrue(HalLinkDiscoverer.class.isInstance(discoverer));
}
