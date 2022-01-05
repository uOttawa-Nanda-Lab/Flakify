@Test(expected=NullPointerException.class) public void should_throws_exception_if_iterable_is_null(){
  Iterables.sizeOf(null);
}
