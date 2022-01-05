@Test public void should_throw_error_if_Collection_contains_nulls(){
  thrown.expectNullPointerException("The given conditions should not have null entries");
  Collection<Condition<Object>> conditions=new ArrayList<>();
  conditions.add(new TestCondition<>());
  conditions.add(null);
  new ConcreteJoin(conditions);
}
