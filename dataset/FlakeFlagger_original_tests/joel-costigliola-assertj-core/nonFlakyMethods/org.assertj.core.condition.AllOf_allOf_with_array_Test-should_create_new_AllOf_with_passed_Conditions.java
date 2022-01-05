@Test public void should_create_new_AllOf_with_passed_Conditions(){
  Condition<Object>[] conditions=array(new TestCondition<>(),new TestCondition<>());
  Condition<Object> created=AllOf.allOf(conditions);
  assertEquals(AllOf.class,created.getClass());
  AllOf<Object> allOf=(AllOf<Object>)created;
  assertEquals(newArrayList(conditions),allOf.conditions);
}
