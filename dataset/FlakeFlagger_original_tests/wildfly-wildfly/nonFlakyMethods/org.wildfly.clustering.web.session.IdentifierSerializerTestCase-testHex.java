@Test public void testHex() throws IOException {
  test(IdentifierSerializer.HEX,() -> {
    byte[] buffer=new byte[16];
    int sessionIdLength=16;
    StringBuilder builder=new StringBuilder(2 * sessionIdLength);
    int resultLenBytes=0;
    Random random=new Random(System.currentTimeMillis());
    while (resultLenBytes < sessionIdLength) {
      random.nextBytes(buffer);
      for (int j=0; j < buffer.length && resultLenBytes < sessionIdLength; j++) {
        byte b1=(byte)((buffer[j] & 0xf0) >> 4);
        byte b2=(byte)(buffer[j] & 0x0f);
        if (b1 < 10)         builder.append((char)('0' + b1));
 else         builder.append((char)('A' + (b1 - 10)));
        if (b2 < 10)         builder.append((char)('0' + b2));
 else         builder.append((char)('A' + (b2 - 10)));
        resultLenBytes++;
      }
    }
    return builder.toString();
  }
);
}
