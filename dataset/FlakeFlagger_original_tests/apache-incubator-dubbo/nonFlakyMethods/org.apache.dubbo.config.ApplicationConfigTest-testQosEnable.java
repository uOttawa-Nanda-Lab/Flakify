@Test public void testQosEnable() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setQosEnable(true);
  assertThat(application.getQosEnable(),is(true));
  Map<String,String> parameters=new HashMap<String,String>();
  ApplicationConfig.appendParameters(parameters,application);
  assertThat(parameters,hasEntry(Constants.QOS_ENABLE,"true"));
}
