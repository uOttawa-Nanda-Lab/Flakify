@Test public void testJmxDisabled() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.jmxEnabled.name())).thenReturn("false");
  victim.init(mockFilterConfig);
}
