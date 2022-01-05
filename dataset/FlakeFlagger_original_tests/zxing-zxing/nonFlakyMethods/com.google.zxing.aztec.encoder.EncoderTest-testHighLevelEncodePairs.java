@Test public void testHighLevelEncodePairs() throws Exception {
  testHighLevelEncodeString("ABC. DEF\r\n","...X. ...XX ..X.. ..... ...XX ..X.X ..XX. ..XXX ..... ...X.");
  testHighLevelEncodeString("A. : , \r\n","...X. XXX.X XXXX. ...XX ..X.X  ..X.. ...X.");
  testHighLevelEncodeString("A. 1234","...X. XXXX. XX.X ...X ..XX .X.. .X.X .X X.");
  testHighLevelEncodeString("A\200. \200","...X. XXXXX ..X.. X....... ..X.XXX. ..X..... X.......");
}
