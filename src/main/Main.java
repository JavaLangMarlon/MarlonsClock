package main;

import javax.swing.*;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.*;
import java.util.*;

public class Main {
	
	static Date d1;
	static Date d2;
	static ResourceBundle bundle;
	static final String src = "props.xyz";
	static DateFormat df;
	
	public static void main(String... args) {
		bundle = ResourceBundle.getBundle(src, Locale.ENGLISH);
		df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, bundle.getLocale());		
		
		JFrame frame = new JFrame("Uhrzeit");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);

		JLabel hello, stop1, stop2, difference, settings;
		JLabel clock = new JLabel(df.format(new Date()));
		JButton exit, stopper1, stopper2, langSetter;
		

			
			
			hello = new JLabel(bundle.getString("hello"));
			difference = new JLabel("Differenz: none");
			
			exit = new JButton(bundle.getString("exit"));
			exit.addActionListener( evnt -> System.exit(0));
			
			stop1 = new JLabel("none");
			stop2 = new JLabel("none");
			
			JButton resetter1 = new JButton(bundle.getString("reset"));
			resetter1.addActionListener(evnt -> { d1 = null;
												  stop1.setText("none");
			});
			stopper1 = new JButton(bundle.getString("stop1"));
			stopper1.addActionListener(evnt -> { d1 = new Date();
												 stop1.setText(df.format(d1)); 
												 if (stop2.getText() != "none")
													 difference.setText("Differenz: " + Duration.between(d1.toInstant(), d2.toInstant()));
			});
			JButton resetter2 = new JButton(bundle.getString("reset"));
			resetter2.addActionListener(evnt -> { d2 = null;
												  stop2.setText("none");
			});
			stopper2 = new JButton(bundle.getString("stop2"));
		    stopper2.addActionListener(evnt -> { d2 = new Date();
												 stop2.setText(df.format(d2)); 
												 if (stop2.getText() != "none")
													 difference.setText("Differenz: " + Duration.between(d1.toInstant(), d2.toInstant())
													 		   .toString()
													 		   .replace("P", "") 
													 		   .replace("T", ""));
		    });
		    
			settings = new JLabel(bundle.getString("lang"));
			langSetter = new JButton(bundle.getString("langB"));
			langSetter.addActionListener(evnt -> { 
				if (bundle.getLocale() == Locale.GERMAN) 
					bundle = ResourceBundle.getBundle(src, Locale.ENGLISH);
				else 
					bundle = ResourceBundle.getBundle(src, Locale.GERMAN);
				hello.setText(bundle.getString("hello"));
				exit.setText(bundle.getString("exit"));
				stopper1.setText(bundle.getString("stop1"));
				stopper2.setText(bundle.getString("stop2"));
				langSetter.setText(bundle.getString("langB"));
				settings.setText(bundle.getString("lang"));
				resetter1.setText(bundle.getString("reset"));
				resetter2.setText(bundle.getString("reset"));
				df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.LONG, bundle.getLocale());
				
			});


		
			
		frame.setLayout(new GridLayout(0, 1));
		JPanel helloJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		helloJP.add(hello);
		frame.add(helloJP);
		JPanel change = new JPanel(new FlowLayout(FlowLayout.CENTER));
		change.add(settings);
		change.add(langSetter);
		frame.add(change);
		JPanel clockJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		clockJP.add(clock);
		frame.add(clockJP);
		JPanel time1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		time1.add(stop1);
		time1.add(stopper1);
		time1.add(resetter1);
		frame.add(time1);
		JPanel time2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		time2.add(stop2);
		time2.add(stopper2);
		time2.add(resetter2);
		frame.add(time2);
		
		JPanel difJP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		difJP.add(difference);
		frame.add(difJP);
		frame.add(exit);
		
		frame.setVisible(true);

		while (true) 
			clock.setText(df.format(new Date()));

		
	}
}
