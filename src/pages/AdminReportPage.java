package pages;

import OODJAssignment.Cart;
import OODJAssignment.CartDetails;
import OODJAssignment.Customer;
import OODJAssignment.DataIO;
import static OODJAssignment.DataIO.allCustomer;
import OODJAssignment.Item;
import OODJAssignment.OODMSystem;
import OODJAssignment.Order;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class AdminReportPage implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource()==back) {
            adminRepPage.setVisible(false);
            OODMSystem.adminPage1.getJFrame().setVisible(true);
        }
    }
    
    private final JFrame adminRepPage;
    private final JLabel title;
    private final Button back;

    public AdminReportPage() {
        adminRepPage = new JFrame("Report Page");
        adminRepPage.setSize(1100,900);
        adminRepPage.setLocation(700,400);       
        adminRepPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminRepPage.setLocationRelativeTo(null);
        adminRepPage.setLayout(null);

        title= new JLabel("Report Page");
        title.setBounds(437,20,300,40);
        title.setFont(new Font("Consolas",Font.BOLD,27));
        adminRepPage.add(title);
        
        back = new Button("Back");
        back.setBounds(50,20,80,30);
        back.addActionListener(this);
        adminRepPage.add(back);
        customerChart();
        itemChart();
        ageChart();
        orderChart(); 
    }
    
    
    private void orderChart() {
        // Create a map to store the number of orders for each day
        Map<LocalDate, Integer> ordersPerDay = new HashMap<>();
        for (Order or : DataIO.allOrder) {
            LocalDate orderDate = or.getOrderDate();
            ordersPerDay.merge(orderDate, 1, Integer::sum);
        } 
        // Create a time series collection to hold the data for the line chart
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = new TimeSeries("Orders per day");
        for (Map.Entry<LocalDate, Integer> entry : ordersPerDay.entrySet()) {
            LocalDate date = entry.getKey();
            int orders = entry.getValue();
            Day day = new Day(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            series.add(day, orders);
        }
        dataset.addSeries(series);
        // Create the line chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Orders per day",
            "Date",
            "Orders",
            dataset
        );
        // Customize the chart
        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new java.text.SimpleDateFormat("dd-MMM-yyyy"));
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);
        // Add the chart to a JPanel
        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        chartPanel.setBounds(80,500,370,300);
        adminRepPage.add(chartPanel);
    }
    
    public JFrame getJFrame() {
        return adminRepPage;
    }
    
    private void ageChart() {
        Set<Integer> ageSet = new HashSet<>();
        for(Customer c : DataIO.allCustomer) {
            if(ageSet.contains(c.getAge())) {
                // if age has exist ignore
                continue;
            }
            ageSet.add(c.getAge());
        }
        
        Map<Integer, Integer> ageMap = new HashMap<>();
        for (Integer ageNum : ageSet) {
            int count = 0;
            for (Customer c : DataIO.allCustomer) {
                if (ageNum.equals(c.getAge())) {
                    // This is the same item, so add its value to the total
                    count++;
                }
            }
            // Put the total value for this item into the map
            ageMap.put(ageNum, count);
        }
        
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Integer> entry : ageMap.entrySet()) {
            dataset.addValue(entry.getValue(), "Age", entry.getKey());
        }

        // Create a chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Age Distribution", // Chart title
            "Age", // X-axis label
            "Count", // Y-axis label
            dataset, // Dataset
            PlotOrientation.VERTICAL, // Plot orientation
            true, // Include legend
            true, // Include tooltips
            false // Do not include URLs
        );

        // Customize the chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 0, 255));
        
        // Add the chart to a JPanel
        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(540, 500, 470, 300);

        // Add the JPanel to the adminRepPage frame
        adminRepPage.add(chartPanel);
    }
    
    private void itemChart() {
        // Set to add unique itemNames into the Set Collection (Remove duplication)
        Set<String> itemNames = new HashSet<>();
        for(Item cd : DataIO.allItem) {
            if (itemNames.contains(cd.getName())) {
                // This itemName has already been added, so skip it
                continue;
            }
            itemNames.add(cd.getName());
        }
        
        Map<String, Integer> ItemValMap = new HashMap<>();
        for (String itemName : itemNames) {
            int itemTotal = 0;
            for (Cart c : DataIO.allCart) {
                for (CartDetails cd : c.getOrderList()) {
                    if (itemName.equals(cd.getItem())) {
                        // This is the same item, so add its value to the total
                        itemTotal += cd.getQty();
                    }
                }
            }
            // Put the total value for this item into the map
            ItemValMap.put(itemName, itemTotal);
        }
        
       
        // Create a new DefaultCategoryDataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add data to the dataset
        for(Map.Entry<String,Integer> entry : ItemValMap.entrySet()) {
            String itemName = entry.getKey();
            int itemValue = entry.getValue();
            dataset.addValue(itemValue, "Item", itemName);
        }

        // Create the BarChart
        JFreeChart chart = ChartFactory.createBarChart("Item Distribution", "Item", "Value", dataset, PlotOrientation.VERTICAL, false, true, false);

        // Create the ChartPanel
        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(540, 100, 470, 300);

        // Add the ChartPanel to the frame
        adminRepPage.add(chartPanel);
        
    }
    
    private void customerChart() {
        int mCount = 0, fCount = 0;
        for(Customer c : allCustomer) {
            if(c.getGender().equals("Male")) {
                mCount++;
            } else if (c.getGender().equals("Female")) {
                fCount++;
            }
        }
        
        // Create a dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Male", mCount);
        dataset.setValue("Female", fCount);

        // Create a chart
        JFreeChart chart = ChartFactory.createPieChart(
            "Gender Distribution", // Chart title
            dataset, // Dataset
            true, // Include legend
            true, // Include tooltips
            false // Do not include URLs
        );

        // Customize the chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Male", new Color(0, 0, 255));
        plot.setSectionPaint("Female", new Color(255, 0, 0));
        
        // Add the chart to a JPanel
        JPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(80, 100, 370, 300);
        
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.0%")));

        // Add the JPanel to the adminRepPage frame
        adminRepPage.add(chartPanel);
    }
    
    
    
    
    
}
