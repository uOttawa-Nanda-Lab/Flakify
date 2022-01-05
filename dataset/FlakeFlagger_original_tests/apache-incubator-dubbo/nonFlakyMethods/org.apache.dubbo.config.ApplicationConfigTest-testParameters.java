@Test public void testParameters() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setQosAcceptForeignIp(true);
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put("k1","v1");
  ApplicationConfig.appendParameters(parameters,application);
  assertThat(parameters,hasEntry("k1","v1"));
  assertThat(parameters,hasEntry(Constants.ACCEPT_FOREIGN_IP,"true"));
}
