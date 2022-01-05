@Test public void testVersion() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setVersion("1.0.0");
  assertThat(monitor.getVersion(),equalTo("1.0.0"));
}
