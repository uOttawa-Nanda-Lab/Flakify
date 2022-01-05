@Test public void verifySignedJar() throws Exception {
  String classpath=System.getProperty("java.class.path");
  String[] entries=classpath.split(System.getProperty("path.separator"));
  String signedJarFile=null;
  for (  String entry : entries) {
    if (entry.contains("bcprov")) {
      signedJarFile=entry;
    }
  }
  assertNotNull(signedJarFile);
  java.util.jar.JarFile jarFile=new JarFile(new File(signedJarFile));
  jarFile.getManifest();
  Enumeration<JarEntry> jarEntries=jarFile.entries();
  while (jarEntries.hasMoreElements()) {
    JarEntry jarEntry=jarEntries.nextElement();
    InputStream inputStream=jarFile.getInputStream(jarEntry);
    inputStream.skip(Long.MAX_VALUE);
    inputStream.close();
    if (!jarEntry.getName().startsWith("META-INF") && !jarEntry.isDirectory() && !jarEntry.getName().endsWith("TigerDigest.class")) {
      assertNotNull("Missing cert " + jarEntry.getName(),jarEntry.getCertificates());
    }
  }
  jarFile.close();
}
