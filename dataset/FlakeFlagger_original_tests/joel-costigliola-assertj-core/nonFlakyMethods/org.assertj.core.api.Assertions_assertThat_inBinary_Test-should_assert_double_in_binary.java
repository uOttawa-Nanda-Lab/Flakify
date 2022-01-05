@Test public void should_assert_double_in_binary(){
  thrown.expectMessage("expected:<0b01000000_0000[1000_00000000_00000000_00000000_00000000_00000000_00000000]> " + "but was:<0b01000000_0000[0000_11001100_11001100_11001100_11001100_11001100_11001101]>");
  assertThat(2.1d).inBinary().isEqualTo(3d);
}
