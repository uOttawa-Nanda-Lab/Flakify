@Test public void testMinimizedWithInvalidParamValue(){
  final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
  Mockito.when(request.getParameter(DefaultGroupExtractor.PARAM_MINIMIZE)).thenReturn("someInvalidBoolean");
  assertTrue(groupExtractor.isMinimized(request));
}
