@Test public void should_pass_if_actual_has_no_cause_and_expected_cause_is_null(){
  throwables.assertHasCause(someInfo(),new Throwable(),null);
}
