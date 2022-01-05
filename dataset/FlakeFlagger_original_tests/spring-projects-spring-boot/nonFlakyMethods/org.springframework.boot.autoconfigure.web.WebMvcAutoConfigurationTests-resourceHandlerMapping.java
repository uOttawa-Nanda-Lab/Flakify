@Test public void resourceHandlerMapping() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  Map<String,List<Resource>> mappingLocations=getMappingLocations();
  assertThat(mappingLocations.get("/**").size(),equalTo(5));
  assertThat(mappingLocations.get("/webjars/**").size(),equalTo(1));
  assertThat(mappingLocations.get("/webjars/**").get(0),equalTo((Resource)new ClassPathResource("/META-INF/resources/webjars/")));
}
