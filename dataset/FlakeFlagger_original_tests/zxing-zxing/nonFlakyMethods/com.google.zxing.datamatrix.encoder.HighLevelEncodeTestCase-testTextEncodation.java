@Test public void testTextEncodation(){
  String visualized=encodeHighLevel("aimaimaim");
  assertEquals("239 91 11 91 11 91 11 254",visualized);
  visualized=encodeHighLevel("aimaimaim'");
  assertEquals("239 91 11 91 11 91 11 254 40 129",visualized);
  visualized=encodeHighLevel("aimaimaIm");
  assertEquals("239 91 11 91 11 87 218 110",visualized);
  visualized=encodeHighLevel("aimaimaimB");
  assertEquals("239 91 11 91 11 91 11 254 67 129",visualized);
  visualized=encodeHighLevel("aimaimaim{txt}\u0004");
  assertEquals("239 91 11 91 11 91 11 16 218 236 107 181 69 254 129 237",visualized);
}
