@Test public void testMacroCharacters(){
  String visualized=encodeHighLevel("[)>\u001E05\u001D5555\u001C6666\u001E\u0004");
  assertEquals("236 185 185 29 196 196 129 56",visualized);
}
