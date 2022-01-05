@Test public void test_Loop_pojo() throws Exception {
  Parent p=new Parent();
  p.setAge(10);
  p.setName("jerry");
  Child c=new Child();
  c.setToy("haha");
  p.setChild(c);
  c.setParent(p);
  Object generalize=PojoUtils.generalize(p);
  Parent parent=(Parent)PojoUtils.realize(generalize,Parent.class);
  assertEquals(10,parent.getAge());
  assertEquals("jerry",parent.getName());
  assertEquals("haha",parent.getChild().getToy());
  assertSame(parent,parent.getChild().getParent());
}
