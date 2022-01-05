@Override @Test public void testBlackBox() throws IOException {
  assertFalse(testResults.isEmpty());
  File[] imageFiles=getImageFiles();
  int[] falsePositives=new int[testResults.size()];
  for (  File testImage : imageFiles) {
    log.info(String.format("Starting %s",testImage.getAbsolutePath()));
    BufferedImage image=ImageIO.read(testImage);
    if (image == null) {
      throw new IOException("Could not read image: " + testImage);
    }
    for (int x=0; x < testResults.size(); x++) {
      TestResult testResult=testResults.get(x);
      if (!checkForFalsePositives(image,testResult.getRotation())) {
        falsePositives[x]++;
      }
    }
  }
  int totalFalsePositives=0;
  int totalAllowed=0;
  for (int x=0; x < testResults.size(); x++) {
    TestResult testResult=testResults.get(x);
    totalFalsePositives+=falsePositives[x];
    totalAllowed+=testResult.getFalsePositivesAllowed();
  }
  if (totalFalsePositives < totalAllowed) {
    log.warning(String.format("+++ Test too lax by %d images",totalAllowed - totalFalsePositives));
  }
 else   if (totalFalsePositives > totalAllowed) {
    log.warning(String.format("--- Test failed by %d images",totalFalsePositives - totalAllowed));
  }
  for (int x=0; x < testResults.size(); x++) {
    TestResult testResult=testResults.get(x);
    log.info(String.format("Rotation %d degrees: %d of %d images were false positives (%d allowed)",(int)testResult.getRotation(),falsePositives[x],imageFiles.length,testResult.getFalsePositivesAllowed()));
    assertTrue("Rotation " + testResult.getRotation() + " degrees: Too many false positives found",falsePositives[x] <= testResult.getFalsePositivesAllowed());
  }
}
