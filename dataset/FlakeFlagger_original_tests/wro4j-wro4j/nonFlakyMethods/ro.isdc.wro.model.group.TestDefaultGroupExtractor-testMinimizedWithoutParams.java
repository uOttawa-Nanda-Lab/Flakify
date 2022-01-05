@Test public void testMinimizedWithoutParams(){
  final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);
  assertTrue(groupExtractor.isMinimized(request));
}
