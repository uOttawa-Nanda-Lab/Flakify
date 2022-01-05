@Test public void should_fail_if_other_is_null(){
  thrown.expectNullPointerException("The Iterable to compare actual size with should not be null");
  Iterable<?> other=null;
  iterables.assertHasSameSizeAs(someInfo(),newArrayList("Yoda","Luke"),other);
}
