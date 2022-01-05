@Test public void should_not_be_equal_to_IsNotEqual_with_different_expected(){
  assertFalse(factory.equals(shouldBeEqual("Yoda","Leia",new StandardRepresentation())));
}
