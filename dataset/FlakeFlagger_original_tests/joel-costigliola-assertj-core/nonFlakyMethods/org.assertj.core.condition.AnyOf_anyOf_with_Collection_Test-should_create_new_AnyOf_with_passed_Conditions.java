@Test public void should_create_new_AnyOf_with_passed_Conditions(){
  Collection<Condition<Object>> conditions=new ArrayList<>();
  conditions.add(new TestCondition<>());
  Condition<Object> created=AnyOf.anyOf(conditions);
  assertEquals(AnyOf.class,created.getClass());
  AnyOf<Object> anyOf=(AnyOf<Object>)created;
  assertEquals(conditions,anyOf.conditions);
}
