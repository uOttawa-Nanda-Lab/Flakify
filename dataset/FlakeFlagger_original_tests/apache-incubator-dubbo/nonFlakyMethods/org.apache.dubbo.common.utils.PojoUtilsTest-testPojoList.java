@Test public void testPojoList() throws Exception {
  ListResult<Parent> result=new ListResult<Parent>();
  List<Parent> list=new ArrayList<Parent>();
  Parent parent=new Parent();
  parent.setAge(Integer.MAX_VALUE);
  parent.setName("zhangsan");
  list.add(parent);
  result.setResult(list);
  Object generializeObject=PojoUtils.generalize(result);
  Object realizeObject=PojoUtils.realize(generializeObject,ListResult.class);
  assertTrue(realizeObject instanceof ListResult);
  ListResult listResult=(ListResult)realizeObject;
  List l=listResult.getResult();
  assertTrue(l.size() == 1);
  assertTrue(l.get(0) instanceof Parent);
  Parent realizeParent=(Parent)l.get(0);
  Assert.assertEquals(parent.getName(),realizeParent.getName());
  Assert.assertEquals(parent.getAge(),realizeParent.getAge());
}
