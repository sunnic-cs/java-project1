package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PieChart extends JPanel  {

    private ArrayList<Integer> values;
    private ArrayList<String> labels;
    private ArrayList<Color> colors;
    
    public PieChart(ArrayList<Integer> values, ArrayList<String> labels, ArrayList<Color> colors) {
        this.values = values;
        this.labels = labels;
        this.colors = colors;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set font and color for the chart title
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        String title = "Item Popularity";
        g.drawString(title, 11, 11);
        
        int total = 0;
        for (int i = 0; i < values.size(); i++) {
            total += values.get(i);
        }

        double angle1 = 0;
        double angle2 = 0;
        g.setFont(new Font("SansSerif", Font.PLAIN, 11));
        g.setColor(Color.BLACK);
        for (int i = 0; i < values.size(); i++) {
            angle2 = angle1 + ((double) values.get(i) / total) * 360.0;

            g2d.setColor(colors.get(i));
            g2d.fill(new Arc2D.Double(20, 20, 190, 190, angle1, angle2 - angle1, Arc2D.PIE));
            g2d.setColor(Color.BLACK);

            double labelAngle = angle1 + (angle2 - angle1) / 2;
            double x = 120 + Math.cos(Math.toRadians(labelAngle)) * 100;
            double y = 120 + Math.sin(Math.toRadians(labelAngle)) * 100;
            g2d.drawString(labels.get(i), (int) x, (int) y);
            double totals = 0;
            for(int d : values) {
                totals = totals+d;
            }
            String val = String.format("%.1f%%",(values.get(i)/totals*100));
            g2d.drawString(val,(int)x ,(int) y+20);

            angle1 = angle2;
        }

        g2d.setColor(Color.WHITE);
        g2d.fill(new Ellipse2D.Double(62, 62, 110, 110));
    }
    
}
