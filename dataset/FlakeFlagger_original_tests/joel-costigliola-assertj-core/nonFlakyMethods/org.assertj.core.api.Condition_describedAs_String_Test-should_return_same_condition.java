@Test public void should_return_same_condition(){
  Condition<Object> returnedCondition=condition.describedAs(someTextDescription());
  assertSame(condition,returnedCondition);
}
