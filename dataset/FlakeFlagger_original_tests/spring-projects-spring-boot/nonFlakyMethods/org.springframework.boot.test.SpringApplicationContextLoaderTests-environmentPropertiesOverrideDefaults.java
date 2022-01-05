@Test public void environmentPropertiesOverrideDefaults() throws Exception {
  Map<String,Object> config=getEnvironmentProperties(OverrideConfig.class);
  assertKey(config,"server.port","2345");
}
