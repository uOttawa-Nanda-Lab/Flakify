@Test public void should_create_new_AnyOf_with_passed_Conditions(){
  Condition<Object>[] conditions=array(new TestCondition<>(),new TestCondition<>());
  Condition<Object> created=AnyOf.anyOf(conditions);
  assertEquals(AnyOf.class,created.getClass());
  AnyOf<Object> anyOf=(AnyOf<Object>)created;
  assertEquals(newArrayList(conditions),anyOf.conditions);
}
