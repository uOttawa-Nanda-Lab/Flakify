@SuppressWarnings("serial") @Test public void testDeleteUsers() throws Exception {
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
  request=new UserRequest("user1");
  controller.deleteUsers(Collections.singleton(request));
  Set<UserResponse> responses=controller.getUsers(Collections.singleton(new UserRequest(null)));
  Assert.assertEquals(0,responses.size());
  RoleDAO roleDao=injector.getInstance(RoleDAO.class);
  RoleEntity re1=roleDao.findByName("user");
  RoleEntity re2=roleDao.findByName("admin");
  Assert.assertNotNull(re1);
  Assert.assertNotNull(re2);
}
