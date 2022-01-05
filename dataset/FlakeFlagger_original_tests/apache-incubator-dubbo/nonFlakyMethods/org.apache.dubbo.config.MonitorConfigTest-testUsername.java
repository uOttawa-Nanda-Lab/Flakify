@Test public void testUsername() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setUsername("user");
  assertThat(monitor.getUsername(),equalTo("user"));
  Map<String,String> parameters=new HashMap<String,String>();
  MonitorConfig.appendParameters(parameters,monitor);
  assertThat(parameters.isEmpty(),is(true));
}
