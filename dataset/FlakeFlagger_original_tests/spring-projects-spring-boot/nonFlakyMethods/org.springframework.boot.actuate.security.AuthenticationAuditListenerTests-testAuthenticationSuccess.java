@Test public void testAuthenticationSuccess(){
  this.listener.onApplicationEvent(new AuthenticationSuccessEvent(new UsernamePasswordAuthenticationToken("user","password")));
  verify(this.publisher).publishEvent((ApplicationEvent)anyObject());
}
