@Test public void testMonitor() throws Exception {
  ApplicationConfig application=new ApplicationConfig("app");
  application.setMonitor(new MonitorConfig("monitor-addr"));
  assertThat(application.getMonitor().getAddress(),equalTo("monitor-addr"));
  application.setMonitor("monitor-addr");
  assertThat(application.getMonitor().getAddress(),equalTo("monitor-addr"));
}
