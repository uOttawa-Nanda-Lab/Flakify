@Test public void testProtocol() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setProtocol("protocol");
  assertThat(monitor.getProtocol(),equalTo("protocol"));
  Map<String,String> parameters=new HashMap<String,String>();
  MonitorConfig.appendParameters(parameters,monitor);
  assertThat(parameters.isEmpty(),is(true));
}
