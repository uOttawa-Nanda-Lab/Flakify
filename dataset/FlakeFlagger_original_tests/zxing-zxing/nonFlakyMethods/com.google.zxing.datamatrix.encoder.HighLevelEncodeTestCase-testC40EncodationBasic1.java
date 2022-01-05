@Test public void testC40EncodationBasic1(){
  String visualized=encodeHighLevel("AIMAIMAIM");
  assertEquals("230 91 11 91 11 91 11 254",visualized);
}
