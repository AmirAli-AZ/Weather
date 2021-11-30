package graphics;

import java.awt.*;
import javax.swing.*;

public class panelGraphics extends JPanel {
	/** Stroke size. it is recommended to set it to 1 for better view */
	private int strokeSize = 1;
	/** Color of shadow */
	private Color shadowColor = Color.black;
	/** Sets if it drops shadow */
	private boolean shady = true;
	/** Sets if it has an High Quality view */
	private boolean highQuality = true;
	/** Double values for Horizontal and Vertical radius of corner arcs */
	private Dimension arcs = new Dimension(20, 20);
	/** Distance between shadow border and opaque panel border */
	private int shadowGap = 4;
	/** The offset of shadow. */
	private int shadowOffset = 3;
	/** The transparency value of shadow. ( 0 - 255) */
	private int shadowAlpha = 90;

	public panelGraphics(boolean shadow) {
		super();
		setOpaque(false);
		this.shady = shadow;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int shadowGap = this.shadowGap;
		Color shadowColorA = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(),
				shadowAlpha);
		Graphics2D graphics = (Graphics2D) g;

		// Sets antialiasing if HQ.
		if (highQuality) {
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}

		// Draws shadow borders if any.
		if (shady) {
			graphics.setColor(shadowColorA);
			graphics.fillRoundRect(shadowOffset, // X position
					shadowOffset, // Y position
					width - strokeSize - shadowOffset, // width
					height - strokeSize - shadowOffset, // height
					arcs.width, arcs.height);// arc Dimension
		} else {
			shadowGap = 1;
		}

		// Draws the rounded opaque panel with borders.
		graphics.setColor(getBackground());
		graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);
		graphics.setColor(getForeground());
		graphics.setStroke(new BasicStroke(strokeSize));
		graphics.drawRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);

		// Sets strokes to default, is better.
		graphics.setStroke(new BasicStroke());
	}
}
