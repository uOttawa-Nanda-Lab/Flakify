@Test public void registersJodaModuleAutomatically(){
  this.context.register(JacksonAutoConfiguration.class);
  this.context.refresh();
  ObjectMapper objectMapper=this.context.getBean(ObjectMapper.class);
  assertThat(objectMapper.canSerialize(LocalDateTime.class),is(true));
}
