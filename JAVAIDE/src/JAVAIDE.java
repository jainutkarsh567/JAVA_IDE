import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.*;

public class JAVAIDE extends JFrame implements ActionListener {
JMenuBar mbar;
JMenu mnuFile,mnuBuild;
JMenuItem mitNew,mitOpen,mitClose, mitClose_All,mitSave,mitSave_As,mitSave_All,mitOpen_Workspace,mitSave_Workspace,mitClose_Workspace,mitPrint,mitRecent_Files,mitRecent_Workspaces,mitExit,mitCompile_Project,mitCompile_File,mitCompile_Batch,mitExecute_Project,mitExecute_File,mitStart_Debugger,mitStep,mitStep_Intro,mitStep_Out,mitContinue,mitDebugger,mitRuntime_Configuration;
JPanel p1,p2;
JSplitPane jsp;
JTextArea t1,t2;
File currentFile=null;
String fileName="",filePath="";
JToolBar tb;
JButton run,compile;
boolean saveflag=true;

JAVAIDE(){
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	mnuFile=new JMenu("FILE");
	mnuBuild=new JMenu("BUILD");

	mitNew=new JMenuItem("NEW");
	mitNew.addActionListener(this);
	mitNew.setMnemonic(KeyEvent.VK_N);
	mitOpen=new JMenuItem("OPEN");
	mitOpen.addActionListener(this);
	mitOpen.setMnemonic(KeyEvent.VK_O);
	mitOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
	mitClose=new JMenuItem("CLOSE");
	mitClose.addActionListener(this);
	mitClose.setMnemonic(KeyEvent.VK_C);
	mitClose_All=new JMenuItem("CLOSE ALL");
	mitClose_All.addActionListener(this);
	mitSave=new JMenuItem("SAVE");
	mitSave.addActionListener(this);
	mitSave.setMnemonic(KeyEvent.VK_S);
	mitSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	mitSave_As=new JMenuItem("SAVE AS");
	mitSave_As.addActionListener(this);
	mitSave_All=new JMenuItem("SAVE ALL");
	mitSave_All.addActionListener(this);
	mitOpen_Workspace=new JMenuItem("OPEN WORKSPACE");
	mitOpen_Workspace.addActionListener(this);
	mitSave_Workspace=new JMenuItem("SAVE WORKSPACE");
	mitSave_Workspace.addActionListener(this);
	mitClose_Workspace=new JMenuItem("CLOSE WORKSPACE");
	mitClose_Workspace.addActionListener(this);
	mitPrint=new JMenuItem("PRINT");
	mitPrint.addActionListener(this);
	mitRecent_Files=new JMenuItem("RECENT FILES");
	mitRecent_Files.addActionListener(this);
	mitRecent_Workspaces=new JMenuItem("RECENT WORKSAPCES");
	mitRecent_Workspaces.addActionListener(this);
	mitExit=new JMenuItem("EXIT");
	mitExit.addActionListener(this);
	
	mitCompile_Project=new JMenuItem("COMPILE PROJECT");
	mitCompile_Project.addActionListener(this);
	mitCompile_File=new JMenuItem("COMPILE FILE");
	mitCompile_File.addActionListener(this);
	mitCompile_Batch=new JMenuItem("COMPILE BATCH");
	mitCompile_Batch.addActionListener(this);
	mitExecute_File=new JMenuItem("EXECUTE FILE");
	mitExecute_File.addActionListener(this);
	mitExecute_Project=new JMenuItem("EXECUTE PROJECT");
	mitExecute_Project.addActionListener(this);
	mitStart_Debugger=new JMenuItem("START DEBUGGER");
	mitStart_Debugger.addActionListener(this);
	mitStep=new JMenuItem("STEP");
	mitStep.addActionListener(this);
	mitStep_Intro=new JMenuItem("STEP INTRO");
	mitStep_Intro.addActionListener(this);
	mitStep_Out=new JMenuItem("STEP OUT");
	mitStep_Out.addActionListener(this);
	mitContinue=new JMenuItem("CONTINUE");
	mitContinue.addActionListener(this);
	mitDebugger=new JMenuItem("DEBUGGER");
	mitDebugger.addActionListener(this);
	mitRuntime_Configuration=new JMenuItem("RUNTIME CONFIGURATION");
	mitRuntime_Configuration.addActionListener(this);
	
	mnuFile.add(mitNew);
	mnuFile.add(mitOpen);
	mnuFile.add(mitClose);
	mnuFile.add(mitClose_All);
	mnuFile.addSeparator();
	mnuFile.add(mitSave);
	mnuFile.add(mitSave_As);
	mnuFile.add(mitSave_All);
	mnuFile.addSeparator();
	mnuFile.add(mitOpen_Workspace);
	mnuFile.add(mitSave_Workspace);
	mnuFile.add(mitClose_Workspace);
	mnuFile.addSeparator();
	mnuFile.add(mitPrint);
	mnuFile.addSeparator();
	mnuFile.add(mitRecent_Files);
	mnuFile.add(mitRecent_Workspaces);
	mnuFile.add(mitExit);
	
	mnuBuild.add(mitCompile_Project);
	mnuBuild.add(mitCompile_File);
	mnuBuild.add(mitCompile_Batch);
	mnuBuild.addSeparator();
	mnuBuild.add(mitExecute_Project);
	mnuBuild.add(mitExecute_File);
	mnuBuild.addSeparator();
	mnuBuild.add(mitStart_Debugger);
	mnuBuild.add(mitStep);
	mnuBuild.add(mitStep_Intro);
	mnuBuild.add(mitStep_Out);
	mnuBuild.add(mitContinue);
	mnuBuild.add(mitDebugger);
	mnuBuild.addSeparator();
	mnuBuild.add(mitRuntime_Configuration);
	
	mbar=new JMenuBar();
	mbar.add(mnuFile);
	mbar.add(mnuBuild);
	
	run=new JButton("RUN");
	compile=new JButton("COMPILE");
	
	tb=new JToolBar();
	tb.add(run);
	tb.add(compile);
	
	setJMenuBar(mbar);
	add(tb,"North");
	
	t1=new JTextArea();
	t2=new JTextArea();
	p1=new JPanel(new BorderLayout());
	p1.setPreferredSize(new Dimension(1300,500));
	p2=new JPanel(new BorderLayout());
	p2.add(t2);
	add(p1);
	add(p2,"South");
	
	jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,p1,p2);
	add(jsp);
	
