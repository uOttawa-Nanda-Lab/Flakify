@Test public void resourceHandlerMappingOverrideAll() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.register(AllResources.class,Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  Map<String,List<Resource>> mappingLocations=getMappingLocations();
  assertThat(mappingLocations.get("/**").size(),equalTo(1));
  assertThat(mappingLocations.get("/**").get(0),equalTo((Resource)new ClassPathResource("/foo/")));
}
