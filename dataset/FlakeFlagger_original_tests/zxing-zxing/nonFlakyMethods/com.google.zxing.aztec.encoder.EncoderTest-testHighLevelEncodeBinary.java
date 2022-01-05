@Test public void testHighLevelEncodeBinary() throws Exception {
  testHighLevelEncodeString("N\0N",".XXXX XXXXX ....X ........ .XXXX");
  testHighLevelEncodeString("N\0n",".XXXX XXXXX ...X. ........ .XX.XXX.");
  testHighLevelEncodeString("N\0\u0080 A",".XXXX XXXXX ...X. ........ X....... ....X ...X.");
  testHighLevelEncodeString("\0a\u00FF\u0080 A","XXXXX ..X.. ........ .XX....X XXXXXXXX X....... ....X ...X.");
  testHighLevelEncodeString("1234\0","XXXX. ..XX .X.. .X.X .XX. XXX. XXXXX ....X ........");
  StringBuilder sb=new StringBuilder();
  for (int i=0; i <= 3000; i++) {
    sb.append((char)(128 + (i % 30)));
  }
  for (  int i : new int[]{1,2,3,10,29,30,31,32,33,60,61,62,63,64,2076,2077,2078,2079,2080,2100}) {
    int expectedLength=(8 * i) + ((i <= 31) ? 10 : (i <= 62) ? 20 : (i <= 2078) ? 21 : 31);
    testHighLevelEncodeString(sb.substring(0,i),expectedLength);
    if (i != 1 && i != 32 && i != 2079) {
      testHighLevelEncodeString('a' + sb.substring(0,i - 1),expectedLength);
      testHighLevelEncodeString(sb.substring(0,i - 1) + 'a',expectedLength);
    }
    testHighLevelEncodeString('a' + sb.substring(0,i) + 'b',expectedLength + 15);
  }
}
