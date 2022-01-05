@Test public void testConvertRolesToAuthorities() throws Exception {
  Collection<RoleEntity> roles=new ArrayList<RoleEntity>();
  RoleEntity role=new RoleEntity();
  role.setRoleName("admin");
  roles.add(role);
  role=new RoleEntity();
  role.setRoleName("user");
  roles.add(role);
  Collection<GrantedAuthority> authorities=new AuthorizationHelper().convertRolesToAuthorities(roles);
  assertEquals("Wrong number of authorities",2,authorities.size());
  Iterator<GrantedAuthority> iterator=authorities.iterator();
  assertEquals("Wrong authority name","ADMIN",iterator.next().getAuthority());
}
