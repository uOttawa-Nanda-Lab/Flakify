@Test public void should_pass_if_cause_has_expected_type_and_message(){
  throwables.assertHasCause(someInfo(),throwableWithCause,new IllegalArgumentException(EXCEPTION_MESSAGE));
}
