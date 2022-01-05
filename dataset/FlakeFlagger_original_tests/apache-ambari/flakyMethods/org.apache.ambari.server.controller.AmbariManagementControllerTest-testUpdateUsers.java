@SuppressWarnings("serial") @Test public void testUpdateUsers() throws Exception {
  createUser("user1");
  users.createDefaultRoles();
  UserRequest request=new UserRequest("user1");
  request.setRoles(new HashSet<String>(){
{
      add("user");
      add("admin");
    }
  }
);
  controller.updateUsers(Collections.singleton(request));
}
