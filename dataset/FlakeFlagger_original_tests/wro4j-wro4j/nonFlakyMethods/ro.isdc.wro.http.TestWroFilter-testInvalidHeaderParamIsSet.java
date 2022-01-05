@Test(expected=WroRuntimeException.class) public void testInvalidHeaderParamIsSet() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.header.name())).thenReturn("ETag 998989 expires 1");
  victim.init(mockFilterConfig);
}
