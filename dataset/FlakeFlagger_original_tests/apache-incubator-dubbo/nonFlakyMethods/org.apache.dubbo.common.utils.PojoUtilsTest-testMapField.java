@Test public void testMapField() throws Exception {
  TestData data=new TestData();
  Child child=newChild("first",1);
  data.addChild(child);
  child=newChild("second",2);
  data.addChild(child);
  child=newChild("third",3);
  data.addChild(child);
  data.setList(Arrays.asList(newChild("forth",4)));
  Object obj=PojoUtils.generalize(data);
  Assert.assertEquals(3,data.getChildren().size());
  assertTrue(data.getChildren().get("first").getClass() == Child.class);
  Assert.assertEquals(1,data.getList().size());
  assertTrue(data.getList().get(0).getClass() == Child.class);
  TestData realizadData=(TestData)PojoUtils.realize(obj,TestData.class);
  Assert.assertEquals(data.getChildren().size(),realizadData.getChildren().size());
  Assert.assertEquals(data.getChildren().keySet(),realizadData.getChildren().keySet());
  for (  Map.Entry<String,Child> entry : data.getChildren().entrySet()) {
    Child c=realizadData.getChildren().get(entry.getKey());
    Assert.assertNotNull(c);
    Assert.assertEquals(entry.getValue().getName(),c.getName());
    Assert.assertEquals(entry.getValue().getAge(),c.getAge());
  }
  Assert.assertEquals(1,realizadData.getList().size());
  Assert.assertEquals(data.getList().get(0).getName(),realizadData.getList().get(0).getName());
  Assert.assertEquals(data.getList().get(0).getAge(),realizadData.getList().get(0).getAge());
}
