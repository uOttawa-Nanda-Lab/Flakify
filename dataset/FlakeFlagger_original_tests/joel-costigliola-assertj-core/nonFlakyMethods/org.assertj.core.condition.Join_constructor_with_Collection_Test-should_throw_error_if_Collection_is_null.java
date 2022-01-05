@Test public void should_throw_error_if_Collection_is_null(){
  thrown.expectNullPointerException("The given conditions should not be null");
  Collection<Condition<Object>> conditions=null;
  new ConcreteJoin(conditions);
}
