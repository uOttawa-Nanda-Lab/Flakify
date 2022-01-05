@Test public void should_throw_exception_when_extracted_value_is_not_an_array_or_an_iterable(){
  try {
    assertThat(new CartoonCharacter[]{homer,fred}).flatExtracting("name");
    failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
  }
 catch (  IllegalArgumentException e) {
    assertThat(e).hasMessage("Flat extracting expects extracted values to be Iterables or arrays but was a String");
  }
}
