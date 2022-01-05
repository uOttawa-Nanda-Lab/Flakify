@Test public void testMinimizedWithFalseParam(){
  final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
  Mockito.when(request.getParameter(DefaultGroupExtractor.PARAM_MINIMIZE)).thenReturn("false");
  assertFalse(groupExtractor.isMinimized(request));
}
