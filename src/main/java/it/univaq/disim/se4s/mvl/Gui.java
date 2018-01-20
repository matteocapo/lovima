/*package it.univaq.disim.se4as.mvl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import java.util.ArrayList;
import java.util.List;

public class Gui {

static List<String> old_active_box_list = new ArrayList<String>();

  public static void main(String[] args) {
	  
    final Display display = new Display();
    final Shell shell = new Shell(display);
    FillLayout fillLayout = new FillLayout();
    shell.setLayout(fillLayout);
    //shell.setSize(1500,900);
    shell.setMaximized(true);
    
    final Composite composite1 = new Composite(shell, SWT.BORDER);
	composite1.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
	RowLayout rowLayout1 = new RowLayout();
	rowLayout1.center = true;
	rowLayout1.spacing = 30;
	rowLayout1.marginBottom =30;
	composite1.setLayout(rowLayout1);
    setComposite1Title(composite1);
    
    final Composite composite2 = new Composite(shell,SWT.BORDER);
    GridLayout gridLayout2 = new GridLayout();
    gridLayout2.numColumns = 4;
    composite2.setLayout(gridLayout2);
    composite2.setBackground(display.getSystemColor(SWT.COLOR_RED));
    setComposite2Template(composite2);
    setIds(composite2);
    //composite2.pack();
    
    shell.open();
    new Thread() {
      public void run() {
          while(true) {
    	  try {
            Thread.sleep(2000);
          } catch (Throwable th) {
          }
          if (display.isDisposed())
            return;
          display.asyncExec(new Runnable() {
            public void run() {
            	if (shell.isDisposed())
                    return;
            	if (composite1.isDisposed())
            		return;
            	if (composite2.isDisposed())
            		return;
            	updateBoxList(composite1,composite2);
            }
          });
          }
      }
    }.start();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
  
  public static void updateBoxList(Composite composite1, Composite composite2) {
	  final Composite comp2 = composite2;
	  //List<String> new_active_box_list = getOnlineBoxes();
	  List<String> new_active_box_list = getActiveBoxes();
	  //List<String> new_active_box_list = new ArrayList<String>();
	  //new_active_box_list.add("1");
	  //new_active_box_list.add("2");
	  //new_active_box_list.add("3");
	  //new_active_box_list.add("4");
	  List<String> deactived_box_list = getDeactivedBoxes();
	  //List<String> deactived_box_list = new ArrayList<String>();
	  //deactived_box_list.add("4");
	  
	  for(Integer i=0; i<new_active_box_list.size(); i++) {
		  
		final Composite block = new Composite(composite1,SWT.BORDER);
		GridLayout gridLayout = new GridLayout();
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginWidth = 10;
		block.setLayout(gridLayout);
			  
		//contenuto del blocco: ID della teca, tipo di animali contenuti e bottone per vedere i dettagli
		final Label label1 = new Label(block, SWT.NONE);
		final int id = Integer.parseInt(new_active_box_list.get(i));
		label1.setText("ID: " + id);
		final Label label2 = new Label(block, SWT.NONE);
		label2.setText("Animal Type: ");
		final Button button = new Button(block,SWT.PUSH);
		button.setText("See Box Details");
		//premendo il bottone si apre nel secondo contenitore principale la lista dei dettagli della teca selezionata
	  	button.addListener(SWT.Selection, new Listener() { 
	  			 public void handleEvent(Event e) {
	  				 showDetails(id, comp2);
	  			 }
	  	});
			  
		//canvas.pack();
		//canvas.setSize(683,1000);
		composite1.layout(true);
	  }
	  
	  for(Integer i=0; i<deactived_box_list.size(); i++) {
		  Control[] children = composite1.getChildren();
		  //System.out.println(children);
		  for(Control e : children) {
			  if(e instanceof Composite) {
				//System.out.println(e);
				Boolean flag = false;
				Composite c = (Composite) e;
				Control[] children2 = c.getChildren();
				for(Control f : children2) {
					if(f instanceof Label) {
						Label l = (Label) f;
						String text = l.getText();
						String id_to_delete = text.substring(4);
						if(id_to_delete.equals(deactived_box_list.get(i))){
							flag = true;
						}
					}
				}
				if(flag) {
					e.dispose();
				}
			  }
		  }
	  }
  }

  public static void showDetails(int id, Composite composite2) {
	  Control[] children = composite2.getChildren();
	  for(Control e : children) {
		  if(e instanceof Text) {
			  Integer text_box_id = ((Integer)e.getData()).intValue();
			  switch(text_box_id) {
			  	case 2:
			  	((Text) e).setText(String.valueOf(id));
			  	break;
			  	case 4: 
			  	Integer number = askAnimalNumber(String.valueOf(id));
			  	((Text) e).setText(String.valueOf(number));
			  	break;
			  	case 6: 
			  	Float temp = readTemperature(String.valueOf(id));
				((Text) e).setText(String.valueOf(temp));
				break;
			  	case 8: 
			  	Float humidity = readHumidity(String.valueOf(id));
				((Text) e).setText(String.valueOf(humidity));
			  	break;
			  	case 10:
			  	Integer light = readLight(String.valueOf(id));
				((Text) e).setText(String.valueOf(light));
			  	break;
			  	case 12:
			  	String windler_state = readWindler(String.valueOf(id));
				((Text) e).setText(windler_state);
			  	break;
			  	case 16:
			  	String display_state = readDisplay(String.valueOf(id));
				((Text) e).setText(display_state);
			  	break;
			  	case 20:
			  	String alarm_state = readAlarm(String.valueOf(id));
				((Text) e).setText(alarm_state);
			  	break;
			  	case 24:
			  	Float food = askAnimalFood(String.valueOf(id));
				((Text) e).setText(String.valueOf(food));
			  	break;
			  	case 28:
			  	Float water = askAnimalWater(String.valueOf(id));
				((Text) e).setText(String.valueOf(water));
				break;
			  }
		  }
	  }
  }
  
  public static void setComposite1Title(Composite composite1) {
	  
	  final Composite label_composite = new Composite(composite1, SWT.NONE);
		RowLayout labelCompLayout = new RowLayout();
		labelCompLayout.marginWidth = 300;
		label_composite.setLayout(labelCompLayout);
		label_composite.setBackground(composite1.getDisplay().getSystemColor(SWT.COLOR_GREEN));
		
		final Label composite_title = new Label(label_composite, SWT.NONE);
		composite_title.setText("ACTIVE BOXES");
		FontData fontData = composite_title.getFont().getFontData()[0];
		Font font = new Font(composite1.getDisplay(), new FontData(fontData.getName(), fontData.getHeight(), SWT.BOLD));
		composite_title.setFont(font);
	  
  }
  
  public static void setComposite2Template(Composite composite2) {
	  
	  //setta il titolo del composite
	  final Label composite_title = new Label(composite2, SWT.NONE);
	  composite_title.setText("BOX DETAILS");
	  FontData fontData = composite_title.getFont().getFontData()[0];
	  Font font = new Font(composite2.getDisplay(), new FontData(fontData.getName(), fontData.getHeight(), SWT.BOLD));
	  composite_title.setFont(font);
	  GridData gridData = new GridData(SWT.CENTER, SWT.FILL, true, false);
	  gridData.horizontalSpan = 4;
      composite_title.setLayoutData(gridData);
	  
      // setta il campo id
      final Label id = new Label(composite2,SWT.NONE);
      id.setText("BOX ID");
      id.setFont(font);
	  final Text id_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      GridData gridData2 = new GridData();
      gridData2.horizontalSpan = 3;
      id_text.setLayoutData(gridData2); 
      
      // setta il campo relativo al n° di animali
      final Label animal = new Label(composite2,SWT.NONE);
      animal.setText("ANIMAL NUMBER");
      animal.setFont(font);
	  final Text animal_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      animal_text.setLayoutData(gridData2);
      
      // setta il campo temperatura
      final Label temperature = new Label(composite2,SWT.NONE);
      temperature.setText("TEMPERATURE (°C)");
      temperature.setFont(font);
	  final Text temperature_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      temperature_text.setLayoutData(gridData2);
      
      // setta il campo umidità
      final Label humidity = new Label(composite2,SWT.NONE);
      humidity.setText("HUMIDITY");
      humidity.setFont(font);
	  final Text humidity_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      humidity_text.setLayoutData(gridData2);
      
      // setta il campo luce
      final Label light = new Label(composite2,SWT.NONE);
      light.setText("LIGHT");
      light.setFont(font);
	  final Text light_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      light_text.setLayoutData(gridData2);
      
      // setta il campo per le info sulla ventola
      final Label windler = new Label(composite2,SWT.NONE);
      windler.setText("WINDLER STATE");
      windler.setFont(font);
      final Text windler_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      windler_text.setText("");
      final Button buttonOn = new Button(composite2,SWT.PUSH);
      buttonOn.setText("SWITCH ON");
      buttonOn.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (windler_text.getText().equals("OFF")) {
		        	String com = setWindler(String.valueOf(id), "ON");
		        	windler_text.setText(com);
				}
		    }
	  });
      final Button buttonOff = new Button(composite2,SWT.PUSH);
      buttonOff.setText("SWITCH OFF");
      buttonOff.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (windler_text.getText().equals("ON")) {
		        	String com = setWindler(String.valueOf(id),"OFF");
		        	windler_text.setText(com);
				}
		    }
	  });
      
      // setta il campo per le info sul display
      final Label display = new Label(composite2,SWT.NONE);
      display.setText("DISPLAY STATE");
      display.setFont(font);
      final Text display_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      display_text.setText("");
      final Button displayOn = new Button(composite2,SWT.PUSH);
      displayOn.setText("SWITCH ON");
      displayOn.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (display_text.getText().equals("OFF")) {
		        	String com = setDisplay(String.valueOf(id), "ON");
		        	display_text.setText(com);
				}
		    }
	  });
      final Button displayOff = new Button(composite2,SWT.PUSH);
      displayOff.setText("SWITCH OFF");
      displayOff.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (display_text.getText().equals("ON")) {
		        	String com = setDisplay(String.valueOf(id), "OFF");
		        	display_text.setText(com);
				}
		    }
	  });
      
      // setta il campo per le info sull'allarme
      final Label alarm = new Label(composite2,SWT.NONE);
      alarm.setText("ALARM STATE");
      alarm.setFont(font);
      final Text alarm_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      alarm_text.setText("");
      final Button alarmOn = new Button(composite2,SWT.PUSH);
      alarmOn.setText("SWITCH ON");
      alarmOn.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (alarm_text.getText().equals("OFF")) {
		        	String com = setAlarm(String.valueOf(id), "ON");
		        	alarm_text.setText(com);
				}
		    }
	  });
      final Button alarmOff = new Button(composite2,SWT.PUSH);
      alarmOff.setText("SWITCH OFF");
      alarmOff.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (alarm_text.getText().equals("ON")) {
		        	String com = setAlarm(String.valueOf(id), "OFF");
		        	alarm_text.setText(com);
				}
		    }
	  });
      
      // setta il campo per le info sulla quantità di cibo corrente
      final Label food = new Label(composite2,SWT.NONE);
      food.setText("FOOD QUANTITY (g)");
      food.setFont(font);
      final Text food_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      food_text.setText("");
      final Button moreFood = new Button(composite2,SWT.PUSH);
      moreFood.setText(" + ");
      moreFood.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		    	if (!food_text.getText().equals("")) {
		    		Float amount = setFood(String.valueOf(id), 10);
		    		//Float amount = Float.parseFloat(food_text.getText());
		    		//amount+=10;
		    		food_text.setText(Float.toString(amount));
				}
		    }
	  });
      final Button lessFood = new Button(composite2,SWT.PUSH);
      lessFood.setText(" - ");
      lessFood.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		    	if (!food_text.getText().equals("")) {
		    		if(Float.parseFloat(food_text.getText())>=10) {
		    			Float amount = setFood(String.valueOf(id), -10);
		    			//Float amount = Float.parseFloat(food_text.getText());
		    			//amount-=10;
		    			food_text.setText(Float.toString(amount));
		    		}
				}
		    }
	  });
      
      // setta il campo per le info sulla quantità di acqua corrente
      final Label water = new Label(composite2,SWT.NONE);
      water.setText("WATER QUANTITY (l)");
      water.setFont(font);
      final Text water_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
      water_text.setText("");
      final Button moreWater = new Button(composite2,SWT.PUSH);
      moreWater.setText(" + ");
      moreWater.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		    	if (!water_text.getText().equals("")) {
		    		Float amount = setWater(String.valueOf(id), 10);
		    		//Float amount = Float.parseFloat(water_text.getText());
		    		//amount+=10;
		    		water_text.setText(Float.toString(amount));
				}
		    }
	  });
      final Button lessWater = new Button(composite2,SWT.PUSH);
      lessWater.setText(" - ");
      lessWater.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		    	if (!water_text.getText().equals("")) {
		    		if(Float.parseFloat(water_text.getText())>=10) {
		    			Float amount = setWater(String.valueOf(id), -10);
		    			//Float amount = Float.parseFloat(water_text.getText());
		    			//amount-=10;
		    			water_text.setText(Float.toString(amount));
		    		}
				}
		    }
	  });
      
	  composite2.layout(true);
  }
  
  public static void setIds(Composite c) {
	  Integer count = 0;
	    Control[] children = c.getChildren();
	    for(Integer j = 0 ; j < children.length; j++) {
	        if(children[j] instanceof Composite) {
	            setIds((Composite) children[j]);
	        } else {
	            children[j].setData(count);
	            ++count;
	        }
	    }
	}
  // da implementare controlla che ci sia una nuova teca attiva
  public static List<String> getDeactivedBoxes() {
	  List<String> new_active_box_list = getOnlineBoxes();
	  //List<String> new_active_box_list = new ArrayList<String>();
	  List<String> deactived_box_list = new ArrayList<String>();
	  for(Integer i=0; i<old_active_box_list.size(); i++) {
		  Boolean flag = true;
		  for(Integer j=0; j<new_active_box_list.size(); j++) {
			  if(new_active_box_list.get(j).equals(old_active_box_list.get(i))) {
				  flag=false;
			  }
		  }
		  if(flag) {
			  deactived_box_list.add(old_active_box_list.get(i));
		  }
	  }
	  old_active_box_list = new_active_box_list;
	  return deactived_box_list;
  }
  
  public static List<String> getActiveBoxes() {
	  List<String> new_active_box_list = getOnlineBoxes();
	  //List<String> new_active_box_list = new ArrayList<String>();
	  List<String> active_box_list = new ArrayList<String>();
	  for(Integer i=0; i<new_active_box_list.size(); i++) {
		  Boolean flag = true;
		  for(Integer j=0; j<old_active_box_list.size(); j++) {
			  if(old_active_box_list.get(j).equals(new_active_box_list.get(i))) {
				  flag=false;
			  }
		  }
		  if(flag) {
			  active_box_list.add(new_active_box_list.get(i));
		  }
	  }
	  //old_active_box_list = new_active_box_list;
	  return active_box_list;
  }
  
  // da implementare...le funzioni qui sotto tornano valori relativi alle varie 
  // informazioni sulla teca da prendere sul db tramite mqtt
  public static Float readTemperature(String id) {
	  readTemperature(id);
	  Float temp = getThemperature(id);
	  return temp;
  }
  
  public static Integer askAnimalNumber(String id) {
	  Integer num = getAnimalsN(id);
	  return num;
  }
  
  public static Float readHumidity(String id) {
	  readHumidity(id);
	  Float hum = getHumidity(id);
	  return hum;
  }
  
  public static Integer readLight(String id) {
	  readLight(id);
	  Integer luce = getLight(id);
	  return luce;
  }
  
  public static String readWindler(String id) {
	  //Boolean state = false;
	  readWindler(id);
	  Boolean state = getWindler(id);
	  if(state == true) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static String readDisplay(String id) {
	  //Boolean state = false;
	  readDisplay(id);
	  Boolean state = getDisplay(id);
	  if(state == true) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static String readAlarm(String id) {
	  //Boolean state = false;
	  readAlarm(id);
	  Boolean state = getAlarm(id);
	  if(state == true) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static Float askAnimalFood(String id) {
	  Float food = getFoodQnt(id);
	  return food;
  }
  
  public static Float askAnimalWater(String id) {
	  Float water = getWaterQnt(id);
	  return water;
  }
  
  //funzioni che settano dei valori nel db tramite la Gui
  public static String setWindler(String id,String com) {
	  if(com.equals("ON")){
		  setWindler(id,true);
	  }
	  else{
		  setWindler(id, false);
	  }
	  state = getWindler(id);
	  if(state) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static String setDisplay(String id, String com) {
	  if(com.equals("ON")){
		  setDisplay(id,true);
	  }
	  else{
		  setDisplay(id, false);
	  }
	  state = getDisplay(id);
	  if(state) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }

  public static String setAlarm(String id, String com) {
	  Boolean state;
	  if(com.equals("ON")){
		  setAlarm(id, true);
	  }
	  else{
		  setAlarm(id, false);
	  }
	  state = getAlarm(id);
	  if(state) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static Float setFood(String id, Integer val) {
	  Float food = getFoodQnt(id);
	  Float new_food = food + val;
	  String toStr = String.valueOf(new_food);
	  setFoodQnt(id,toStr);
	  return getFoodQnt(id);
  }

  public static Float setWater(String id, Integer val) {
	  Float water = getWaterQnt(id);
	  Float new_water = water + val;
	  String toStr = String.valueOf(new_water);
	  setFoodQnt(id,toStr);
	  return getWaterQnt(id);
  }
}
*/
