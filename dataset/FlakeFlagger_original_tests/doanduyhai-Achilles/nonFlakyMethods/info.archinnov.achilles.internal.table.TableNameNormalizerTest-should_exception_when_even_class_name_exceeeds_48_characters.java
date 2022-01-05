@Test public void should_exception_when_even_class_name_exceeeds_48_characters() throws Exception {
  String canonicalName="ItIsAVeryLoooooooooooooooooooooooooooooooooooooongClassNameExceeding48Characters";
  exception.expect(AchillesInvalidTableException.class);
  exception.expectMessage("The table name 'ItIsAVeryLoooooooooooooooooooooooooooooooooooooongClassNameExceeding48Characters' is invalid. It should be respect the pattern [a-zA-Z0-9_] and be at most 48 characters long");
  TableNameNormalizer.normalizerAndValidateColumnFamilyName(canonicalName);
}
