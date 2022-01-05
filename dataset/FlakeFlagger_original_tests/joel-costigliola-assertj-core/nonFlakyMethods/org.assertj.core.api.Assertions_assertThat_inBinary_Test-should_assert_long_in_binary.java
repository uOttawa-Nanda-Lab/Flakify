@Test public void should_assert_long_in_binary(){
  thrown.expectMessage("expected:<...000_00000000_0000001[1]> but was:<...000_00000000_0000001[0]>");
  assertThat((long)2).inBinary().isEqualTo((long)3);
}
