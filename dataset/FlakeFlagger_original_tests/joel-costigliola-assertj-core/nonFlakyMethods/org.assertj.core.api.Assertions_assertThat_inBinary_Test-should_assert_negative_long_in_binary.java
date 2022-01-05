@Test public void should_assert_negative_long_in_binary(){
  thrown.expectMessage("expected:<0b[00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000011]> " + "but was:<0b[11111111_11111111_11111111_11111111_11111111_11111111_11111111_11111110]>");
  assertThat((long)-2).inBinary().isEqualTo((long)3);
}
