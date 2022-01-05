@Test public void testParameters() throws Exception {
  MonitorConfig monitor=new MonitorConfig();
  Map<String,String> parameters=Collections.singletonMap("k1","v1");
  monitor.setParameters(parameters);
  assertThat(monitor.getParameters(),hasEntry("k1","v1"));
}
