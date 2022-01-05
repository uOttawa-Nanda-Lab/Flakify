@Test public void testIsFrameValid() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  TextFrame textFrame=new TextFrame();
  try {
    defaultExtension.isFrameValid(textFrame);
  }
 catch (  Exception e) {
    fail("This frame is valid");
  }
  textFrame.setRSV1(true);
  try {
    defaultExtension.isFrameValid(textFrame);
    fail("This frame is not valid");
  }
 catch (  Exception e) {
  }
  textFrame.setRSV1(false);
  textFrame.setRSV2(true);
  try {
    defaultExtension.isFrameValid(textFrame);
    fail("This frame is not valid");
  }
 catch (  Exception e) {
  }
  textFrame.setRSV2(false);
  textFrame.setRSV3(true);
  try {
    defaultExtension.isFrameValid(textFrame);
    fail("This frame is not valid");
  }
 catch (  Exception e) {
  }
}
