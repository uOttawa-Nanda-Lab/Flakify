import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ClassNameTest {
@Test public void testThriftUtils(){
  Assert.assertEquals($__ClassNameTestDubboStub.echo_args.class.getName(),ThriftUtils.generateMethodArgsClassName(ClassNameTestDubbo.class.getName(),"echo"));
  Assert.assertEquals($__ClassNameTestDubboStub.echo_result.class.getName(),ThriftUtils.generateMethodResultClassName(ClassNameTestDubbo.class.getName(),"echo"));
  Assert.assertEquals(ClassNameTestThrift.echo_args.class.getName(),ThriftUtils.generateMethodArgsClassNameThrift(ClassNameTestThrift.Iface.class.getName(),"echo"));
  Assert.assertEquals(ClassNameTestThrift.echo_result.class.getName(),ThriftUtils.generateMethodResultClassNameThrift(ClassNameTestThrift.Iface.class.getName(),"echo"));
}

}