@Test public void should_throw_error_if_array_contains_nulls(){
  thrown.expectNullPointerException("The given conditions should not have null entries");
  Condition<Object>[] conditions=array(new TestCondition<>(),null);
  new ConcreteJoin(conditions);
}
