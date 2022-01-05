@Test public void should_fail_if_comparator_is_null(){
  thrown.expect(NullPointerException.class);
  lists.assertIsSortedAccordingToComparator(someInfo(),newArrayList(),null);
}
