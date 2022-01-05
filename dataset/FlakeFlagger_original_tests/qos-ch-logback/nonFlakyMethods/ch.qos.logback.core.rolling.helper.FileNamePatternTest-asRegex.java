@Test public void asRegex(){
{
    FileNamePattern fnp=new FileNamePattern("foo-%d{yyyy.MM.dd}-%i.txt",context);
    String regex=fnp.toRegex();
    assertEquals("foo-\\d{4}\\.\\d{2}\\.\\d{2}-\\d{1,2}.txt",regex);
  }
{
    FileNamePattern fnp=new FileNamePattern("foo-%d{yyyy.MM.dd'T'}-%i.txt",context);
    String regex=fnp.toRegex();
    assertEquals("foo-\\d{4}\\.\\d{2}\\.\\d{2}T-\\d{1,2}.txt",regex);
  }
}
