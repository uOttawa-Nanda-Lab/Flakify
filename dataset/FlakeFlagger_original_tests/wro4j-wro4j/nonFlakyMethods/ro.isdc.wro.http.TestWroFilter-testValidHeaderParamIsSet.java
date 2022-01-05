@Test public void testValidHeaderParamIsSet() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.header.name())).thenReturn("ETag: 998989");
  victim.init(mockFilterConfig);
}
