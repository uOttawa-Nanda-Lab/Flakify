@Test public void testAuthenticationSuccess(){
  this.listener.onApplicationEvent(new AuthorizationFailureEvent(this,Arrays.<ConfigAttribute>asList(new SecurityConfig("USER")),new UsernamePasswordAuthenticationToken("user","password"),new AccessDeniedException("Bad user")));
  verify(this.publisher).publishEvent((ApplicationEvent)anyObject());
}
