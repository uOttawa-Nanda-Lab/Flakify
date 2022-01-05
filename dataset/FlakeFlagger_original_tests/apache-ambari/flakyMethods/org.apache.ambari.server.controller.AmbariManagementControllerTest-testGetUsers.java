@Test public void testGetUsers() throws Exception {
  createUser("user1");
  createUser("user2");
  createUser("user3");
  UserRequest request=new UserRequest(null);
  Set<UserResponse> responses=controller.getUsers(Collections.singleton(request));
  Assert.assertEquals(3,responses.size());
}
