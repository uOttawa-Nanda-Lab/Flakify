@Test public void testPublicField() throws Exception {
  Parent parent=new Parent();
  parent.gender="female";
  parent.email="email@host.com";
  parent.setEmail("securityemail@host.com");
  Child child=new Child();
  parent.setChild(child);
  child.gender="male";
  child.setAge(20);
  child.setParent(parent);
  Object obj=PojoUtils.generalize(parent);
  Parent realizedParent=(Parent)PojoUtils.realize(obj,Parent.class);
  Assert.assertEquals(parent.gender,realizedParent.gender);
  Assert.assertEquals(child.gender,parent.getChild().gender);
  Assert.assertEquals(child.age,realizedParent.getChild().getAge());
  Assert.assertEquals(parent.getEmail(),realizedParent.getEmail());
  Assert.assertNull(realizedParent.email);
}
