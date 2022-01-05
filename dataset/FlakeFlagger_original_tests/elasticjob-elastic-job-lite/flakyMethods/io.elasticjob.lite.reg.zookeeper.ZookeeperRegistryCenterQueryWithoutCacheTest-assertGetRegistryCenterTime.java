@Test public void assertGetRegistryCenterTime(){
  long regCenterTime=zkRegCenter.getRegistryCenterTime("/_systemTime/current");
  assertTrue(regCenterTime <= System.currentTimeMillis());
  long updatedRegCenterTime=zkRegCenter.getRegistryCenterTime("/_systemTime/current");
  System.out.println(regCenterTime + "," + updatedRegCenterTime);
  assertTrue(regCenterTime < updatedRegCenterTime);
}
