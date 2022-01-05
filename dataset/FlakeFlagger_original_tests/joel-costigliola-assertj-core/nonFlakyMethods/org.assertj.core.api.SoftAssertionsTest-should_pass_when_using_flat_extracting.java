@Test public void should_pass_when_using_flat_extracting(){
  List<CartoonCharacter> characters=asList(homer,fred);
  softly.assertThat(characters).flatExtracting(children()).as("using flatExtracting on Iterable").hasSize(4);
  CartoonCharacter[] charactersAsArray=characters.toArray(new CartoonCharacter[characters.size()]);
  softly.assertThat(charactersAsArray).flatExtracting(children()).as("using flatExtracting on array").hasSize(4);
  softly.assertAll();
}
