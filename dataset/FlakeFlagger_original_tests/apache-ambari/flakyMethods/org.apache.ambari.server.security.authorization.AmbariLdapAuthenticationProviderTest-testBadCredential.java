@Test(expected=BadCredentialsException.class) public void testBadCredential() throws Exception {
  Authentication authentication=new UsernamePasswordAuthenticationToken("notFound","wrong");
  authenticationProvider.authenticate(authentication);
}
