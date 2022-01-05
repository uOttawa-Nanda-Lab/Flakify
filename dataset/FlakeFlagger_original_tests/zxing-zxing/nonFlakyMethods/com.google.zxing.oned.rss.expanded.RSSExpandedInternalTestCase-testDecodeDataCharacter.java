@Test public void testDecodeDataCharacter() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/3.png";
  File file=new File(path);
  if (!file.exists()) {
    file=new File("core",path);
  }
  BufferedImage image=ImageIO.read(file);
  BinaryBitmap binaryMap=new BinaryBitmap(new GlobalHistogramBinarizer(new BufferedImageLuminanceSource(image)));
  BitArray row=binaryMap.getBlackRow(binaryMap.getHeight() / 2,null);
  int[] startEnd={145,243};
  int value=0;
  FinderPattern finderPatternA1=new FinderPattern(value,startEnd,startEnd[0],startEnd[1],image.getHeight() / 2);
  RSSExpandedReader rssExpandedReader=new RSSExpandedReader();
  DataCharacter dataCharacter=rssExpandedReader.decodeDataCharacter(row,finderPatternA1,true,false);
  assertEquals(19,dataCharacter.getValue());
  assertEquals(1007,dataCharacter.getChecksumPortion());
}
