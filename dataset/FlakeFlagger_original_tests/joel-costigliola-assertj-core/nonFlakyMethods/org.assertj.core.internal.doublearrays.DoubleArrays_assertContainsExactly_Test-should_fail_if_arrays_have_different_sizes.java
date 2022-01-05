@Test public void should_fail_if_arrays_have_different_sizes(){
  thrown.expect(AssertionError.class);
  arrays.assertContainsExactly(someInfo(),actual,arrayOf(6d,8d));
}
