private static void testRotate180(int width,int height){
  BitMatrix input=getInput(width,height);
  Detector.rotate180(input);
  BitMatrix expected=getExpected(width,height);
  for (int y=0; y < height; y++) {
    for (int x=0; x < width; x++) {
      assertEquals("(" + x + ','+ y+ ')',expected.get(x,y),input.get(x,y));
    }
  }
}
