@Test public void test_LoopPojoInMap() throws Exception {
  Parent p=new Parent();
  p.setAge(10);
  p.setName("jerry");
  Child c=new Child();
  c.setToy("haha");
  p.setChild(c);
  c.setParent(p);
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("k",p);
  Object generalize=PojoUtils.generalize(map);
  @SuppressWarnings("unchecked") Map<String,Object> realize=(Map<String,Object>)PojoUtils.realize(generalize,Map.class,getType("getMapGenericType"));
  Parent parent=(Parent)realize.get("k");
  assertEquals(10,parent.getAge());
  assertEquals("jerry",parent.getName());
  assertEquals("haha",parent.getChild().getToy());
  assertSame(parent,parent.getChild().getParent());
}
