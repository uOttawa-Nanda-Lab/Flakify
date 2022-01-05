/** 
 * Set filter init params with proper values and check they are the same in  {@link WroConfiguration} object.
 */
@Test public void testConfigurationInitParam() throws Exception {
  when(mockFilterConfig.getInitParameter(FilterConfigWroConfigurationFactory.PARAM_CONFIGURATION)).thenReturn("anyOtherString");
  victim.init(mockFilterConfig);
  Assert.assertEquals(true,victim.getConfiguration().isDebug());
}
