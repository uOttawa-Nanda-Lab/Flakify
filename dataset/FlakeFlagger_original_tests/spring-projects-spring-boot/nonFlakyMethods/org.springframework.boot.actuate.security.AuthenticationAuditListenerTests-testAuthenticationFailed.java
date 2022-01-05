@Test public void testAuthenticationFailed(){
  this.listener.onApplicationEvent(new AuthenticationFailureExpiredEvent(new UsernamePasswordAuthenticationToken("user","password"),new BadCredentialsException("Bad user")));
  verify(this.publisher).publishEvent((ApplicationEvent)anyObject());
}
