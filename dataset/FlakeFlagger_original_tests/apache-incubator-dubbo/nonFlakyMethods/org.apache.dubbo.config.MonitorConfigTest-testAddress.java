@Test public void testAddress() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setAddress("monitor-addr");
  assertThat(monitor.getAddress(),equalTo("monitor-addr"));
  Map<String,String> parameters=new HashMap<String,String>();
  MonitorConfig.appendParameters(parameters,monitor);
  assertThat(parameters.isEmpty(),is(true));
}
