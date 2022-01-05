@Test public void should_fail_if_object_parameter_is_not_an_array(){
  thrown.expect(IllegalArgumentException.class);
  shouldBeSorted(1,"not an array");
}
