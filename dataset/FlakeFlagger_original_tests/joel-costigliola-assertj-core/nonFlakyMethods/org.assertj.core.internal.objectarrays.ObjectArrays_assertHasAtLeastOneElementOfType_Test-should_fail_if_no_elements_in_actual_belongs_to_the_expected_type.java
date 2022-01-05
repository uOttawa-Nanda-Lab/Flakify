@Test public void should_fail_if_no_elements_in_actual_belongs_to_the_expected_type(){
  try {
    arrays.assertHasAtLeastOneElementOfType(someInfo(),array,Float.class);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveAtLeastOneElementOfType(array,Float.class).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
