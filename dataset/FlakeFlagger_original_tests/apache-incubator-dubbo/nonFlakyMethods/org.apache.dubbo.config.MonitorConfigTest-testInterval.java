@Test public void testInterval() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setInterval("100");
  assertThat(monitor.getInterval(),equalTo("100"));
}
