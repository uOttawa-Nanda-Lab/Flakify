@Test public void should_assert_String_in_binary(){
  thrown.expectMessage("expected:<...0001', '0b00000000_0[01101]10']\"> but was:<...0001', '0b00000000_0[11000]10']\">");
  assertThat("ab").inBinary().isEqualTo("a6");
}
