public void testGetAdminUser() throws Exception {
  User user=authenticator.getUserForToken(ADMIN_TOKEN);
  assertNotNull(user);
  assertEquals(user.getName(),ADMIN_USERNAME);
  assertTrue(user.isAdmin());
  assertFalse(user.isDisabled());
}
