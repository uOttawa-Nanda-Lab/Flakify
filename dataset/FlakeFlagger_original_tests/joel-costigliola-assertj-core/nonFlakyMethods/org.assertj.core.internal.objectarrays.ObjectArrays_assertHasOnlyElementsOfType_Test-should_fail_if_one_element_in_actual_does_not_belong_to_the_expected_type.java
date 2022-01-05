@Test public void should_fail_if_one_element_in_actual_does_not_belong_to_the_expected_type(){
  try {
    arrays.assertHasOnlyElementsOfType(someInfo(),array,Long.class);
  }
 catch (  AssertionError e) {
    assertThat(e).hasMessage(shouldHaveOnlyElementsOfType(array,Long.class,Integer.class).create());
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
