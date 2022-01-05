@Test(expected=UsernameNotFoundException.class) public void testUsernameNotFound() throws Exception {
  userDetailsService.loadUserByUsername("notExists_123123123");
}
