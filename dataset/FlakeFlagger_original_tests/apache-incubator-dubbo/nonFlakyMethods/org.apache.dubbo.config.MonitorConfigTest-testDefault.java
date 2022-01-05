@Test public void testDefault() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setDefault(true);
  assertThat(monitor.isDefault(),is(true));
}
