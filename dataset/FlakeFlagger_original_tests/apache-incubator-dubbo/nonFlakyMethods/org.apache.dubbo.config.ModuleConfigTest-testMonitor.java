@Test public void testMonitor() throws Exception {
  ModuleConfig module=new ModuleConfig();
  module.setMonitor("monitor-addr1");
  assertThat(module.getMonitor().getAddress(),equalTo("monitor-addr1"));
  module.setMonitor(new MonitorConfig("monitor-addr2"));
  assertThat(module.getMonitor().getAddress(),equalTo("monitor-addr2"));
}
