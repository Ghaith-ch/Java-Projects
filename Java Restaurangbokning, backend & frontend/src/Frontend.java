import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Frontend extends JFrame{

	private JPanel pTop,pCenter,pWest,pEast,pTopOfCenter,pGridCenter;
	private JLabel welcome,available,notAvailable;
	private JButton addButton,deletButton; 	
	private DefaultListModel<String>model;
	private JList list; 
	private JButton buttons[];    
	private Image floorplan;
	private Backend backend;
	private final Color green = new Color (30,172,139);
	private final Color byzantium = new Color(112,41,99);
	
	public Frontend() {
		backend=new Backend(this);
		pTop=new JPanel();
		pCenter=new JPanel();
		pWest=new JPanel();
		pEast=new JPanel();
		pTopOfCenter=new JPanel();
		pGridCenter=new JPanel();
		buttons=new JButton[13];		
	
		welcome= new JLabel("<html><strong>Welcome To Our Resturant!</strong></html>");
		welcome.setFont(new Font("Calibri", Font.BOLD, 30));
		welcome.setForeground(new Color(204,204,204));
		pTop.setBorder(BorderFactory.createEmptyBorder(25,0,25,0));
		pTop.setBackground(new Color(38,70,83));
		pTop.add(welcome);
		
		addButton = new JButton("Add to queue");
		addButton.setFocusable(false);
		addButton.setFont(new Font("Calibri ",Font.BOLD,20));
		addButton.setForeground(new Color(204,204,204));
		addButton.setBackground(Color.BLACK);
		addButton.addActionListener(backend);
		addButton.setActionCommand("add");
		model= new DefaultListModel<String>();
		model.addElement("test");
		
		list=new JList(model);
		list.setBorder(BorderFactory.createEmptyBorder(10,10,1,10));
		list.setFont(new Font("Calibri ",Font.BOLD,20));
		list.setFixedCellHeight(50);
		list.setFixedCellWidth(150);

		deletButton = new JButton("Delet from queue");
		deletButton.setFocusable(false);
		deletButton.setFont(new Font("Calibri ",Font.BOLD,20));
		deletButton.setForeground(new Color(204,204,204));
		deletButton.setBackground(Color.BLACK);
		deletButton.addActionListener(backend);
		deletButton.setActionCommand("delet");

		addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		list.setAlignmentX(Component.LEFT_ALIGNMENT);
		deletButton.setAlignmentX(Component.LEFT_ALIGNMENT);

		pWest.setLayout(new BoxLayout (pWest, BoxLayout.Y_AXIS));
		pWest.setBorder(BorderFactory.createEmptyBorder(30,10,0,30));
		pWest.setBackground(new Color(38,70,83));

		pWest.add(addButton);
		pWest.add(Box.createRigidArea(new Dimension(0, 20)));
		pWest.add(list);
		pWest.add(Box.createRigidArea(new Dimension(0, 20)));
		pWest.add(deletButton);

		available= new JLabel("<html><strong><font color= #1EAC8B>Available</font></strong></html>");
		available.setFont(new Font("Calibri", Font.BOLD, 30));
		notAvailable= new JLabel("<html><strong><font color= #702963>Not Available</font></strong></html>");
		notAvailable.setFont(new Font("Calibri", Font.BOLD, 30));
		
		pTopOfCenter.add(available);
		pTopOfCenter.add(Box.createRigidArea(new Dimension(100, 0)));
		pTopOfCenter.add(notAvailable);
		pTopOfCenter.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pTopOfCenter.setBackground(Color.BLACK);

		for(int i=1;i<13;i++) {
			buttons[i] = new JButton(" "+ i);
			buttons[i].setFont(new Font("Calibri", Font.BOLD, 30));
			buttons[i].setBackground(new Color(30,172,139));
			buttons[i].addActionListener(backend);
			buttons[i].setActionCommand(i+"");
			pGridCenter.add(buttons[i]);		
		}
		pGridCenter.setLayout(new GridLayout(4,4,3,3));
		
		pCenter.setLayout(new BorderLayout());
		pCenter.add(pTopOfCenter,BorderLayout.NORTH);
		pCenter.add(pGridCenter,BorderLayout.CENTER);

		try {
			Image img = ImageIO.read(new File("floorplan.png"));
			floorplan = img.getScaledInstance(
					img.getWidth(null) / 2, img.getHeight(null) / 2, Image.SCALE_SMOOTH);
			JLabel picLabel = new JLabel(new ImageIcon(floorplan));
			pEast.add(picLabel);
			
		} catch (IOException e) {
			System.out.println("Cannot load the image");
			System.exit(1);
		}
		this.setLayout(new BorderLayout());
		this.add(pTop, BorderLayout.NORTH);
		this.add(pWest, BorderLayout.WEST);
		this.add(pEast, BorderLayout.EAST);
		this.add(pCenter, BorderLayout.CENTER);

		this.setMinimumSize(new Dimension(1300, 800)); 
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public void addToLista(String name, int number) {
		String info=name+" "+Integer.toString(number); 
		model.addElement(info);
	}
	
	public void deletFromLista() {
		System.out.print(list.getSelectedIndex());

		if(list.getSelectedIndex()>=0) {
			
			int result = JOptionPane.showConfirmDialog(this,"Are you Sure?", "Delet from Lista",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
		    if(result == JOptionPane.YES_OPTION)
		            	model.remove(list.getSelectedIndex());            
		}
		
		else
			JOptionPane.showMessageDialog(this,"Please select first from the lista!");
	}
	
	public boolean checkColor(int number) {

		if (buttons[number].getBackground().getRGB()==green.getRGB()) 
			return true;		
		else 
			return false;		
	}
	
	public void updateView(int number, Color color) {
		if (color.getRGB()==green.getRGB()) 
			buttons[number].setBackground(byzantium);
		else 
			buttons[number].setBackground(green);
	}

	public static void main(String[] args) {
		new Frontend();
	}
}
