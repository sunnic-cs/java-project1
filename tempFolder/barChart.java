package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class barChart extends JPanel {
    private barChart chart;
    private String title;
    private int maleCount;
    private int femaleCount;
    
    public barChart(String title, int maleCount, int femaleCount) {
        this.title = title;
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int barWidth = 40;
        int barHeight = 10;

        // Set font and color for the chart title
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString(title, 80, 20);

        // Set font and color for the axis labels and values
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.setColor(Color.BLACK);
        g.drawString("Gender", 240, 250);
        g.drawString("Count", 10, 70);
        g.drawString("Male", 80, 250);
        g.drawString("Female", 150, 250);

        g.setColor(Color.BLACK);
        g.drawLine(50, 230, 250, 230);  // x axis
        g.drawLine(50, 230, 50, 70);    // y axis

        g.drawString("Percentage : ",8,270);
        g.drawString("Count : ",38,290);

        double sum = maleCount+femaleCount;
        String mCount = String.format("%d",(maleCount));
        String malePercent = String.format("%.2f%%", (maleCount/sum)*100);
        g.drawString(malePercent,80,270);
        g.drawString(mCount,80,290);
        String fCount = String.format("%d",(femaleCount));
        String femalePercent = String.format("%.2f%%", (femaleCount/sum)*100);
        g.drawString(femalePercent,150,270);
        g.drawString(fCount,150,290);



        // Set color for the bars and draw them
        g.setColor(Color.BLUE);
        g.fillRect(80, 230-maleCount*barHeight, barWidth, maleCount*barHeight);
        g.setColor(Color.PINK);
        g.fillRect(150, 230-femaleCount*barHeight, barWidth, femaleCount*barHeight);
    }
}

