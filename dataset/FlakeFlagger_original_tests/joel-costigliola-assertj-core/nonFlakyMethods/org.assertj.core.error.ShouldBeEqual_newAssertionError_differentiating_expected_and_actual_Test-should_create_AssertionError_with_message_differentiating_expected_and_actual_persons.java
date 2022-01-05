@Test public void should_create_AssertionError_with_message_differentiating_expected_and_actual_persons(){
  Person actual=new Person("Jake",43);
  Person expected=new Person("Jake",47);
  shouldBeEqual=(ShouldBeEqual)shouldBeEqual(actual,expected,new StandardRepresentation());
  shouldBeEqual.descriptionFormatter=mock(DescriptionFormatter.class);
  when(shouldBeEqual.descriptionFormatter.format(description)).thenReturn(formattedDescription);
  AssertionError error=shouldBeEqual.newAssertionError(description,new StandardRepresentation());
  assertThat(error.getMessage()).isEqualTo("[my test] \nExpecting:\n <\"Person[name=Jake] (Person@" + toHexString(actual.hashCode()) + ")\">\nto be equal to:\n <\"Person[name=Jake] (Person@"+ toHexString(expected.hashCode())+ ")\">\nbut was not.");
}
