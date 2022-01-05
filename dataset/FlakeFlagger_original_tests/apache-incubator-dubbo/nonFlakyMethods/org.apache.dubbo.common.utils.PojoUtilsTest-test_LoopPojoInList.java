@Test public void test_LoopPojoInList() throws Exception {
  Parent p=new Parent();
  p.setAge(10);
  p.setName("jerry");
  Child c=new Child();
  c.setToy("haha");
  p.setChild(c);
  c.setParent(p);
  List<Object> list=new ArrayList<Object>();
  list.add(p);
  Object generalize=PojoUtils.generalize(list);
  @SuppressWarnings("unchecked") List<Object> realize=(List<Object>)PojoUtils.realize(generalize,List.class,getType("getListGenericType"));
  Parent parent=(Parent)realize.get(0);
  assertEquals(10,parent.getAge());
  assertEquals("jerry",parent.getName());
  assertEquals("haha",parent.getChild().getToy());
  assertSame(parent,parent.getChild().getParent());
  Object[] objects=PojoUtils.realize(new Object[]{generalize},new Class[]{List.class},new Type[]{getType("getListGenericType")});
  assertTrue(((List)objects[0]).get(0) instanceof Parent);
}
