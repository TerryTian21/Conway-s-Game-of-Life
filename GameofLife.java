package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameofLife {

	static JButton[][] butArray;
	boolean returnValue = true;
	boolean keepGoing = true;
	JButton stop;
	Timer timer;

	public GameofLife() {

		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Light's Out");
		frame.setSize(1600, 1000);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		butArray = new JButton[60][60];
		JPanel array = new JPanel(new GridLayout(60, 60));

		for (int i = 0; i < 60; i++)
			for (int x = 0; x < 60; x++) {

				JButton button = new JButton();
				butArray[i][x] = button;
				button.setName("" + i + x);
				button.setBackground(Color.GRAY);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						JButton button = (JButton) e.getSource();
						if (button.getBackground() == Color.GRAY)
							button.setBackground(Color.YELLOW);
						else
							button.setBackground(Color.GRAY);
					}
				});
				array.add(butArray[i][x]);
			}

		panel.add(array, BorderLayout.CENTER);

		JPanel header = new JPanel();
		JLabel title = new JLabel("Welcome To Conway's Game of Life");
		title.setFont(new Font("Serif", Font.ITALIC, 40));
		Color blue = new Color(51, 70, 255);
		title.setForeground(blue);
		header.setBackground(Color.GRAY);
		header.setOpaque(true);
		header.setForeground(Color.BLUE);
		header.add(title);
		panel.add(header, BorderLayout.PAGE_START);

		JPanel controls = new JPanel(new GridLayout(1, 3));

		stop = new JButton();
		stop.setText("Stop");

		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				if (!checkStop())
					timer.stop();

				else {
					for (int i = 0; i < 60; i++)
						for (int j = 0; j < 60; j++)
							butArray[i][j].setEnabled(false);

					try {
						nextIteration();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				returnValue = true;

			}
		};
		timer = new Timer(800, taskPerformer);

		JButton start = new JButton();
		start.setText("Start");
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent args0) {

				timer.start();
			}
		});

		JButton next = new JButton();
		next.setText("Next");
		next.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					nextIteration();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		JButton reset = new JButton();
		reset.setText("Reset");
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < 60; i++)
					for (int j = 0; j < 60; j++) {
						butArray[i][j].setEnabled(true);
						butArray[i][j].setBackground(Color.GRAY);
					}

			}

		});

		controls.add(start);
		controls.add(stop);
		controls.add(next);
		controls.add(reset);

		panel.add(controls, BorderLayout.SOUTH);
		frame.setContentPane(panel);
		frame.setVisible(true);

	}

	private static void nextIteration() throws Exception {

		for (int i = 0; i < 60; i++)
			for (int j = 0; j < 60; j++) {

				int counter = 0;

				if (butArray[i][j].getBackground() == Color.YELLOW) {

					if (i - 1 > 0 && j - 1 > 0 && butArray[i - 1][j - 1].getBackground() == Color.YELLOW)
						counter++;
					if (i - 1 > 0 && butArray[i - 1][j].getBackground() == Color.YELLOW)
						counter++;
					if (i - 1 > 0 && j + 1 < 60 && butArray[i - 1][j + 1].getBackground() == Color.YELLOW)
						counter++;
					if (j - 1 > 0 && butArray[i][j - 1].getBackground() == Color.YELLOW)
						counter++;
					if (j + 1 < 60 && butArray[i][j + 1].getBackground() == Color.YELLOW)
						counter++;
					if (i + 1 < 60 && j - 1 > 0 && butArray[i + 1][j - 1].getBackground() == Color.YELLOW)
						counter++;
					if (i + 1 < 60 && butArray[i + 1][j].getBackground() == Color.YELLOW)
						counter++;
					if (i + 1 < 60 && j + 1 < 60 && butArray[i + 1][j + 1].getBackground() == Color.YELLOW)
						counter++;

					if (counter <= 1)
						butArray[i][j].setBackground(Color.GRAY);
					else if (counter >= 4)
						butArray[i][j].setBackground(Color.GRAY);

				}

				else {

					if (i - 1 > 0 && j - 1 > 0 && butArray[i - 1][j - 1].getBackground() == Color.YELLOW)
						counter++;
					if (i - 1 > 0 && butArray[i - 1][j].getBackground() == Color.YELLOW)
						counter++;
					if (i - 1 > 0 && j + 1 < 60 && butArray[i - 1][j + 1].getBackground() == Color.YELLOW)
						counter++;
					if (j - 1 > 0 && butArray[i][j - 1].getBackground() == Color.YELLOW)
						counter++;
					if (j + 1 < 60 && butArray[i][j + 1].getBackground() == Color.YELLOW)
						counter++;
					if (i + 1 < 60 && j - 1 > 0 && butArray[i + 1][j - 1].getBackground() == Color.YELLOW)
						counter++;
					if (i + 1 < 60 && butArray[i + 1][j].getBackground() == Color.YELLOW)
						counter++;
					if (i + 1 < 60 && j + 1 < 60 && butArray[i + 1][j + 1].getBackground() == Color.YELLOW)
						counter++;

					if (counter == 3)
						butArray[i][j].setBackground(Color.yellow);
				}

			}

	}

	public boolean checkStop() {

		stop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				returnValue = false;
				for (int i = 0; i < 60; i++)
					for (int j = 0; j < 60; j++)
						butArray[i][j].setEnabled(true);

			}
		});

		return returnValue;

	}

}
