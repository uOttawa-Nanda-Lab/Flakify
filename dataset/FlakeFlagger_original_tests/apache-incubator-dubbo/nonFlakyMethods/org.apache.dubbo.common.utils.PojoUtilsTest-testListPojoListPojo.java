@Test public void testListPojoListPojo() throws Exception {
  InnerPojo<Parent> parentList=new InnerPojo<Parent>();
  Parent parent=new Parent();
  parent.setName("zhangsan");
  parent.setAge(Integer.MAX_VALUE);
  parentList.setList(Arrays.asList(parent));
  ListResult<InnerPojo<Parent>> list=new ListResult<InnerPojo<Parent>>();
  list.setResult(Arrays.asList(parentList));
  Object generializeObject=PojoUtils.generalize(list);
  Object realizeObject=PojoUtils.realize(generializeObject,ListResult.class);
  assertTrue(realizeObject instanceof ListResult);
  ListResult realizeList=(ListResult)realizeObject;
  List realizeInnerList=realizeList.getResult();
  Assert.assertEquals(1,realizeInnerList.size());
  assertTrue(realizeInnerList.get(0) instanceof InnerPojo);
  InnerPojo realizeParentList=(InnerPojo)realizeInnerList.get(0);
  Assert.assertEquals(1,realizeParentList.getList().size());
  assertTrue(realizeParentList.getList().get(0) instanceof Parent);
  Parent realizeParent=(Parent)realizeParentList.getList().get(0);
  Assert.assertEquals(parent.getName(),realizeParent.getName());
  Assert.assertEquals(parent.getAge(),realizeParent.getAge());
}
