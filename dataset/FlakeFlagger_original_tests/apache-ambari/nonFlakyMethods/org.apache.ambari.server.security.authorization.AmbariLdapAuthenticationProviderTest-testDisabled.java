@Test public void testDisabled() throws Exception {
  configuration.setClientSecurityType(ClientSecurityType.LOCAL);
  Authentication authentication=new UsernamePasswordAuthenticationToken("allowedUser","password");
  Authentication auth=authenticationProvider.authenticate(authentication);
  Assert.assertTrue(auth == null);
}
