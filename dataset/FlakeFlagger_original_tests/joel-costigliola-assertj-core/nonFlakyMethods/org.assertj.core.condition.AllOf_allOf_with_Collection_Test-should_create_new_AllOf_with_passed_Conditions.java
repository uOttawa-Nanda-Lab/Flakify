@Test public void should_create_new_AllOf_with_passed_Conditions(){
  Collection<Condition<Object>> conditions=new ArrayList<>();
  conditions.add(new TestCondition<>());
  Condition<Object> created=AllOf.allOf(conditions);
  assertEquals(AllOf.class,created.getClass());
  AllOf<Object> allOf=(AllOf<Object>)created;
  assertEquals(conditions,allOf.conditions);
}
