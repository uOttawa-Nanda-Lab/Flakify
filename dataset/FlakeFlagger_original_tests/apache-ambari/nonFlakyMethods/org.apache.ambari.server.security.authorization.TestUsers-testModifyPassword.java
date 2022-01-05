@Test(expected=AmbariException.class) public void testModifyPassword() throws Exception {
  users.createUser("user","user");
  UserEntity userEntity=userDAO.findLocalUserByName("user");
  assertNotSame("user",userEntity.getUserPassword());
  assertTrue(passwordEncoder.matches("user",userEntity.getUserPassword()));
  users.modifyPassword("user","user","resu");
  assertNotSame(userEntity.getUserPassword(),userDAO.findLocalUserByName("user").getUserPassword());
  users.modifyPassword("user","error","new");
  fail("Exception was not thrown");
}
