@Test public void should_create_ComparisonFailure_if_JUnit4_is_present_and_trim_spaces_in_formatted_description(){
  when(formatter.format(description)).thenReturn(formattedDescription);
  AssertionError error=factory.newAssertionError(description,new StandardRepresentation());
  assertEquals(ComparisonFailure.class,error.getClass());
  assertEquals("[Jedi] expected:<\"[Yoda]\"> but was:<\"[Luke]\">",error.getMessage());
}
