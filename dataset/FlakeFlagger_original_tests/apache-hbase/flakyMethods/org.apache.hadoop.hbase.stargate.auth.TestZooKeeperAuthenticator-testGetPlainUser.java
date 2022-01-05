public void testGetPlainUser() throws Exception {
  User user=authenticator.getUserForToken(USER_TOKEN);
  assertNotNull(user);
  assertEquals(user.getName(),USER_USERNAME);
  assertFalse(user.isAdmin());
  assertFalse(user.isDisabled());
}
