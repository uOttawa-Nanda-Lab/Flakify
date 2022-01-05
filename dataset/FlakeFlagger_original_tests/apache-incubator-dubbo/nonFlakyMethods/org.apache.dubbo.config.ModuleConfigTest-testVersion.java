@Test public void testVersion() throws Exception {
  ModuleConfig module=new ModuleConfig();
  module.setName("module-name");
  module.setVersion("1.0.0");
  assertThat(module.getVersion(),equalTo("1.0.0"));
  Map<String,String> parameters=new HashMap<String,String>();
  ModuleConfig.appendParameters(parameters,module);
  assertThat(parameters,hasEntry("module.version","1.0.0"));
}
