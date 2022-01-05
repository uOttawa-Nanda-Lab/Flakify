@Test public void should_pass_if_cause_is_instance_of_expected_type() throws Exception {
  throwables.assertHasCauseInstanceOf(someInfo(),throwableWithCause,RuntimeException.class);
}
