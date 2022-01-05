@Test(expected=UsernameNotFoundException.class) public void testEmptyRoles() throws Exception {
  UserEntity user=userDAO.findLocalUserByName("userWithoutRoles");
  userDetailsService.loadUserByUsername(user.getUserName());
}
