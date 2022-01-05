@Test public void testCalculateBCHCode(){
  assertEquals(0xdc,MatrixUtil.calculateBCHCode(5,0x537));
  assertEquals(0x1c2,MatrixUtil.calculateBCHCode(0x13,0x537));
  assertEquals(0x214,MatrixUtil.calculateBCHCode(0x1b,0x537));
  assertEquals(0xc94,MatrixUtil.calculateBCHCode(7,0x1f25));
  assertEquals(0x5bc,MatrixUtil.calculateBCHCode(8,0x1f25));
  assertEquals(0xa99,MatrixUtil.calculateBCHCode(9,0x1f25));
  assertEquals(0x4d3,MatrixUtil.calculateBCHCode(10,0x1f25));
  assertEquals(0x9a6,MatrixUtil.calculateBCHCode(20,0x1f25));
  assertEquals(0xd75,MatrixUtil.calculateBCHCode(30,0x1f25));
  assertEquals(0xc69,MatrixUtil.calculateBCHCode(40,0x1f25));
}
