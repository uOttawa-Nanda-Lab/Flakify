@Test public void testLoadUserByUsername() throws Exception {
  UserDetails userDetails=userDetailsService.loadUserByUsername("administrator");
  assertEquals("Wrong username","administrator",userDetails.getUsername());
  assertTrue("Password not matches",passwordEncoder.matches("admin",userDetails.getPassword()));
  assertFalse("Wrong password accepted",passwordEncoder.matches("wrong",userDetails.getPassword()));
}
