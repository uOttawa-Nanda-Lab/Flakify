@Test public void handerAdaptersCreated() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals(3,this.context.getBeanNamesForType(HandlerAdapter.class).length);
  assertFalse(this.context.getBean(RequestMappingHandlerAdapter.class).getMessageConverters().isEmpty());
  assertEquals(this.context.getBean(HttpMessageConverters.class).getConverters(),this.context.getBean(RequestMappingHandlerAdapter.class).getMessageConverters());
}
