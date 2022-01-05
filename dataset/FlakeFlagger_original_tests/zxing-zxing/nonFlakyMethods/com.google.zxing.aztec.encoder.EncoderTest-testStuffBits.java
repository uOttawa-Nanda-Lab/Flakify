private static void testStuffBits(int wordSize,String bits,String expected){
  BitArray in=toBitArray(bits);
  BitArray stuffed=Encoder.stuffBits(in,wordSize);
  assertEquals("stuffBits() failed for input string: " + bits,expected.replace(" ",""),stuffed.toString().replace(" ",""));
}
