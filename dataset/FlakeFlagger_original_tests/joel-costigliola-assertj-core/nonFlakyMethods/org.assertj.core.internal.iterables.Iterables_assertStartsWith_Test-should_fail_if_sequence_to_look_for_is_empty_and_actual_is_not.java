@Test public void should_fail_if_sequence_to_look_for_is_empty_and_actual_is_not(){
  thrown.expect(AssertionError.class);
  iterables.assertStartsWith(someInfo(),actual,emptyArray());
}
