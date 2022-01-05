@Test public void doubleModuleRegistration() throws Exception {
  this.context.register(DoubleModulesConfig.class,HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  ObjectMapper mapper=this.context.getBean(ObjectMapper.class);
  assertEquals("{\"foo\":\"bar\"}",mapper.writeValueAsString(new Foo()));
}
