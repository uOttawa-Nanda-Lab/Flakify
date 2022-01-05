@Test public void testStackOverflow() throws Exception {
  Parent parent=Parent.getNewParent();
  parent.setAge(Integer.MAX_VALUE);
  String name=UUID.randomUUID().toString();
  parent.setName(name);
  Object generalize=PojoUtils.generalize(parent);
  assertTrue(generalize instanceof Map);
  Map map=(Map)generalize;
  assertEquals(Integer.MAX_VALUE,map.get("age"));
  assertEquals(name,map.get("name"));
  Parent realize=(Parent)PojoUtils.realize(generalize,Parent.class);
  assertEquals(Integer.MAX_VALUE,realize.getAge());
  assertEquals(name,realize.getName());
}
