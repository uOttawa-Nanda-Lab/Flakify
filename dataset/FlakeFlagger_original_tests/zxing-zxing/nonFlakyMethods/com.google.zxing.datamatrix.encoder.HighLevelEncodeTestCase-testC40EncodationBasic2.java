@Test public void testC40EncodationBasic2(){
  String visualized=encodeHighLevel("AIMAIAB");
  assertEquals("230 91 11 90 255 254 67 129",visualized);
  visualized=encodeHighLevel("AIMAIAb");
  assertEquals("66 74 78 66 74 66 99 129",visualized);
  visualized=encodeHighLevel("AIMAIMAIMË");
  assertEquals("230 91 11 91 11 91 11 254 235 76",visualized);
  visualized=encodeHighLevel("AIMAIMAIMë");
  assertEquals("230 91 11 91 11 91 11 254 235 108",visualized);
}
