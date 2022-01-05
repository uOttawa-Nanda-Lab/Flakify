@Test public void testQosAcceptForeignIp() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setQosAcceptForeignIp(true);
  assertThat(application.getQosAcceptForeignIp(),is(true));
  Map<String,String> parameters=new HashMap<String,String>();
  ApplicationConfig.appendParameters(parameters,application);
  assertThat(parameters,hasEntry(Constants.ACCEPT_FOREIGN_IP,"true"));
}
