@Test public void should_throw_error_if_set_is_null(){
  actual=newArrayList("Yoda","Luke");
  thrown.expectNullPointerException(iterableToLookForIsNull());
  iterables.assertIsSubsetOf(someInfo(),actual,null);
}
