@Test public void noMessageCodesResolver() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(AllResources.class,Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNull(this.context.getBean(WebMvcAutoConfigurationAdapter.class).getMessageCodesResolver());
}
