@Test public void should_throw_exception_if_expected_type_is_null(){
  thrown.expect(NullPointerException.class);
  arrays.assertHasAtLeastOneElementOfType(someInfo(),array,null);
}
