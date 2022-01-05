@Test public void testSecureFilter(){
  when(context.getSessionCookie()).thenReturn(null);
  secureFilter.filter(filterChain,context);
  verifyZeroInteractions(filterChain);
}
