@Test public void testPromoteUser() throws Exception {
  users.createUser("admin","admin");
  User user=users.getLocalUser("admin");
  assertTrue(user.getRoles().contains(users.getUserRole()));
  assertFalse(user.getRoles().contains(users.getAdminRole()));
  users.promoteToAdmin(user);
  user=users.getLocalUser("admin");
  assertTrue(user.getRoles().contains(users.getAdminRole()));
  users.demoteAdmin(user);
  user=users.getLocalUser("admin");
  assertFalse(user.getRoles().contains(users.getAdminRole()));
}
