package processing.app;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.junit.Before;
import processing.app.helpers.ArduinoFrameFixture;

import javax.swing.*;

public abstract class AbstractGUITest {

  protected ArduinoFrameFixture window;

  @Before
  public void startUpTheIDE() throws Exception {
    FailOnThreadViolationRepaintManager.install();

    Base.initPlatform();
    Preferences.init(null);
    JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    Theme.init();
    Base.platform.setLookAndFeel();
    Base.untitledFolder = Base.createTempFolder("untitled");
    Base.untitledFolder.deleteOnExit();

    window = GuiActionRunner.execute(new GuiQuery<ArduinoFrameFixture>() {
      @Override
      protected ArduinoFrameFixture executeInEDT() throws Throwable {
        return new ArduinoFrameFixture(new Base(new String[0]).editors.get(0));
      }
    });
  }

}
