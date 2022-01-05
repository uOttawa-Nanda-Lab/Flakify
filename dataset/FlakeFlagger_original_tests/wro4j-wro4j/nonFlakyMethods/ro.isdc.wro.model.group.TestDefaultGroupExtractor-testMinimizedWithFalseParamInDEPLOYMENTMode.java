/** 
 * Test that in DEPLOYMENT mode, the minimize flag cannot be false, no matter what parameter value is supplied.
 */
@Test public void testMinimizedWithFalseParamInDEPLOYMENTMode(){
  final WroConfiguration config=new WroConfiguration();
  config.setDebug(false);
  Context.get().setConfig(config);
  final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
  Mockito.when(request.getParameter(DefaultGroupExtractor.PARAM_MINIMIZE)).thenReturn("false");
  assertTrue(groupExtractor.isMinimized(request));
}
