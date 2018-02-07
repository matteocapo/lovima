package it.univaq.disim.se4s.mvl;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

import it.univaq.disim.se4s.dbquery.DbAnimalsInterface;
import it.univaq.disim.se4s.dbquery.DbInterface;
import it.univaq.disim.se4s.mqttfunction.readFunction;
import it.univaq.disim.se4s.mqttfunction.setFunction;

import org.eclipse.swt.layout.*;

import java.awt.image.DataBufferInt;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gui {

static List<String> old_active_box_list = new ArrayList<String>();

	private static Composite composite1;
	private static Composite composite2;
	private static Text id_text;

  public static void main(String[] args) {
	  
    final Display display = new Display();
    final Shell shell = new Shell(display);
    FillLayout fillLayout = new FillLayout();
    shell.setLayout(fillLayout);
    //shell.setSize(1500,900);
    shell.setMaximized(true);
    
    composite1 = new Composite(shell, SWT.BORDER);
	//composite1.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
    GridLayout gridLayout1 = new GridLayout();
    gridLayout1.numColumns = 4;
    gridLayout1.marginBottom = 30;
    gridLayout1.horizontalSpacing = 50;
    gridLayout1.verticalSpacing = 40;
    composite1.setLayout(gridLayout1);
	//RowLayout rowLayout1 = new RowLayout();
	//rowLayout1.center = true;
	//rowLayout1.spacing = 30;
	//rowLayout1.marginBottom =30;
	//composite1.setLayout(rowLayout1);
    setComposite1Title(composite1);
    
    composite2 = new Composite(shell,SWT.BORDER);
    GridLayout gridLayout2 = new GridLayout();
    gridLayout2.numColumns = 4;
    gridLayout2.horizontalSpacing = 50;
    gridLayout2.verticalSpacing = 20;
    composite2.setLayout(gridLayout2);
    //composite2.setBackground(display.getSystemColor(SWT.COLOR_RED));
    setComposite2Template(composite2);
    setIds(composite2);
    //composite2.pack();
    
    //START LISTENING
	try {
		readFunction.readAll();
	} catch (MqttException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    shell.open();
    new Thread() {
      public void run() {
          while(true) {
    	  try {
            Thread.sleep(5000);
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
            	try {
            		
            		List<String> activeBoxes = updateBoxList(composite1,composite2);

            		if(id_text.getText() != null && id_text.getText() != "") {
            			showDetails(id_text.getText(), composite2);
            			doLogic(id_text.getText());
            		}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
  
  public static List<String> updateBoxList(Composite composite1, Composite composite2) throws SQLException {
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
		final String id = new_active_box_list.get(i);
		label1.setText("ID: " + id);
		//final Label label2 = new Label(block, SWT.NONE);
		//label2.setText("Animal Type: ");
		final Button button = new Button(block,SWT.PUSH);
		button.setText("See Box Details");
		//premendo il bottone si apre nel secondo contenitore principale la lista dei dettagli della teca selezionata
	  	button.addListener(SWT.Selection, new Listener() { 
	  			 public void handleEvent(Event e) {
	  				 try {
						showDetails(id, comp2);
					} catch (MqttException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

	return new_active_box_list;
  }

  public static void showDetails(String id, Composite composite2) throws MqttException, SQLException {
	  Control[] children = composite2.getChildren();
	  for(Control e : children) {
		  if(e instanceof Text) {
			  Integer text_box_id = ((Integer)e.getData()).intValue();
			  switch(text_box_id) {
			  	case 2:
			  	((Text) e).setText(id);
			  	break;
			  	case 4: 
			  	Integer number = askAnimalNumber(id);
			  	((Text) e).setText(String.valueOf(number));
			  	break;
			  	case 6: 
			  	Float temp = readTemperature(id);
				((Text) e).setText(String.valueOf(temp));			  	
				break;
			  	case 8: 
			  	Float humidity = readHumidity(id);
				((Text) e).setText(String.valueOf(humidity));
			  	break;
			  	case 10:
			  	Integer light = readLight(id);
				((Text) e).setText(String.valueOf(light));
			  	break;
			  	case 12:
			  	String windler_state = readWindler(id);
				((Text) e).setText(windler_state);
			  	break;
			  	case 16:
			  	String display_state = readDisplay(id);
				((Text) e).setText(display_state);
			  	break;
			  	case 20:
			  	String alarm_state = readAlarm(id);
				((Text) e).setText(alarm_state);
			  	break;
			  	case 24:
			  	Double food = askAnimalFood(id);
				((Text) e).setText(String.valueOf(food));
			  	break;
			  	case 28:
			  	Double water = askAnimalWater(id);
				((Text) e).setText(String.valueOf(water));
				break;
			  }
		  }
	  }
  }
  
  public static void setComposite1Title(Composite composite1) {
	  
	  	//final Composite label_composite = new Composite(composite1, SWT.NONE);
		//RowLayout labelCompLayout = new RowLayout();
		//labelCompLayout.marginWidth = 300;
		//label_composite.setLayout(labelCompLayout);
		//label_composite.setBackground(composite1.getDisplay().getSystemColor(SWT.COLOR_GREEN));
		
		final Label composite_title = new Label(composite1, SWT.NONE);
		composite_title.setText("ACTIVE BOXES");
		FontData fontData = composite_title.getFont().getFontData()[0];
		Font font = new Font(composite1.getDisplay(), new FontData(fontData.getName(), fontData.getHeight(), SWT.BOLD));
		composite_title.setFont(font);
		GridData gridData = new GridData(SWT.CENTER, SWT.FILL, true, false);
		gridData.horizontalSpan = 4;
	    composite_title.setLayoutData(gridData);
	  
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
	  //gridData.verticalSpan = 2;
	  gridData.grabExcessHorizontalSpace = true;
      composite_title.setLayoutData(gridData);
      
      // setta il campo id
      final Label id = new Label(composite2,SWT.NONE);
      id.setText("BOX ID");
      id.setFont(font);
	  id_text = new Text(composite2, SWT.SINGLE | SWT.BORDER);
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
		        	String com = null;
					try {
						com = setWindler(id_text.getText(), "ON");
					} catch (MqttException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	windler_text.setText(com);
				}
		    }
	  });
      final Button buttonOff = new Button(composite2,SWT.PUSH);
      buttonOff.setText("SWITCH OFF");
      buttonOff.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (windler_text.getText().equals("ON")) {
		        	String com = null;
					try {
						com = setWindler(id_text.getText(),"OFF");
					} catch (MqttException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
		        	String com = null;
					try {
						com = setDisplay(id_text.getText(), "ON");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MqttException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	display_text.setText(com);
				}
		    }
	  });
      final Button displayOff = new Button(composite2,SWT.PUSH);
      displayOff.setText("SWITCH OFF");
      displayOff.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (display_text.getText().equals("ON")) {
		        	String com = null;
					try {
						com = setDisplay(id_text.getText(), "OFF");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MqttException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
		        	String com = null;
					try {
						com = setAlarm(id_text.getText(), "ON");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MqttException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	alarm_text.setText(com);
				}
		    }
	  });
      final Button alarmOff = new Button(composite2,SWT.PUSH);
      alarmOff.setText("SWITCH OFF");
      alarmOff.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		        if (alarm_text.getText().equals("ON")) {
		        	String com = null;
					try {
						com = setAlarm(id_text.getText(), "OFF");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MqttException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
		    		Double amount = null;
					try {
						amount = setFood(id_text.getText(), 10);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		//Float amount = Float.parseFloat(food_text.getText());
		    		//amount+=10;
		    		food_text.setText(Double.toString(amount));
				}
		    }
	  });
      final Button lessFood = new Button(composite2,SWT.PUSH);
      lessFood.setText(" - ");
      lessFood.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		    	if (!food_text.getText().equals("")) {
		    		if(Float.parseFloat(food_text.getText())>=10) {
		    			Double amount = null;
						try {
							amount = setFood(id_text.getText(), -10);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    			//Float amount = Float.parseFloat(food_text.getText());
		    			//amount-=10;
		    			food_text.setText(Double.toString(amount));
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
		    		Double amount = null;
					try {
						amount = setWater(id_text.getText(), 10);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		//Float amount = Float.parseFloat(water_text.getText());
		    		//amount+=10;
		    		water_text.setText(Double.toString(amount));
				}
		    }
	  });
      final Button lessWater = new Button(composite2,SWT.PUSH);
      lessWater.setText(" - ");
      lessWater.addListener(SWT.Selection, new Listener() { 
		    public void handleEvent(Event e) {
		    	if (!water_text.getText().equals("")) {
		    		if(Float.parseFloat(water_text.getText())>=10) {
		    			Double amount = null;
						try {
							amount = setWater(id_text.getText(), -10);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    			//Float amount = Float.parseFloat(water_text.getText());
		    			//amount-=10;
		    			water_text.setText(Double.toString(amount));
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
  public static List<String> getDeactivedBoxes() throws SQLException {
	  List<String> new_active_box_list = DbInterface.getOnlibeBoxes();
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
  
  public static List<String> getActiveBoxes() throws SQLException {
	  List<String> new_active_box_list = DbInterface.getOnlibeBoxes();
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
  public static Float readTemperature(String id) throws SQLException, MqttException {
	  //readFunction.readThemperature(id);
	  Float temp = DbInterface.getThemperature(id);
	  return temp;
  }
  
  public static Integer askAnimalNumber(String id) throws SQLException {
	  Integer num = DbInterface.getAnimalsN(id);
	  return num;
  }
  
  public static Float readHumidity(String id) throws MqttException, SQLException {
	  //readFunction.readHumidity(id);
	  Float hum = DbInterface.getHumidity(id);
	  return hum;
  }
  
  public static Integer readLight(String id) throws MqttException, SQLException {
	  //readFunction.readLight(id);
	  Integer luce = DbInterface.getLight(id);
	  return luce;
  }
  
  public static String readWindler(String id) throws MqttException, SQLException {
	  //Boolean state = false;
	  //readFunction.readWindler(id);
	  Boolean state = DbInterface.getWindler(id);
	  if(state == true) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static String readDisplay(String id) throws MqttException, SQLException {
	  //Boolean state = false;
	  //readFunction.readDisplay(id);
	  Boolean state = DbInterface.getDisplay(id);
	  if(state == true) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static String readAlarm(String id) throws MqttException, SQLException {
	  //Boolean state = false;
	  //readFunction.readAlarm(id);
	  Boolean state = DbInterface.getAlarm(id);
	  if(state == true) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static Double askAnimalFood(String id) throws SQLException {
	  Double food = DbInterface.getFoodQnt(id);
	  return food;
  }
  
  public static Double askAnimalWater(String id) throws SQLException {
	  Double water = DbInterface.getWaterQnt(id);
	  return water;
  }
  
  //funzioni che settano dei valori nel db tramite la Gui
  public static String setWindler(String id,String com) throws MqttException, SQLException {
	  boolean state;
	  if(com.equals("ON")){
		  DbInterface.setWindler(id, true);
		  setFunction.setWindler(id,true);
	  }
	  else{
		  setFunction.setWindler(id, false);
		  DbInterface.setWindler(id, false);
	  }
	  state = DbInterface.getWindler(id);
	  System.out.println(Boolean.toString(state));
	  if(state) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static String setDisplay(String id, String com) throws SQLException, MqttException {
	  boolean state;
	  if(com.equals("ON")){
		  DbInterface.setDisplay(id, true);
		  setFunction.setDisplay(id,true);
	  }
	  else{
		  DbInterface.setDisplay(id, false);
		  setFunction.setDisplay(id, false);
	  }
	  state = DbInterface.getDisplay(id);
	  if(state) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }

  public static String setAlarm(String id, String com) throws SQLException, MqttException {
	  Boolean state;
	  if(com.equals("ON")){
		  DbInterface.setAlarm(id, true);
		  setFunction.setAlarm(id, true);
	  }
	  else{
		  DbInterface.setAlarm(id, false);
		  setFunction.setAlarm(id, false);
	  }
	  state = DbInterface.getAlarm(id);
	  
	  if(state) {
		  return "ON";
	  }
	  else {
		  return "OFF";
	  }
  }
  
  public static Double setFood(String id, Integer val) throws SQLException {
	  Double food = DbInterface.getFoodQnt(id);
	  Float new_food = (float) (food + val);
	  String toStr = String.valueOf(new_food);
	  DbInterface.setFoodQnt(id,toStr);
	  return DbInterface.getFoodQnt(id);
  }

  public static Double setWater(String id, Integer val) throws SQLException {
	  Double water = DbInterface.getWaterQnt(id);
	  Float new_water = (float) (water + val);
	  String toStr = String.valueOf(new_water);
	  DbInterface.setWaterQnt(id,toStr);
	  return DbInterface.getWaterQnt(id);
  }
  
  
  public static void doLogic(String id) throws SQLException, MqttException {
	  //valori di default, non toccare
	  boolean alarmMustBeEnabled = false;
	  boolean windlerMustBeEnabled = false;
	  boolean displayMustBeEnabled = false;
	  
	  //dizionario contenente i dati relativi all'animale da dover tenere sotto controllo
	  Map<String, String> dict = DbAnimalsInterface.getAllAnimalInfo(DbInterface.getIdAnimal(id));
	  Integer nAnimals = DbInterface.getAnimalsN(id);
	  Integer totFood = nAnimals*Integer.parseInt((dict.get("foodDoses")));
	  Double totWater = nAnimals.doubleValue()*100;
	  Float maxTemp = Float.parseFloat(dict.get("maxTemp"));
	  Float minTemp = Float.parseFloat(dict.get("minTemp"));
	  Float maxHum = Float.parseFloat(dict.get("maxhum"));
	  Float minHum = Float.parseFloat(dict.get("minhum"));
	  // valori settati in locale per simulare le condizioni di un caso di incendio
	  Float tempMax = (float) 40.1;
	  Float lightMax = (float) 650;
	  	  
	  //water and food alarm management
	  if(DbInterface.getFoodQnt(id) <= (double) totFood || DbInterface.getWaterQnt(id) <= totWater) {
		  alarmMustBeEnabled = true;
	  }
	  
	  //themperature management windler
	  if(DbInterface.getThemperature(id) <= minTemp) {
		  displayMustBeEnabled = true;
	  }else if (DbInterface.getThemperature(id) >= maxTemp) {
		  windlerMustBeEnabled = true;
	  }

	  if(DbInterface.getThemperature(id) >= tempMax || DbInterface.getLight(id) >= lightMax) {
		  alarmMustBeEnabled = true;
		  windlerMustBeEnabled = true;
	  }
	  
	  if(DbInterface.getHumidity(id) <= minHum) {
		  displayMustBeEnabled = true;
	  }else if(DbInterface.getHumidity(id) >= maxHum) {
		  windlerMustBeEnabled = true;
	  }
	  
	  
	
	  //non toccare le funzioni qua sotto!!!!
	  if(alarmMustBeEnabled && !DbInterface.getAlarm(id))
		  setFunction.setAlarm(id, true);
	  else if(!alarmMustBeEnabled && DbInterface.getAlarm(id))
		  setFunction.setAlarm(id, false);
	  
	  if(windlerMustBeEnabled && !DbInterface.getWindler(id))
		  setFunction.setWindler(id, true);
	  else if(!windlerMustBeEnabled && DbInterface.getWindler(id))
		  setFunction.setWindler(id, false);
	  
	  if(displayMustBeEnabled && !DbInterface.getDisplay(id))
		  setFunction.setDisplay(id, true);
	  else if(!displayMustBeEnabled && DbInterface.getDisplay(id))
		  setFunction.setDisplay(id, false);
	  
  	}


}

