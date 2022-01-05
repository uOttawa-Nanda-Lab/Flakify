public void testGetDisabledUser() throws Exception {
  User user=authenticator.getUserForToken(DISABLED_TOKEN);
  assertNotNull(user);
  assertEquals(user.getName(),DISABLED_USERNAME);
  assertFalse(user.isAdmin());
  assertTrue(user.isDisabled());
}
