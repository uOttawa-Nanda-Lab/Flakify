@Test public void should_create_new_Join_with_passed_Conditions(){
  Condition<Object>[] conditions=array(new TestCondition<>(),new TestCondition<>());
  Join<Object> join=new ConcreteJoin(conditions);
  assertEquals(newArrayList(conditions),join.conditions);
}
