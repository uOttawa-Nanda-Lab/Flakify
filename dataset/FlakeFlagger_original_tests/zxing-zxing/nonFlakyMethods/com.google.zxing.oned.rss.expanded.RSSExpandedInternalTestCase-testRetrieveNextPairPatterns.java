@Test public void testRetrieveNextPairPatterns() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/3.png";
  File file=new File(path);
  if (!file.exists()) {
    file=new File("core",path);
  }
  BufferedImage image=ImageIO.read(file);
  BinaryBitmap binaryMap=new BinaryBitmap(new GlobalHistogramBinarizer(new BufferedImageLuminanceSource(image)));
  int rowNumber=binaryMap.getHeight() / 2;
  BitArray row=binaryMap.getBlackRow(rowNumber,null);
  List<ExpandedPair> previousPairs=new ArrayList<ExpandedPair>();
  RSSExpandedReader rssExpandedReader=new RSSExpandedReader();
  ExpandedPair pair1=rssExpandedReader.retrieveNextPair(row,previousPairs,rowNumber);
  previousPairs.add(pair1);
  FinderPattern finderPattern=pair1.getFinderPattern();
  assertNotNull(finderPattern);
  assertEquals(0,finderPattern.getValue());
  ExpandedPair pair2=rssExpandedReader.retrieveNextPair(row,previousPairs,rowNumber);
  previousPairs.add(pair2);
  finderPattern=pair2.getFinderPattern();
  assertNotNull(finderPattern);
  assertEquals(0,finderPattern.getValue());
}
