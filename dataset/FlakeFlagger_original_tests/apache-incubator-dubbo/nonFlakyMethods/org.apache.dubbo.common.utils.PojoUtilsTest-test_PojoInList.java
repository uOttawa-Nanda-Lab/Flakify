@Test public void test_PojoInList() throws Exception {
  Parent p=new Parent();
  p.setAge(10);
  p.setName("jerry");
  List<Object> list=new ArrayList<Object>();
  list.add(p);
  Object generalize=PojoUtils.generalize(list);
  @SuppressWarnings("unchecked") List<Object> realize=(List<Object>)PojoUtils.realize(generalize,List.class,getType("getListGenericType"));
  Parent parent=(Parent)realize.get(0);
  assertEquals(10,parent.getAge());
  assertEquals("jerry",parent.getName());
}
