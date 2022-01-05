@Test public void testHighLevelEncode() throws Exception {
  testHighLevelEncodeString("A. b.","...X. ..... ...XX XXX.. ...XX XXXX. XX.X");
  testHighLevelEncodeString("Lorem ipsum.",".XX.X XXX.. X.... X..XX ..XX. .XXX. ....X .X.X. X...X X.X.. X.XX. .XXX. XXXX. XX.X");
  testHighLevelEncodeString("Lo. Test 123.",".XX.X XXX.. X.... ..... ...XX XXX.. X.X.X ..XX. X.X.. X.X.X  XXXX. ...X ..XX .X.. .X.X XX.X");
  testHighLevelEncodeString("Lo...x",".XX.X XXX.. X.... XXXX. XX.X XX.X XX.X XXX. XXX.. XX..X");
  testHighLevelEncodeString(". x://abc/.","..... ...XX XXX.. XX..X ..... X.X.X ..... X.X.. ..... X.X.. ...X. ...XX ..X.. ..... X.X.. XXXX. XX.X");
  testHighLevelEncodeString("ABCdEFG","...X. ...XX ..X.. XXXXX ....X .XX..X.. ..XX. ..XXX .X...");
  testHighLevelEncodeString("09  UAG    ^160MEUCIQC0sYS/HpKxnBELR1uB85R20OoqqwFGa0q2uEi" + "Ygh6utAIgLl1aBVM4EOTQtMQQYH9M2Z3Dp4qnA/fwWuQ+M8L3V8U=",823);
}
