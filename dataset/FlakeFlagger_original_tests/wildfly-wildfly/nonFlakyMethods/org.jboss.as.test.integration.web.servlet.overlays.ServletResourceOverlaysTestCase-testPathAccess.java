/** 
 * Tests that a servlet (through the use of  {@link javax.servlet.ServletContext#getResourceAsStream(String)} (or similar APIs)cannot access paths outside of the deployment
 * @throws Exception
 */
@Test public void testPathAccess() throws Exception {
  final String aTxtPath="a.txt";
  final String aTxtAccess=performCall(url,"/check-path-access?path=a.txt&expected-accessible=true");
  assertEquals("Unexpected result from call to " + aTxtPath,PathAccessCheckServlet.ACCESS_CHECKS_CORRECTLY_VALIDATED,aTxtAccess);
  VirtualFile deploymentRoot=VFS.getChild("content/single.war");
  final StringBuilder accessRootPath=new StringBuilder("");
  while (!deploymentRoot.isRoot()) {
    accessRootPath.append("/..");
    deploymentRoot=deploymentRoot.getParent();
  }
  final File fileUnderTest=Paths.get(System.getProperty("java.io.tmpdir"),"noaccess.txt").toFile();
  fileUnderTest.createNewFile();
  if (fileUnderTest.exists()) {
    String canonicalPath=fileUnderTest.getCanonicalPath();
    canonicalPath=canonicalPath.substring(fileUnderTest.toPath().getRoot().toString().length());
    if (File.separator.equals("\\")) {
      canonicalPath=canonicalPath.replace("\\","%5c");
    }
    final String pathOutsideOfDeployment=accessRootPath.toString() + "/../../../../../../../" + canonicalPath;
    final String outsidePathAccessCheck=performCall(url,"/check-path-access?path=" + pathOutsideOfDeployment + "&expected-accessible=false");
    assertEquals("Unexpected result from call to " + pathOutsideOfDeployment,PathAccessCheckServlet.ACCESS_CHECKS_CORRECTLY_VALIDATED,outsidePathAccessCheck);
    fileUnderTest.delete();
  }
 else {
    fail("Cannot create the file under test: " + fileUnderTest.getCanonicalPath());
  }
}
