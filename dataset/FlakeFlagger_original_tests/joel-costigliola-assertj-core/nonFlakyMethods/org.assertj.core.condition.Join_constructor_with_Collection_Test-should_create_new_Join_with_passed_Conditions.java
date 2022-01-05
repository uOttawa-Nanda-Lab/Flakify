@Test public void should_create_new_Join_with_passed_Conditions(){
  Collection<Condition<Object>> conditions=new ArrayList<>();
  conditions.add(new TestCondition<>());
  Join<Object> join=new ConcreteJoin(conditions);
  assertEquals(conditions,join.conditions);
}
