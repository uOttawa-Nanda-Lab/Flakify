@Test public void should_pass_if_actual_has_only_elements_of_the_expected_type(){
  arrays.assertHasOnlyElementsOfType(someInfo(),array,Number.class);
}
