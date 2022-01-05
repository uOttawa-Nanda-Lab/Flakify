@Test public void should_throw_error_if_array_is_null(){
  thrown.expectNullPointerException("The given conditions should not be null");
  Condition<Object>[] conditions=null;
  new ConcreteJoin(conditions);
}
