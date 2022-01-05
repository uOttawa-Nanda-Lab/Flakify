@Test public void testCreateAndGetUsers() throws Exception {
  createUser("user1");
  Set<UserResponse> r=controller.getUsers(Collections.singleton(new UserRequest("user1")));
  Assert.assertEquals(1,r.size());
  UserResponse resp=r.iterator().next();
  Assert.assertEquals("user1",resp.getUsername());
}
