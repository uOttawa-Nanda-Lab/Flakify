@Test public void should_pass_if_fields_are_public_protected_or_private(){
  classes.assertHasDeclaredFields(someInfo(),actual,"publicField","protectedField","privateField");
}
