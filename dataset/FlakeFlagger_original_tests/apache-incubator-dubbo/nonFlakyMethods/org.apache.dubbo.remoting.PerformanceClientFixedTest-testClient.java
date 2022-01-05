@Test public void testClient() throws Exception {
  if (PerformanceUtils.getProperty("server",null) == null) {
    logger.warn("Please set -Dserver=127.0.0.1:9911");
    return;
  }
  final String server=System.getProperty("server","127.0.0.1:9911");
  final String transporter=PerformanceUtils.getProperty(Constants.TRANSPORTER_KEY,Constants.DEFAULT_TRANSPORTER);
  final String serialization=PerformanceUtils.getProperty(Constants.SERIALIZATION_KEY,Constants.DEFAULT_REMOTING_SERIALIZATION);
  final int timeout=PerformanceUtils.getIntProperty(Constants.TIMEOUT_KEY,Constants.DEFAULT_TIMEOUT);
  final int connectionCount=PerformanceUtils.getIntProperty(Constants.CONNECTIONS_KEY,1);
  final String url="exchange://" + server + "?transporter="+ transporter+ "&serialization="+ serialization+ "&timeout="+ timeout;
  Random rd=new Random(connectionCount);
  ArrayList<ExchangeClient> arrays=new ArrayList<ExchangeClient>();
  String oneKBlock=null;
  String messageBlock=null;
  int s=0;
  int f=0;
  System.out.println("initialize arrays " + url);
  while (s < connectionCount) {
    ExchangeClient client=null;
    try {
      System.out.println("open connection " + s + " "+ url+ arrays.size());
      client=Exchangers.connect(url);
      System.out.println("run after open");
      if (client.isConnected()) {
        arrays.add(client);
        s++;
        System.out.println("open client success " + s);
      }
 else {
        System.out.println("open client failed, try again.");
      }
    }
 catch (    Throwable t) {
      t.printStackTrace();
    }
 finally {
      if (client != null && client.isConnected() == false) {
        f++;
        System.out.println("open client failed, try again " + f);
        client.close();
      }
    }
  }
  StringBuilder sb1=new StringBuilder();
  Random rd2=new Random();
  char[] numbersAndLetters=("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
  int size1=numbersAndLetters.length;
  for (int j=0; j < 1024; j++) {
    sb1.append(numbersAndLetters[rd2.nextInt(size1)]);
  }
  oneKBlock=sb1.toString();
  for (int j=0; j < Integer.MAX_VALUE; j++) {
    try {
      String size="10";
      int request_size=10;
      try {
        request_size=Integer.parseInt(size);
      }
 catch (      Throwable t) {
        request_size=10;
      }
      if (messageBlock == null) {
        StringBuilder sb=new StringBuilder();
        for (int i=0; i < request_size; i++) {
          sb.append(oneKBlock);
        }
        messageBlock=sb.toString();
        System.out.println("set messageBlock to " + messageBlock);
      }
      int index=rd.nextInt(connectionCount);
      ExchangeClient client=arrays.get(index);
      String output=(String)client.request(messageBlock).get();
      if (output.lastIndexOf(messageBlock) < 0) {
        System.out.println("send messageBlock;get " + output);
        throw new Throwable("return results invalid");
      }
 else {
        if (j % 100 == 0)         System.out.println("OK: " + j);
      }
    }
 catch (    Throwable t) {
      t.printStackTrace();
    }
  }
}
