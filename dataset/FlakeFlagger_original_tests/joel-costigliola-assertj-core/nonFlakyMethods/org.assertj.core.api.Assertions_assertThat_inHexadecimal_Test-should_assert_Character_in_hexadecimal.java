@Test public void should_assert_Character_in_hexadecimal(){
  thrown.expectMessage("expected:<'0x006[2]'> but was:<'0x006[1]'>");
  assertThat('a').inHexadecimal().isEqualTo('b');
}
