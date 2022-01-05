@Test public void testMinimizedWithTrueParam(){
  final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
  Mockito.when(request.getParameter(DefaultGroupExtractor.PARAM_MINIMIZE)).thenReturn("true");
  assertTrue(groupExtractor.isMinimized(request));
}
