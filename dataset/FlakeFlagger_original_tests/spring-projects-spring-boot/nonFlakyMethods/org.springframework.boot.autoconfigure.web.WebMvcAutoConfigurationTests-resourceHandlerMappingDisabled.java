@Test public void resourceHandlerMappingDisabled() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.resources.add-mappings:false");
  this.context.register(Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  Map<String,List<Resource>> mappingLocations=getMappingLocations();
  assertThat(mappingLocations.size(),equalTo(0));
}
