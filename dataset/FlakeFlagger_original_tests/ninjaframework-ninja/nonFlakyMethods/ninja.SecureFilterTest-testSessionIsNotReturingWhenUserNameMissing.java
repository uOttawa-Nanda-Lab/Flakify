@Test public void testSessionIsNotReturingWhenUserNameMissing(){
  when(context.getSessionCookie()).thenReturn(sessionCookie);
  when(sessionCookie.get("username")).thenReturn(null);
  Result result=secureFilter.filter(filterChain,context);
  assertEquals(NinjaConstant.LOCATION_VIEW_FTL_HTML_FORBIDDEN,result.getTemplate());
  verifyZeroInteractions(filterChain);
}
