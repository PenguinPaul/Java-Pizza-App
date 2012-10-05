/**
 * @(#)FinalProject.java
 *
 * FinalProject Applet application
 *
 * Pizza Ordering App
 *		This applet will allow users to create, customize, and order a pizza.
 *
 * Song used is "That's Amore", performed by Dean Martin
 *
 *
 * @author Paul Hedman
 * @version 1.5 4/26/2012
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.AudioClip;

public class FinalProject extends JApplet implements ActionListener {

	//sound
	AudioClip amore;

	//all of the JLabels, on two lines for neatness
	JLabel bg, thankYou, logo, title, subtitle, rightText, instructions, sizeLabel, addableLabel, addedLabel, costLabel, cost;
	JLabel pizza, pepperoni, sausage, bacon, peppers, mushrooms;

	//JList stuff
	DefaultListModel addable, added;
	JList addableToppings, addedToppings;

	//buttons and combo box
	JComboBox<String> pizzaSize;
	JButton addButton, removeButton, orderButton, soundButton;

	boolean toppings[] = new boolean[5];
	//0 - pepperoni, 1 - sausage, 2 - bacon, 3 - peppers, 4 - mushrooms

	boolean setup, sound;

	//current pizza size
	int currentSize = 3;

	ImageIcon soundIcon, muteIcon;



	public void init()
	{
		//this is used later in the action listener, we don't want the action listener running until init is done
		setup = false;

		//null layout
		setLayout(null);

		//the background
		bg = new JLabel(new ImageIcon(getImage(getCodeBase(),"bg.png")));
		bg.setBounds(0,0,700,500);
		add(bg);

		//thank you message
		thankYou = new JLabel(new ImageIcon(getImage(getCodeBase(),"thankyou.png")));
		thankYou.setBounds(0,0,700,500);
		bg.add(thankYou);
		thankYou.setVisible(false);

		//pizza image logo
		logo = new JLabel(new ImageIcon(getImage(getCodeBase(),"pizzaLogo.png")));
		logo.setBounds(10, 10, 106, 72);
		bg.add(logo);

		//title
		title = new JLabel("<html><font size=24><i>Paul's Pizzeria</i></font></html>");
		title.setBounds(125, 10, 500, 50);
		bg.add(title);

		//text under the title
		subtitle = new JLabel("Family Owned and Operated Since 2012");
		subtitle.setBounds(150, 50, 300, 20);
		bg.add(subtitle);

		//description saying pizza ordering app
		rightText = new JLabel("<html><h3><i>Online Pizza Ordering App</i></h3></html>");
		rightText.setBounds(445, 30, 300, 20);
		bg.add(rightText);

		//instructions
		instructions = new JLabel("Customize your pizza below! \u2193");
		instructions.setBounds(475,60,200,20);
		bg.add(instructions);

		//pizza size label & selector
		sizeLabel = new JLabel("Size");
		sizeLabel.setBounds(595, 85, 80, 20);
		bg.add(sizeLabel);

		pizzaSize = new JComboBox<String>();
		pizzaSize.addItem("Tiny ($3)");
		pizzaSize.addItem("Small ($5)");
		pizzaSize.addItem("Medium ($7)");
		pizzaSize.addItem("Large ($10)");
		pizzaSize.addItem("Huge ($15)");
		pizzaSize.setBounds(595, 110, 100, 20);
		bg.add(pizzaSize);
		pizzaSize.addActionListener(this);

		//grab the current pizza size from the applet parameters
		String sizeParam = this.getParameter("size");

		switch(sizeParam)
		{
			case "tiny":
				currentSize = 0;
				break;
			case "small":
				currentSize = 1;
				break;
			case "medium":
				currentSize = 2;
				break;
			case "large":
				currentSize = 3;
				break;
			case "huge":
				currentSize = 4;
				break;

			//should never need but just in case
			default:
				currentSize = 3;
				break;
		}

		//select the current size
		pizzaSize.setSelectedIndex(currentSize);


		//toppings lists

		//label
		addableLabel = new JLabel("Addable Toppings");
		addableLabel.setBounds(595, 135, 110, 20);
		bg.add(addableLabel);

		//create addable toppings list
		addable = new DefaultListModel();
		addableToppings = new JList(addable);
		addableToppings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addable.add(0,"Pepperoni ($1)");
		addable.add(1,"Sausage ($1)");
		addable.add(2,"Bacon ($2)");
		addable.add(3,"Peppers ($2)");
		addable.add(4,"Mushrooms ($2)");

		//add it to the applet
		addableToppings.setBounds(595, 155, 95, 90);
		bg.add(addableToppings);

		//buttons
		addButton = new JButton("\u25BC");
		addButton.setBounds(595,245,47,24);
		bg.add(addButton);
		addButton.addActionListener(this);

		removeButton = new JButton("\u25B2");
		removeButton.setBounds(642,245,48,24);
		bg.add(removeButton);
		removeButton.addActionListener(this);

		//the added toppings
		addedLabel = new JLabel("Added Toppings");
		addedLabel.setBounds(595, 275, 110, 14);
		bg.add(addedLabel);

		//added toppings list
		added = new DefaultListModel();
		addedToppings = new JList(added);
		addedToppings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		added.add(0," ");
		added.add(1," ");
		added.add(2," ");
		added.add(3," ");
		added.add(4," ");
		addedToppings.setBounds(595, 295, 95, 90);
		bg.add(addedToppings);


		//cost stuff
		costLabel = new JLabel("Cost");
		costLabel.setBounds(595, 385, 80, 20);
		bg.add(costLabel);

		cost = new JLabel("<html><font size=20>$10.00</font></html>");
		cost.setBounds(575, 400, 120, 40);
		bg.add(cost);

		//order button
		orderButton = new JButton("Order");
		orderButton.setBounds(595, 460, 80, 30);
		bg.add(orderButton);
		orderButton.addActionListener(this);

		//sound icons
		soundIcon = new ImageIcon(getImage(getCodeBase(),"sound.png"));
		muteIcon = new ImageIcon(getImage(getCodeBase(),"mute.png"));

		//sound button
		soundButton = new JButton(soundIcon);
		soundButton.setBounds(664,0,36,36);
		bg.add(soundButton);
		soundButton.addActionListener(this);

		//pizza
		pizza = new JLabel(new ImageIcon(getImage(getCodeBase(),"basePizza.png")));
		pizza.setBounds(90,130,350,333);
		bg.add(pizza);

		//toppings - pepperoni, sausage, bacon, peppers, mushrooms
		pepperoni = new JLabel(new ImageIcon(getImage(getCodeBase(),"pepperoni.png")));
		pepperoni.setBounds(0,0,350,333);
		pizza.add(pepperoni);

		sausage = new JLabel(new ImageIcon(getImage(getCodeBase(),"sausage.png")));
		sausage.setBounds(0,0,350,333);
		pizza.add(sausage);

		bacon = new JLabel(new ImageIcon(getImage(getCodeBase(),"bacon.png")));
		bacon.setBounds(0,0,350,333);
		pizza.add(bacon);

		peppers = new JLabel(new ImageIcon(getImage(getCodeBase(),"peppers.png")));
		peppers.setBounds(0,0,350,333);
		pizza.add(peppers);

		mushrooms = new JLabel(new ImageIcon(getImage(getCodeBase(),"mushrooms.png")));
		mushrooms.setBounds(0,0,350,333);
		pizza.add(mushrooms);


		//add toppings... yummy!

		//all toppings are off by default
		toppings[0] = false;
		toppings[1] = false;
		toppings[2] = false;
		toppings[3] = false;
		toppings[4] = false;

		//get any preset toppings in the applet parameters
		String toppingsParam = this.getParameter("toppings");

		//are there any?
		if(!toppingsParam.equals("none"))
		{
			//yes, there are.
			//split the comma seperated values (CSV) into an array
			String[] toppingsParamArray = toppingsParam.split(",");

			//for each topping specified, add it to the pizza
			for (String paramTopping : toppingsParamArray) {
				switch(paramTopping)
				{
					case "pepperoni":
						toppings[0] = true;
						addable.set(0," ");
						added.set(0, "Pepperoni ($1)");
						break;

					case "sausage":
						toppings[1] = true;
						addable.set(1," ");
						added.set(1, "Sausage ($1)");
						break;

					case "bacon":
						toppings[2] = true;
						addable.set(2," ");
						added.set(2, "Bacon ($2)");
						break;

					case "peppers":
						toppings[3] = true;
						addable.set(3," ");
						added.set(3, "Peppers ($2)");
						break;

					case "mushrooms":
						toppings[4] = true;
						addable.set(4," ");
						added.set(4, "Mushrooms ($2)");
						break;
				}
			}
		}

		//make the pizza!
		makePizza(currentSize,toppings);


		//load music
		amore = getAudioClip(getCodeBase(), "amore.wav");

		//get parameter
		String musicParam = this.getParameter("sound");

		//start music if enabled
		if(musicParam.equals("false"))
		{
			sound = false;
			soundButton.setIcon(muteIcon);
		} else {
			amore.loop();
			sound = true;
		}
		//start sound


		//we're done, the action listener can run now
		setup = true;

	}

	//we never use paint
	public void paint(Graphics g)
	{
		super.paint(g);
	}

	public void actionPerformed(ActionEvent event)
	{
		//see init for why we use this
		if(setup)
		{
			Object source = event.getSource();

			if(source == pizzaSize)
			{
				//the user changed the pizza size
				//get the newly selected item
				String size = (String)pizzaSize.getSelectedItem();

				//change the size based on the selection
				if(size.equals("Tiny ($3)"))
				{
					currentSize = 0;
				} else if(size.equals("Small ($5)"))
				{
					currentSize = 1;
				} else if(size.equals("Medium ($7)"))
				{
					currentSize = 2;
				} else if(size.equals("Large ($10)"))
				{
					currentSize = 3;
				} else if(size.equals("Huge ($15)"))
				{
					currentSize = 4;
				}

				//remake the pizza with the new size
				makePizza(currentSize, toppings);

			} else if(source == addButton) {
				//we're adding a topping

				//get selected topping
				int topping = addableToppings.getSelectedIndex();

				//add the topping to the added topping list and delete it from the addable list
				switch(topping)
				{
					case 0:
						toppings[topping] = true;
						addable.set(topping," ");
						added.set(topping,"Pepperoni ($1)");
						break;
					case 1:
						toppings[topping] = true;
						addable.set(topping," ");
						added.set(topping,"Sausage ($1)");
						break;
					case 2:
						toppings[topping] = true;
						addable.set(topping," ");
						added.set(topping,"Bacon ($2)");
						break;
					case 3:
						toppings[topping] = true;
						addable.set(topping," ");
						added.set(topping,"Peppers ($2)");
						break;
					case 4:
						toppings[topping] = true;
						addable.set(topping," ");
						added.set(topping,"Mushrooms ($2)");
						break;
				}

				//remake pizza with new toppings
				makePizza(currentSize,toppings);
				//clear selection from the now blank item
				addableToppings.clearSelection();

			} else if(source == removeButton) {
				//removing a topping

				//get topping to be removed
				int topping = addedToppings.getSelectedIndex();

				//hide topping on added list and add it back toi the addable list
				switch(topping)
				{
					case 0:
						toppings[topping] = false;
						added.set(topping," ");
						addable.set(topping,"Pepperoni ($1)");
						break;
					case 1:
						toppings[topping] = false;
						added.set(topping," ");
						addable.set(topping,"Sausage ($1)");
						break;
					case 2:
						toppings[topping] = false;
						added.set(topping," ");
						addable.set(topping,"Bacon ($2)");
						break;
					case 3:
						toppings[topping] = false;
						added.set(topping," ");
						addable.set(topping,"Peppers ($2)");
						break;
					case 4:
						toppings[topping] = false;
						added.set(topping," ");
						addable.set(topping,"Mushrooms ($2)");
						break;
				}

				//remake pizza
				makePizza(currentSize,toppings);
				//clear selection
				addedToppings.clearSelection();

			} else if(source == orderButton) {
				//order the pizza
				orderPizza();
			} else if (source == soundButton) {
				toggleSound();
			}
		}
	}

	/*
	 * This method takes the pizza size in and
	 * boolean toppings array and updates the pizza
	 * image and then calculates the new pizza cost (which it returns).
	 */
	public int makePizza(int size, boolean[] toppings)
	{
		//pizza cost
		int pizzaCost = 0;

		//hide/show toppings
		pepperoni.setVisible(toppings[0]);
		sausage.setVisible(toppings[1]);
		bacon.setVisible(toppings[2]);
		peppers.setVisible(toppings[3]);
		mushrooms.setVisible(toppings[4]);

		//calulate price
		for (int i = 0; i < toppings.length; i++) {

			if(toppings[i])
			{
				if(i < 2)
				{
					//these toppings (pepperoni and sausage) only cost $1
					pizzaCost++;
				} else {
					//these toppings (bacon, peppers, mushrooms) cost $2
					pizzaCost++;
					pizzaCost++;
				}
			}

		}

		//add pizza size cost
		switch(size)
		{
			case 0:
				pizzaCost = pizzaCost + 3;
				break;
			case 1:
				pizzaCost = pizzaCost + 5;
				break;
			case 2:
				pizzaCost = pizzaCost + 7;
				break;
			case 3:
				pizzaCost = pizzaCost + 10;
				break;
			case 4:
				pizzaCost = pizzaCost + 15;
				break;
		}

		//update cost label
		cost.setText("<html><font size=20>$"+pizzaCost+".00</font></html>");

		//return the pizza cost
		return pizzaCost;

	}

	public void orderPizza()
	{
		//time until pickup/delivery
		int time;
		//string saying whether the pizza is being delivered or picked up
		String action;
		//price of delivery
		int deliveryPrice;

		//order!

		//get name

		/* we need to declare this up here,
		 * not in the try statement,
		 * otherwise we get a private error */

		String name = "";

		try {
			//get input
		    name = JOptionPane.showInputDialog("What is your name?");

		    //is it empty?
		    if(name.equals(""))
			{
			    JOptionPane.showMessageDialog(this, "Cancelled.");
				return;
			}
		} catch (NullPointerException e) {
			//the user hit "Cancel"
		    JOptionPane.showMessageDialog(this, "Cancelled.");
		    return;
		}

		//delivery method - yes/no dialog
		int method = JOptionPane.showConfirmDialog(this, "Would you like your pizza delivered for an additional $3?", "Delivery", JOptionPane.YES_NO_OPTION);


		//how is this person getting the pizza?
		if(method == 0)
		{
			//it's being delivered

			action = "delivered (fee $3)";
			time = 30;
			deliveryPrice = 3;

			//get address

			try {
				//get input
		   		String address = JOptionPane.showInputDialog("What is your street address?");

				//empty
		   		if(address.equals(""))
				{
				    JOptionPane.showMessageDialog(this, "Invalid address!");
					return;
				}

			} catch (NullPointerException e) {
				//user pressed cancel
		    	JOptionPane.showMessageDialog(this, "Cancelled.");
		   		return;
			}

			//ZIP code
			try {
				//array of ZIP codes for the dropdown box
				//needs to be string, not int, because of the "Other" option
		   		String[] choices = {"55443", "55428", "55444", "55445", "55429", "Other"};

				//get input
    			String zipCodeString = (String)JOptionPane.showInputDialog(null, "ZIP Code", "Please choose your five digit ZIP code", JOptionPane.QUESTION_MESSAGE, null, choices, choices[3]);

				//the user selected Other
	    		if(zipCodeString.equals("Other"))
				{
					String zipsString = "";

					/* instead of building a static list,
					 * we use this so if we add/remove any
					 * ZIP codes we only have to change
					 * the array above */

					for (int i = 0; i < choices.length; i++)
					{
						if(i == (choices.length - 2))
						{
							zipsString += " and "+choices[i];
						} else if (i == (choices.length - 1)) {
							//nothing, we don't want to display "Other"
						} else {
							zipsString += choices[i]+", ";
						}
					}

					//display error message
					JOptionPane.showMessageDialog(this, "We only deliver to Brooklyn Park area ZIP codes "+zipsString+".");
					return;
				}

			} catch (NullPointerException e) {
				//user pressed cancel
			    JOptionPane.showMessageDialog(this, "Cancelled.");
			   	return;
			}


		} else {
			//it's going to be picked up
			JOptionPane.showMessageDialog(this,"Ok, you will pick the pizza up in the store.");
			action = "ready to pickup";
			time = 15;
			deliveryPrice = 0;
		}

		//final pizza price including delivery price
		int finalPrice = makePizza(currentSize,toppings) + deliveryPrice;

		String sizeString = "";

		switch(currentSize)
		{
			case 0:
				sizeString = "Tiny";
				break;
			case 1:
				sizeString = "Small";
				break;
			case 2:
				sizeString = "Medium";
				break;
			case 3:
				sizeString = "Large";
				break;
			case 4:
				sizeString = "Huge";
				break;
		}

		String toppingsInfo = "";
		String toppingsString = "";

		//make string of all toppings, seperated by commas
		if(toppings[0])
			toppingsString = toppingsString + "Pepperoni, ";
		if(toppings[1])
			toppingsString = toppingsString + "Sausage, ";
		if(toppings[2])
			toppingsString = toppingsString + "Bacon, ";
		if(toppings[3])
			toppingsString = toppingsString + "Peppers, ";
		if(toppings[4])
			toppingsString = toppingsString + "Mushrooms, ";

		//are there any toppings?
		if(toppingsString != "")
		{
			//trim last comma and space from the list
			toppingsString = toppingsString.substring(0, (toppingsString.length()-2));
			toppingsInfo = "It has the topping(s) "+toppingsString+".\n";
		} else {
			toppingsInfo = "It has no toppings.\n";
		}

		//show final message
		int orderOrNot = JOptionPane.showConfirmDialog(this, "Your final price is $"+finalPrice+".00.  The pizza size is "+sizeString+".\n"+toppingsInfo+"Your order is under the name "+name+" and will be "+action+" in about "+time+" minutes.\n\nIs this ok?  Press \"Yes\" to order, press \"No\" to cancel.", "Order Confirmation", JOptionPane.YES_NO_OPTION);

		//are we going through with it or did the user bail out?
		if(orderOrNot == 0)
		{
			//order up!

			//show thank you label
			thankYou.setVisible(true);
			//hide the rest
			addButton.setVisible(false);
			removeButton.setVisible(false);
			pizzaSize.setVisible(false);
			orderButton.setVisible(false);
			addableToppings.setVisible(false);
			addedToppings.setVisible(false);
			soundButton.setVisible(false);


			/* Pseudocode:
			 *
			 * Send a request to the pizzeria's website
			 * with toppings, size, name, address if necessary, etc
			 *
			 * Every so often ping the website again to see
			 * if the order is done, if it is popup a JOptionPane
			 * message box.
			 *
			 * Comment:
			 *
			 * I tried to do this with my website but received errors
			 * about security issues in the applet and read somewhere
			 * you needed signed applets to access the internet.
			 * Signing and applet looked complicated so I cut out the
			 * feature.
			 *
			 */

			//show message confirming order
			JOptionPane.showMessageDialog(this, "Order Placed!");

		} else {
			//user cancelled
			return;
		}
	}

	public void toggleSound()
	{
		//is the sound playing?
		if(sound)
		{
			//if so turn it off
			sound = false;
			amore.stop();
			soundButton.setIcon(muteIcon);
		} else {
			//if it isn't turn it on
			sound = true;
			amore.loop();
			soundButton.setIcon(soundIcon);
		}
	}

	//so the music stops when the applet is closed
	public void stop()
	{
		amore.stop();
	}
}