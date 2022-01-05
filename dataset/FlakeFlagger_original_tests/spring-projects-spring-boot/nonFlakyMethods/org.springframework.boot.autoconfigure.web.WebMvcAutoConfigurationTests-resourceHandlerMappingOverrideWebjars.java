@Test public void resourceHandlerMappingOverrideWebjars() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(WebJars.class,Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  Map<String,List<Resource>> mappingLocations=getMappingLocations();
  assertThat(mappingLocations.get("/webjars/**").size(),equalTo(1));
  assertThat(mappingLocations.get("/webjars/**").get(0),equalTo((Resource)new ClassPathResource("/foo/")));
}
