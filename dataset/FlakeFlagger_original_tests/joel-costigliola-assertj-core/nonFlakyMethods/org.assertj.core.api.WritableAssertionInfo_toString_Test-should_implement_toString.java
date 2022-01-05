@Test public void should_implement_toString(){
  when(description.value()).thenReturn("Yoda");
  assertEquals("WritableAssertionInfo[overridingErrorMessage='Jedi', description='Yoda']",info.toString());
}
