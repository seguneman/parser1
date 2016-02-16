package constants;

public class ATConstants {

	public static int FRAME_WIDTH = 1200;
	public static int FRAME_HEIGHT = 800;
	
	public static int BUTTON_WIDTH = 90;
	public static int BUTTON_HEIGHT = 50;
	public static int BUTTON_POSX = 800;
	public static int BUTTON_POSY = 150;
	public static int BUTTON_X_SPACE = 100;
	public static int BUTTON_Y_SPACE = 80;
	
	public static int LABEL_WIDTH = 80;
	public static int LABEL_HEIGHT = 40;
	public static int LABEL_POSX = 800;
	public static int LABEL_POSY = 120;
	public static int LABEL_SPACE = 80;
	
	public static String FILEPATH = "./workspace/SEM.xml";
	
	// Button Group
	public static enum ELabel {
		wind("�ٶ�"),
		light("��"),
		vibration("����"),
		smell("���");
		
		private String name;
		private ELabel(String name) {
			this.name = name;
		}
		
		public String getName() { return this.name;}
	}
	public static enum EWindButtons {
		wind0("����"),
		wind1("1"),
		wind2("2"),
		wind3("3");
		
		private String name;
		private EWindButtons(String name) {
			this.name = name;
		}
		
		public String getName() { return this.name; }
	}
	
	public static enum ELightButtons {
		noLight("����"),
		red("����"),
		blue("�Ķ�"),
		green("�ʷ�");
		
		private String name;
		private ELightButtons(String name) {
			this.name = name;
		}
		
		public String getName() { return this.name; }
	}
	
	public static enum EVibrationButtons {
		vib0("����"),
		vib1("1"),
		vib2("2"),
		vib3("3");
		
		private String name;
		private EVibrationButtons(String name) {
			this.name = name;
		}
		
		public String getName() { return this.name; }
	}
	
	public static enum EScentButtons {
		noScent("����"),
		popcorn("����"),
		gunpower("ȭ��"),
		flower("��");
		
		private String name;
		private EScentButtons(String name) {
			this.name = name;
		}
		
		public String getName() { return this.name; }
	}
}
