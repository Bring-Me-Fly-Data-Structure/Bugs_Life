///* -------------------
// * GraphFormation.java
// * -------------------
// * (C) Copyright 2006, by Object Refinery Limited.
// *
// */
//package mavenproject3;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.NoResultException;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//
//import javax.swing.JPanel;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.SymbolAxis;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.renderer.category.LineAndShapeRenderer;
//import org.jfree.data.category.CategoryDataset;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;
//import org.jfree.util.ShapeUtilities;
//
///**
// * In this line chart, a SymbolAxis is used for the range axis.
// */
//public class GraphFormation extends ApplicationFrame {
//
//    public static boolean flag = false;
//    public static Date date1 = new Date();
//    public static Date date2 = new Date();
//
//    /**
//     * Creates a new demo.
//     *
//     * @param title the frame title.
//     */
//    public GraphFormation(String title) throws ParseException, IOException {
//        super(title);
//        JPanel chartPanel = createDemoPanel();
//        chartPanel.setPreferredSize(new Dimension(500, 270));
//        setContentPane(chartPanel);
//    }
//
//    private static CategoryDataset createStatus() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.addValue(ReportGeneration.numOpen, "", "Open");
//        dataset.addValue(ReportGeneration.numResolved, "", "Resolved");
//        dataset.addValue(ReportGeneration.numClose, "", "Close");
//        dataset.addValue(ReportGeneration.numInProgress, "", "In Progress");
//        return dataset;
//    }
//
//    private static CategoryDataset createTag() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        ArrayList<Long> values = new ArrayList<>();
//        ArrayList<String> keys = new ArrayList<>();
//        ReportGeneration.occurrences.values().forEach(obj -> values.add(obj));
//        ReportGeneration.occurrences.keySet().forEach(obj -> keys.add(obj));
//
//        for (int i = 0; i < ReportGeneration.occurrences.keySet().size(); i++) {
//            dataset.addValue(values.get(i), "", keys.get(i));
//        }
//        return dataset;
//    }
//
//    private static CategoryDataset createDate() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        ArrayList<Long> values = new ArrayList<>();
//        ArrayList<String> keys = new ArrayList<>();
//        ReportGeneration.occurrencesDate.values().forEach(obj -> values.add(obj));
//        ReportGeneration.occurrencesDate.keySet().forEach(obj -> keys.add(obj));
//
//        for (int i = 0; i < ReportGeneration.occurrencesDate.keySet().size(); i++) {
//            dataset.addValue(values.get(i), "", keys.get(i));
//        }
//        return dataset;
//    }
//
//    private static CategoryDataset createDateRange() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        ArrayList<Long> values = new ArrayList<>();
//        ArrayList<String> keys = new ArrayList<>();
//        ReportGeneration.occurrencesDateRange.values().forEach(obj -> values.add(obj));
//        ReportGeneration.occurrencesDateRange.keySet().forEach(obj -> keys.add(obj));
//
//        for (int i = 0; i < ReportGeneration.occurrencesDateRange.keySet().size(); i++) {
//            dataset.addValue(values.get(i), "", keys.get(i));
//        }
//        return dataset;
//    }
//
//    private static JFreeChart createChart1(CategoryDataset dataset) {
//        JFreeChart chart = ChartFactory.createLineChart(
//                "Statistic of Status", // chart title
//                "Status", // domain axis label
//                "Issue count", // range axis label
//                dataset, // data
//                PlotOrientation.VERTICAL, // orientation
//                true, // include legend
//                true, // tooltips
//                false // urls
//        );
//
//        chart.setBackgroundPaint(Color.white);
//
//        CategoryPlot plot = (CategoryPlot) chart.getPlot();
//        plot.setBackgroundPaint(Color.lightGray);
//        plot.setRangeGridlinePaint(Color.white);
//
////        // customise the range axis...
////        SymbolAxis rangeAxis = new SymbolAxis("Issue count", new String[] {"1", "2", 
////                "3", "4", "5", "6"});
////        plot.setRangeAxis(rangeAxis);
//        // customise the renderer...
//        LineAndShapeRenderer renderer
//                = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setSeriesShapesVisible(0, true);
//        renderer.setSeriesShapesVisible(1, false);
//        renderer.setSeriesShapesVisible(2, true);
//        renderer.setSeriesLinesVisible(2, false);
//        renderer.setSeriesShape(2, ShapeUtilities.createDiamond(4.0f));
//        renderer.setDrawOutlines(true);
//        renderer.setUseFillPaint(true);
//        renderer.setFillPaint(Color.white);
//
//        return chart;
//    }
//
//    private static JFreeChart createChart2(CategoryDataset dataset) {
//        JFreeChart chart = ChartFactory.createLineChart(
//                "Statistic of Tag", // chart title
//                "Tag", // domain axis label
//                "Issue count", // range axis label
//                dataset, // data
//                PlotOrientation.VERTICAL, // orientation
//                true, // include legend
//                true, // tooltips
//                false // urls
//        );
//
//        chart.setBackgroundPaint(Color.white);
//
//        CategoryPlot plot = (CategoryPlot) chart.getPlot();
//        plot.setBackgroundPaint(Color.lightGray);
//        plot.setRangeGridlinePaint(Color.white);
//
////        // customise the range axis...
////        SymbolAxis rangeAxis = new SymbolAxis("Issue count", new String[] {"1", "2", 
////                "3", "4", "5", "6"});
////        plot.setRangeAxis(rangeAxis);
//        // customise the renderer...
//        LineAndShapeRenderer renderer
//                = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setSeriesShapesVisible(0, true);
//        renderer.setSeriesShapesVisible(1, false);
//        renderer.setSeriesShapesVisible(2, true);
//        renderer.setSeriesLinesVisible(2, false);
//        renderer.setSeriesShape(2, ShapeUtilities.createDiamond(4.0f));
//        renderer.setDrawOutlines(true);
//        renderer.setUseFillPaint(true);
//        renderer.setFillPaint(Color.white);
//
//        return chart;
//    }
//
//    private static JFreeChart createChart3(CategoryDataset dataset) {
//        JFreeChart chart = ChartFactory.createLineChart(
//                "Issue Frequency", // chart title
//                "Date", // domain axis label
//                "Issue count", // range axis label
//                dataset, // data
//                PlotOrientation.VERTICAL, // orientation
//                true, // include legend
//                true, // tooltips
//                false // urls
//        );
//
//        chart.setBackgroundPaint(Color.white);
//
//        CategoryPlot plot = (CategoryPlot) chart.getPlot();
//        plot.setBackgroundPaint(Color.lightGray);
//        plot.setRangeGridlinePaint(Color.white);
//
//        // customise the range axis...
////        SymbolAxis rangeAxis = new SymbolAxis("Issue count", new int[] {1, 2, 
////                3, 4,5, 6});
////        plot.setRangeAxis(rangeAxis);
//        // customise the renderer...
//        LineAndShapeRenderer renderer
//                = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setSeriesShapesVisible(0, true);
//        renderer.setSeriesShapesVisible(1, false);
//        renderer.setSeriesShapesVisible(2, true);
//        renderer.setSeriesLinesVisible(2, false);
//        renderer.setSeriesShape(2, ShapeUtilities.createDiamond(4.0f));
//        renderer.setDrawOutlines(true);
//        renderer.setUseFillPaint(true);
//        renderer.setFillPaint(Color.white);
//
//        return chart;
//    }
//
//    /**
//     * Creates a panel for the demo (used by SuperDemo.java).
//     *
//     * @return A panel.
//     */
//    public static JPanel createDemoPanel() throws ParseException, IOException {
//        Scanner input = new Scanner(System.in);
////        ReportGeneration.generateReport();
//        while (true) {
//            try {
//                System.out.println("\nEnter '1' to show Statistic of Status Graph\nOr '2' to show Statistic of Tag Graph\n'3' to Issue Frequency\n'4' to show Issue Frequency for Selected Time Range\n'5' to back to admin page");
//                int userInput = input.nextInt();
//                if (userInput == 1) {
//                    JFreeChart chart = createChart1(createStatus());
//                    return new ChartPanel(chart);
//                } else if (userInput == 2) {
//                    JFreeChart chart = createChart2(createTag());
//                    return new ChartPanel(chart);
//                } else if (userInput == 3) {
//                    JFreeChart chart = createChart3(createDate());
//                    return new ChartPanel(chart);
//                } else if (userInput == 4) {
//                    JFreeChart chart = createChart3(createDateRange());
//                    return new ChartPanel(chart);
//                } else if (userInput == 5) {
//                    tester.adminHomepage();
//                }
//
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid value!\n");
//                input.next();
//            }
//        }
//    }
//
//    /**
//     * Starting point for the demonstration application.
//     *
//     * @param args ignored.
//     */
//    public static void ReportGraph() throws ParseException, IOException {
//        GraphFormation demo = new GraphFormation("Statistic Graph");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
//    }
//
//}
