@Test public void should_pass_if_no_fields_are_expected(){
  classes.assertHasDeclaredFields(someInfo(),actual);
}
