@Test public void testGetAllUsers() throws Exception {
  users.createUser("user","user");
  users.createUser("admin","admin");
  List<User> userList=users.getAllUsers();
  assertEquals(2,userList.size());
  for (  User user : userList) {
    assertEquals(false,user.isLdapUser());
  }
  assertEquals(2,userDAO.findAll().size());
  UserEntity userEntity=userDAO.findLocalUserByName("user");
  assertNotNull("user",userEntity.getUserPassword());
  users.modifyPassword("user","user","resu");
  assertNotSame(userEntity.getUserPassword(),userDAO.findLocalUserByName("user").getUserPassword());
}
