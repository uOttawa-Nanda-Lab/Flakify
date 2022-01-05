@Test public void testValidHeaderParamsAreSet() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.header.name())).thenReturn("ETag: 998989 | Expires: Thu, 15 Apr 2010 20:00:00 GMT");
  victim.init(mockFilterConfig);
}
