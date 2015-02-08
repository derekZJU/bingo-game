package dw35_wz23.server.game.view;

import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import map.MapPanel;
import map.ToggleAnnotation;
/**
 * class for showing the map of nasa
 * @author dw35, wz23
 *
 */
public class MyMapPanel extends MapPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2069337853745191524L;
	public IPanel2ViewAdapter gameView;
	
	/**
	 * constructor of the panel
	 * @param adp
	 */
	public MyMapPanel(IPanel2ViewAdapter adp){
		super();
		gameView = adp;
	}
	/**
	 * override the setupAnnotationToggling to send update when left click the panel
	 */
	public void setupAnnotationToggling(){
		getWWD().addSelectListener(new SelectListener() {
			public void selected(SelectEvent event) {
				if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
					if (event.hasObjects()) {
						Object obj = event.getTopObject();
						if (obj instanceof ToggleAnnotation) {
							ToggleAnnotation annotation = (ToggleAnnotation) obj;
							annotation.toggleText();
							String content = annotation.getText();
							gameView.sendUpdate(content);
						}
					}
				}
			}
		});
	}
}
