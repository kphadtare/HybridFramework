import utils.propertiesHelper;

public final class FrameworkConstants {
	
	
	private FrameworkConstants() {
		
	}
	
	public static final String BROWSER= propertiesHelper.getValue("BROWSER");
	public static final String PROJECT_PATH = propertiesHelper.getProjectDir();

}
