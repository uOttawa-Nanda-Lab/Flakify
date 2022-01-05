public void testGetUserUnknown() throws Exception {
  User user=authenticator.getUserForToken(UNKNOWN_TOKEN);
  assertNull(user);
}
