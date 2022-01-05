@Test public void should_assert_bytes_contains_in_hexadecimal(){
  thrown.expectMessage("Expecting:\n" + " <[0x02, 0x03]>\n" + "to contain:\n"+ " <[0x01]>\n"+ "but could not find:\n"+ " <[0x01]>");
  assertThat(new byte[]{2,3}).inHexadecimal().contains(new byte[]{1});
}
