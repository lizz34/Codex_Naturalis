/**
 * 
 */
package grafica;

/**
 * 
 */
import javax.swing.*;
import java.awt.*;

public class Matrice2DGraphics extends JPanel {

	private static final long serialVersionUID = 1L;

	private Cella[][] matrix = {
			{ new Cella("FARFALLA", Color.GREEN), new Cella("", Color.RED), new Cella("BOCCETTA", Color.RED) },
			{ new Cella("", Color.RED), new Cella("", Color.RED), new Cella("", Color.RED) },
			{ new Cella("", Color.RED), new Cella("", Color.RED), new Cella("FOGLIA", Color.BLUE) } };

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int cellSize = 75;
		int startX = 50;
		int startY = 50;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				Cella cella = matrix[i][j];
				// g.setColor(Color.RED);
				g.setColor(cella.getColore());
				g.drawRect(startX + j * cellSize, startY + i * cellSize, cellSize, cellSize);
				g.drawString(String.valueOf(cella.getTesto()), startX + j * cellSize + cellSize / 10,
						startY + i * cellSize + cellSize / 2);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Matrice 2D Graphics");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Matrice2DGraphics());
		frame.setSize(600, 500);
		frame.setVisible(true);
	}
}
