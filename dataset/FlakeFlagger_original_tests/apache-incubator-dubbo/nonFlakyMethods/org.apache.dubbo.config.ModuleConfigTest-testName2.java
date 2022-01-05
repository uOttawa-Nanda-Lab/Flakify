@Test public void testName2() throws Exception {
  ModuleConfig module=new ModuleConfig();
  module.setName("module-name");
  assertThat(module.getName(),equalTo("module-name"));
  assertThat(module.getId(),equalTo("module-name"));
  Map<String,String> parameters=new HashMap<String,String>();
  ModuleConfig.appendParameters(parameters,module);
  assertThat(parameters,hasEntry("module","module-name"));
}
