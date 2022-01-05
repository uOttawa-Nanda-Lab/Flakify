@Test public void testBug1664266(){
  String visualized=encodeHighLevel("CREX-TAN:h");
  assertEquals("240 13 33 88 181 64 78 124 59 105",visualized);
  visualized=encodeHighLevel("CREX-TAN:hh");
  assertEquals("240 13 33 88 181 64 78 124 59 105 105 129",visualized);
  visualized=encodeHighLevel("CREX-TAN:hhh");
  assertEquals("240 13 33 88 181 64 78 124 59 105 105 105",visualized);
}
