@Test public void testAuthenticationSwitch(){
  this.listener.onApplicationEvent(new AuthenticationSwitchUserEvent(new UsernamePasswordAuthenticationToken("user","password"),new User("user","password",AuthorityUtils.commaSeparatedStringToAuthorityList("USER"))));
  verify(this.publisher).publishEvent((ApplicationEvent)anyObject());
}
