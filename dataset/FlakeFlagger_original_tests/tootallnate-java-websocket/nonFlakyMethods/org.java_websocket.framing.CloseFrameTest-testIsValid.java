@Test public void testIsValid(){
  CloseFrame frame=new CloseFrame();
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setFin(false);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setFin(true);
  frame.setRSV1(true);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setRSV1(false);
  frame.setRSV2(true);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setRSV2(false);
  frame.setRSV3(true);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setRSV3(false);
  frame.setCode(CloseFrame.NORMAL);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.GOING_AWAY);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.PROTOCOL_ERROR);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.REFUSE);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.NOCODE);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.ABNORMAL_CLOSE);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.POLICY_VALIDATION);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.TOOBIG);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.EXTENSION);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.UNEXPECTED_CONDITION);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.SERVICE_RESTART);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.TRY_AGAIN_LATER);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.BAD_GATEWAY);
  try {
    frame.isValid();
  }
 catch (  InvalidDataException e) {
    fail("InvalidDataException should not be thrown");
  }
  frame.setCode(CloseFrame.TLS_ERROR);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.NEVER_CONNECTED);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.BUGGYCLOSE);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.FLASHPOLICY);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.NOCODE);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.NO_UTF8);
  frame.setReason(null);
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
  frame.setCode(CloseFrame.NOCODE);
  frame.setReason("Close");
  try {
    frame.isValid();
    fail("InvalidDataException should be thrown");
  }
 catch (  InvalidDataException e) {
  }
}