	setVisible(true);
	setExtendedState(MAXIMIZED_BOTH);
	setTitle("JAVA IDE");
}
public static void main(String args[])
{
	new JAVAIDE();
}
public void actionPerformed(ActionEvent ae)
{
	String s1=ae.getActionCommand();
	if(s1.equalsIgnoreCase("new")){
		if(saveflag==true) {
			p1.add(t1);
			t1.setText("");
			setTitle("Untitled ");
			saveflag=true;
		}
		else {
			int ans=JOptionPane.showConfirmDialog(JAVAIDE.this, "Do you wan't to save changes","JAVAIDE",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			if(ans==JOptionPane.YES_OPTION) {
				save();
				p1.add(t1);
				t1.setText("");
				setTitle("Untitled ");
				saveflag=true;
			}
			else if(ans==JOptionPane.NO_OPTION) { 
				t1.setText("");
				setTitle("Untitled ");
				saveflag=true;
			}
		}
	}
	else if(s1.equalsIgnoreCase("open")){
		JFileChooser jfc=new JFileChooser("H:/java");
		FileNameExtensionFilter filter1=new FileNameExtensionFilter("JAVA Files", "java");
		jfc.addChoosableFileFilter(filter1);
		jfc.setFileFilter(filter1);
		int code=jfc.showOpenDialog(this);
		if(code==JFileChooser.APPROVE_OPTION) {
			try {
				currentFile=jfc.getSelectedFile();
				fileName=currentFile.getName();
				filePath=currentFile.getParent();
				FileInputStream fis=new FileInputStream(currentFile);
				int l=(int)currentFile.length();
				byte b[]=new byte[l];
				fis.read(b);
				t1.setText(new String(b));
				setTitle(fileName+" .java");
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			saveflag=true;
		}
		else {
			JOptionPane.showMessageDialog(this, "No File Selected");
		}
		
	}
}
void save() {
	if(currentFile==null) {
		saveas();//if we are saving the file first time
	}
	else {
		//We are saving this file 2nd or 3rd.... time.
		try {
			FileOutputStream fos=new FileOutputStream(currentFile);
			fos.write(t1.getText().getBytes());
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}    	
		setTitle(fileName+" .java");
		saveflag=true;
	}
}
void saveas() {
	JFileChooser jfc=new JFileChooser("c:/javaprog");
	FileNameExtensionFilter filter1=new FileNameExtensionFilter("Text Files", "txt");
	jfc.addChoosableFileFilter(filter1);
	jfc.setFileFilter(filter1);
	int code=jfc.showSaveDialog(this);
	if(code==JFileChooser.APPROVE_OPTION) {
		try {
			currentFile=jfc.getSelectedFile();
			fileName=currentFile.getName();
			filePath=currentFile.getParent();
			FileOutputStream fos=new FileOutputStream(currentFile);
			fos.write(t1.getText().getBytes());
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		setTitle(fileName+" .java");
		saveflag=true;
	}
}
}



