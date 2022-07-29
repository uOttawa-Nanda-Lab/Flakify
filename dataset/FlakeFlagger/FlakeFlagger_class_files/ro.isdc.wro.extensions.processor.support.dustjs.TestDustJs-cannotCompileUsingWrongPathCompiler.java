package ro.isdc.wro.extensions.processor.support.dustjs;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.util.WroUtil;


/**
 * @author Alex Objelean
 */
public class TestDustJs {
  private DustJs victim;

  @Before
  public void setUp() {
    victim = new DustJs();
  }

  @Test(expected=WroRuntimeException.class) public void cannotCompileUsingWrongPathCompiler() throws Exception{System.setProperty(DustJs.PARAM_COMPILER_PATH,"/invalid/path/to/dust.js");victim.compile(null,null);}
}
