/** 
 * Set filter init params with proper values and check they are the same in  {@link WroConfiguration} object.
 */
@Test(expected=WroRuntimeException.class) public void testFilterInitParamsAreWrong() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.cacheUpdatePeriod.name())).thenReturn("InvalidNumber");
  when(mockFilterConfig.getInitParameter(ConfigConstants.modelUpdatePeriod.name())).thenReturn("100");
  victim.init(mockFilterConfig);
}
