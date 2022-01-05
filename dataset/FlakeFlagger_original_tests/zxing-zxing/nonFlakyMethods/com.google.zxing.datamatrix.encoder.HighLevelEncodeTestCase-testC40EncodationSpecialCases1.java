@Test public void testC40EncodationSpecialCases1(){
  useTestSymbols();
  String visualized=encodeHighLevel("AIMAIMAIMAIMAIMAIM");
  assertEquals("230 91 11 91 11 91 11 91 11 91 11 91 11",visualized);
  visualized=encodeHighLevel("AIMAIMAIMAIMAIMAI");
  assertEquals("230 91 11 91 11 91 11 91 11 91 11 90 241",visualized);
  visualized=encodeHighLevel("AIMAIMAIMAIMAIMA");
  assertEquals("230 91 11 91 11 91 11 91 11 91 11 254 66",visualized);
  resetSymbols();
  visualized=encodeHighLevel("AIMAIMAIMAIMAIMAI");
  assertEquals("230 91 11 91 11 91 11 91 11 91 11 254 66 74 129 237",visualized);
  visualized=encodeHighLevel("AIMAIMAIMA");
  assertEquals("230 91 11 91 11 91 11 66",visualized);
}
