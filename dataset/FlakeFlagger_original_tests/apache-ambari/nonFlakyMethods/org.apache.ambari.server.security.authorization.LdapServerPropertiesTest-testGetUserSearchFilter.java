@Test public void testGetUserSearchFilter() throws Exception {
  assertEquals(INCORRECT_USER_SEARCH_FILTER,"(uid={0})",ldapServerProperties.getUserSearchFilter());
  ldapServerProperties.setUsernameAttribute("anotherName");
  assertEquals(INCORRECT_USER_SEARCH_FILTER,"(anotherName={0})",ldapServerProperties.getUserSearchFilter());
}
