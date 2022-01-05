@Test public void testWorkingSessionWhenUsernameIsThere(){
  when(context.getSessionCookie()).thenReturn(sessionCookie);
  when(sessionCookie.get("username")).thenReturn("myname");
  secureFilter.filter(filterChain,context);
  verify(filterChain).next(context);
}
