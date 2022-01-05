@Test public void should_return_text_of_description(){
  Description description=mock(Description.class);
  info.description(description);
  when(description.value()).thenReturn("Yoda");
  assertEquals("Yoda",info.descriptionText());
}
