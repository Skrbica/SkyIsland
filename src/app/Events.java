package app;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import models.Model;
import models.Platform;
import models.TransformationParameters;

public class Events {
	private Scene scene;
	private int selectedObject = -1;
	private List<Model> models;
	private Platform platform;
	private List<ImageView> elementImgs;
	private ColorAdjust imgSelectedEffect;
	private SubScene hud;
	
	public Events(Scene scene2, List<Model> models,  Platform platform, List<ImageView> elementImgs, SubScene hud) {
		this.scene = scene2;
		this.platform = platform;
		this.models = models;
		this.elementImgs = elementImgs;
		this.hud = hud;
		
		imgSelectedEffect = new ColorAdjust();
		imgSelectedEffect.setHue(0);
		imgSelectedEffect.setSaturation(0.1);
		
		listen();
	}
	
	public void listen() {
		scene.setOnMouseClicked(e -> {
			
			Scoring.addCoin();
			Scoring.update(hud);
			
			Node node = e.getPickResult().getIntersectedNode();
			
			//field selected
			if (node instanceof Box) {
				if(platform.fieldSelected(node)) {
					if(selectedObject != 0 && !platform.occupied(node)) {
						elementImgs.get(elementImgs.size() - 1).setOpacity(1);
					}
				} else {
					elementImgs.get(elementImgs.size() - 1).setOpacity(0.5);
				}
				
			//selected image from menu
			} else if (node instanceof ImageView) {
				
				ImageView imgView = (ImageView) node;
				
				if(imgView == elementImgs.get(elementImgs.size() - 1)) {
					if(imgView.getOpacity() == 1) {
						TransformationParameters tp = platform.getSelectedFieldTransforms();
						models.get(selectedObject).addNode(tp.getX(), 0, tp.getZ(), tp.getRot());
						Scoring.increase(selectedObject);
						selectedObject = 0;
						platform.occupy();
						imgView.setOpacity(0.5);
						Scoring.update(hud);
					}
				}
				
				for(int i = 0; i < elementImgs.size() - 1; i++) {
					if(elementImgs.get(i).getEffect() == imgSelectedEffect) {
						elementImgs.get(i).setEffect(null);;
					}
					if(elementImgs.get(i) == imgView) { 
					    elementImgs.get(i).setEffect(imgSelectedEffect);
						selectedObject = i;
						if(platform.isSelected()) {
							elementImgs.get(elementImgs.size() - 1).setOpacity(1); 
						}
					}
				}
			}
		});
		
		scene.setOnKeyPressed(e -> {
			//
		});
	}
}
