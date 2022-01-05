@Test public void noCustomPropertyNamingStrategy() throws Exception {
  this.context.register(JacksonAutoConfiguration.class);
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertEquals("{\"propertyName\":null}",mapper.writeValueAsString(new Bar()));
}
