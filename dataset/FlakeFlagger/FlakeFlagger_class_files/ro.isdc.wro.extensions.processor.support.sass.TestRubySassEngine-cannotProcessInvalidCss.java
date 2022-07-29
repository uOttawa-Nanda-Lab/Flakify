package ro.isdc.wro.extensions.processor.support.sass;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;


/**
 * @author Dmitry Erman
 */
public class TestRubySassEngine {
  private RubySassEngine engine;


  @Before
  public void setUp() {
    engine = new RubySassEngine();
  }


  @Test(expected=WroRuntimeException.class) public void cannotProcessInvalidCss(){Assert.assertEquals(StringUtils.EMPTY,engine.process("invalidCss"));}
}
