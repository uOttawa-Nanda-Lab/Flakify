@SuppressWarnings("unchecked") @Test public void should_throw_error_if_array_of_entries_to_look_for_is_empty(){
  thrown.expect(AssertionError.class);
  maps.assertContains(someInfo(),actual,new MapEntry[0]);
}
