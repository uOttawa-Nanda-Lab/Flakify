@Test public void testGroup() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  monitor.setGroup("group");
  assertThat(monitor.getGroup(),equalTo("group"));
}
