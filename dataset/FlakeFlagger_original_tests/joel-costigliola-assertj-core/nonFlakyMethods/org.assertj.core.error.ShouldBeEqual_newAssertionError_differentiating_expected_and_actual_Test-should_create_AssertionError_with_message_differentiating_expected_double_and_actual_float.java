@Test public void should_create_AssertionError_with_message_differentiating_expected_double_and_actual_float(){
  Float actual=42f;
  Double expected=42d;
  shouldBeEqual=(ShouldBeEqual)shouldBeEqual(actual,expected,new StandardRepresentation());
  shouldBeEqual.descriptionFormatter=mock(DescriptionFormatter.class);
  when(shouldBeEqual.descriptionFormatter.format(description)).thenReturn(formattedDescription);
  AssertionError error=shouldBeEqual.newAssertionError(description,new StandardRepresentation());
  assertThat(error.getMessage()).isEqualTo("[my test] expected:<42.0[]> but was:<42.0[f]>");
}
