@Test public void should_pass_if_fields_are_public(){
  classes.assertHasFields(someInfo(),actual,"publicField");
}
