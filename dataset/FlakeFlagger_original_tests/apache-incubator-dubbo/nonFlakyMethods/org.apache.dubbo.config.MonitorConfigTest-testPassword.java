@Test public void testPassword() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setPassword("secret");
  assertThat(monitor.getPassword(),equalTo("secret"));
  Map<String,String> parameters=new HashMap<String,String>();
  MonitorConfig.appendParameters(parameters,monitor);
  assertThat(parameters.isEmpty(),is(true));
}
