@Test public void customJacksonModules() throws Exception {
  this.context.register(ModulesConfig.class,JacksonAutoConfiguration.class);
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  @SuppressWarnings({"unchecked","unused"}) ObjectMapper result=verify(mapper).registerModules((Iterable<Module>)argThat(hasItem(this.context.getBean("jacksonModule",Module.class))));
}
