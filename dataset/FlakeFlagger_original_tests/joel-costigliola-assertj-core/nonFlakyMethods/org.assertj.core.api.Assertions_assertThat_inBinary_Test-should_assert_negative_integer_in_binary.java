@Test public void should_assert_negative_integer_in_binary(){
  thrown.expectMessage("expected:<0b[11111111_11111111_11111111_1111110]1> but was:<0b[00000000_00000000_00000000_0000001]1>");
  assertThat(3).inBinary().isEqualTo(-3);
}
