package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.TextAnchor;

import com.guigu.library.back.ui.control.InfoStatisticsJPanel;
import com.guigu.library.model.Borrowing;
import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.RenewService;
import com.guigu.library.service.ReturningService;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.RenewServiceImpl;
import com.guigu.library.service.impl.ReturningServiceImpl;

public class ShowLRSCartogramJFrame extends JFrame implements MouseListener,
		ItemListener {

	ChartPanel chartPanel;
	JPanel backgroundPanel, toolPanel;
	JLabel tool_export;
	InfoStatisticsJPanel parentPanel;
	JFreeChart chart;
	ButtonGroup group;
	JRadioButton barChart, lineChart, pieChart;

	JLabel label_year, label_month;
	JComboBox selectYear, selectMonth;

	int select = 0;
	// ����service
	BorrowingService borrowingService = new BorrowingServiceImpl();
	RenewService renewService = new RenewServiceImpl();
	ReturningService returningService = new ReturningServiceImpl();

	public ShowLRSCartogramJFrame(InfoStatisticsJPanel parentPanel, int select) {
		this.parentPanel = parentPanel;
		this.select = select;
		backgroundPanel = new JPanel(new BorderLayout());

		initToolPanel();
		initChartPanel();

		this.add(backgroundPanel);

		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	/**
	 * ��ʼ���������
	 */

	private void initToolPanel() {
		toolPanel = new JPanel(new BorderLayout());

		JPanel left = new JPanel();
		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("���ݵ���");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������

		group = new ButtonGroup();
		barChart = new JRadioButton("���½軹ͳ��");
		barChart.addMouseListener(this);
		lineChart = new JRadioButton("���Ƚ軹ͳ��");
		lineChart.addMouseListener(this);
		pieChart = new JRadioButton("��Ƚ軹ͳ��");
		pieChart.addMouseListener(this);
		group.add(barChart);
		group.add(lineChart);
		group.add(pieChart);

		left.add(tool_export);
		left.add(barChart);
		left.add(lineChart);
		left.add(pieChart);

		// ���ݵ�ǰ�û�ѡ������ж�
		if (select == 1) {
			barChart.setSelected(true);
		} else if (select == 2) {
			lineChart.setSelected(true);
		} else if (select == 3) {
			pieChart.setSelected(true);
		}

		JPanel right = new JPanel();
		label_year = new JLabel("���");
		selectYear = new JComboBox();
		selectYear.addItem("Ĭ�����");
		for (int i = 1999; i < 2500; i++) {
			selectYear.addItem(i);
		}
		selectYear.addItemListener(this);
		label_month = new JLabel("�·�");
		selectMonth = new JComboBox();
		selectMonth.addItem("Ĭ���·�");
		for (int i = 1; i <= 12; i++) {
			selectMonth.addItem(i);
		}
		selectMonth.addItemListener(this);
		right.add(label_year);
		right.add(selectYear);
		right.add(Box.createHorizontalStrut(10));
		right.add(label_month);
		right.add(selectMonth);

		toolPanel.add(left, BorderLayout.WEST);
		toolPanel.add(right, BorderLayout.EAST);

		backgroundPanel.add(toolPanel, BorderLayout.NORTH);
		backgroundPanel.validate();

	}

	/**
	 * ��ʼ��ͳ��ͼ���
	 */
	private void initChartPanel() {

		chartPanel = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame
		if (barChart.isSelected()) {
			createBarChart();
		} else if (lineChart.isSelected()) {
//			createLineChart(); ������ʾ����ȷ
			createLineChart2();
		} else if (pieChart.isSelected()) {
			createPieChart();
		}

		backgroundPanel.add(chartPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ���½軹ͳ�ƣ�����ͳ��ͼ
	 */
	public void createBarChart() {
		CategoryDataset dataset = getBarDataSet();
		chart = ChartFactory.createBarChart3D("���½軹ͳ��", // ͼ�����
				"����/����/�黹", // Ŀ¼�����ʾ��ǩ
				"������Ŀ", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);
		// �����￪ʼ
		CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������
		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������

		// ������ʾ����ֵ
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		// ��ʾ��Ŀ��ǩ
		renderer.setBaseItemLabelsVisible(true);
		// ������Ŀ��ǩ������,��JFreeChart1.0.6֮ǰ����ͨ��renderer.setItemLabelGenerator(CategoryItemLabelGenerator
		// generator)����ʵ�֣����ǴӰ汾1.0.6��ʼ�����淽������
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// ������Ŀ��ǩ��ʾ��λ��,outline��ʾ����Ŀ������,baseline_center��ʾ���ڻ����Ҿ���
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
	}

	public CategoryDataset getBarDataSet() {
		// �����ݿ��л�ȡ��Ϣ����ͳ�ƣ����㵱ǰָ���·ݵ�ͼ��������
		// ��ȡ��ǰָ������ݡ��·�
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);// ��ȡ���
		int month = date.get(Calendar.MONTH) + 1;// ��ȡ�·� ��Ҫ+1
		// ���û��ָ������ݡ��·���Ĭ���ǵ�ǰ��ݡ��·�
		if (!selectYear.getSelectedItem().equals("Ĭ�����")) {
			year = Integer.valueOf(selectYear.getSelectedItem() + "");
		}
		if (!selectMonth.getSelectedItem().equals("Ĭ���·�")) {
			month = Integer.valueOf(selectMonth.getSelectedItem() + "");
		}
		// ��ȡ���ݼ�
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// ��ȡ��ǰ�·����е�ͼ����ġ����衢�黹��¼����
		String dateStr;
		if (month >= 1 && month <= 9) {
			dateStr = year + "-0" + month + "-01";
		} else {
			dateStr = year + "-" + month + "-01";
		}
		String start_time = dateStr;
		String end_time = this.getLastDayOfMonth(year, month);
		try {
			List<Borrowing> list_borrow = borrowingService.findBorrowingByTime(
					start_time, end_time);
			List<Renew> list_renew = renewService.findRenewByTime(start_time,
					end_time);
			List<Returning> list_return = returningService.findReturningByTime(
					start_time, end_time);

			dataset.addValue(list_borrow.size(), "�ѽ��", "������");
			dataset.addValue(list_renew.size(), "����", "������");
			dataset.addValue(list_return.size(), "�ѹ黹", "�黹��");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	/**
	 * ���Ƚ軹ͳ�ƣ�����ͳ��ͼ
	 */
	public void createLineChart() {
		// ��ȡ���ݼ�
		XYDataset xydataset = getLineDataset();
		chart = ChartFactory.createTimeSeriesChart("���Ƚ軹ͳ��", "����", "�۸�",
				xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) chart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
		chartPanel = new ChartPanel(chart, true);
		dateaxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ˮƽ�ײ�����
		dateaxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ��ֱ����
		ValueAxis rangeAxis = xyplot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
	}

	public XYDataset getLineDataset() {
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		try {
			// �����ݿ��л�ȡ��Ϣ����ͳ�ƣ����㵱ǰָ���·ݵ�ͼ��������
			// ��ȡ��ǰָ������ݡ��·�
			Calendar date = Calendar.getInstance();
			int year = date.get(Calendar.YEAR);// ��ȡ���
			// ���û��ָ������ݡ��·���Ĭ���ǵ�ǰ��ݡ��·�
			if (!selectYear.getSelectedItem().equals("Ĭ�����")) {
				year = Integer.valueOf(selectYear.getSelectedItem() + "");
			}
			String start_time;
			String end_time;
			List<Borrowing> list_borrow;
			List<Renew> list_renew;
			List<Returning> list_return;

			TimeSeries timeseries1 = new TimeSeries("������",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries2 = new TimeSeries("������",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries3 = new TimeSeries("�黹��",
					org.jfree.data.time.Month.class);
			// ����
			start_time = year + "-01-01";
			end_time = this.getLastDayOfMonth(year, 3);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(3, year), list_borrow.size());
			timeseries2.add(new Month(3, year), list_renew.size());
			timeseries3.add(new Month(3, year), list_return.size());

			// �ļ�
			start_time = year + "-04-01";
			end_time = this.getLastDayOfMonth(year, 6);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(6, year), list_borrow.size());
			timeseries2.add(new Month(6, year), list_renew.size());
			timeseries3.add(new Month(6, year), list_return.size());

			// �＾
			start_time = year + "-07-01";
			end_time = this.getLastDayOfMonth(year, 9);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(9, year), list_borrow.size());
			timeseries2.add(new Month(9, year), list_renew.size());
			timeseries3.add(new Month(9, year), list_return.size());

			// ����
			start_time = year + "-10-01";
			end_time = this.getLastDayOfMonth(year, 12);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			timeseries1.add(new Month(12, year), list_borrow.size());
			timeseries2.add(new Month(12, year), list_renew.size());
			timeseries3.add(new Month(12, year), list_return.size());

			timeseriescollection.addSeries(timeseries1);
			timeseriescollection.addSeries(timeseries2);
			timeseriescollection.addSeries(timeseries3);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return timeseriescollection;
	}

	/**
	 * ��ʷ�軹ͳ�ƣ���״ͼ
	 */
	public void createPieChart() {

		DefaultPieDataset data = getPieDataSet();
		chart = ChartFactory.createPieChart3D("��Ƚ���/�黹ͳ��", data, true, false,
				false);
		// ���ðٷֱ�
		PiePlot pieplot = (PiePlot) chart.getPlot();

		// ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
		pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
		pieplot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})"));

		// û�����ݵ�ʱ����ʾ������
		pieplot.setNoDataMessage("��������ʾ");
		pieplot.setNoDataMessageFont(new Font("�����п�", Font.BOLD, 32));
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
		pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ
		chartPanel = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
		PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
		piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));

	}

	private DefaultPieDataset getPieDataSet() {
		// �����ݿ��л�ȡ���ݣ�ͳ�����е���֧�����--�ܵ���֧
		// ��ȡ��ǰָ������ݡ��·�
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);// ��ȡ���
		// ���û��ָ������ݡ��·���Ĭ���ǵ�ǰ��ݡ��·�
		if (!selectYear.getSelectedItem().equals("Ĭ�����")) {
			year = Integer.valueOf(selectYear.getSelectedItem() + "");
		}

		// ��ȡ���ݼ�
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// ��ȡ��ǰ�·����е�ͼ����ġ����衢�黹��¼����
		String start_time = year + "-01-01";
		String end_time = this.getLastDayOfMonth(year, 12);
		DefaultPieDataset dpd = new DefaultPieDataset();
		// ��ȡ���е����۶�����¼
		try {
			List<Borrowing> list_borrow = borrowingService.findBorrowingByTime(
					start_time, end_time);
			List<Returning> list_return = returningService.findReturningByTime(
					start_time, end_time);
			dpd.setValue("�ѽ��", list_borrow.size());
			dpd.setValue("�ѹ黹", list_return.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dpd;
	}

	/**
	 * ���ͼ������ΪͼƬ��������ָ���ĸ�ʽ
	 */
	private static int writeChartAsImage(JFreeChart chart) {
		// �����ļ�ѡ���
		JFileChooser chooser = new JFileChooser();
		// ��׺��������
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"ͼƬ�ļ�(*.jpg)", "jpg");
		chooser.setFileFilter(filter);
		// ����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��
		int option = chooser.showSaveDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) { // �����û�ѡ���˱���
			File file = chooser.getSelectedFile();
			String fname = chooser.getName(file); // ���ļ���������л�ȡ�ļ���
			// �����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺
			FileOutputStream FOut = null;
			if (fname.indexOf(".jpg") == -1) {
				file = new File(chooser.getCurrentDirectory(), fname + ".jpg");
			}
			try {
				FOut = new FileOutputStream(file);
				ChartUtilities.writeChartAsJPEG(FOut, 1, chart, 400, 300, null);
				return 1;
			} catch (IOException e) {
				System.err.println("IO�쳣");
				e.printStackTrace();
				return -1;
			} finally {
				try {
					FOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_export) {
			// Ĭ�ϵ�������ͼƬ��d��
			int result = this.writeChartAsImage(chart);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "�����ѵ�����ָ��λ�ã�");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "��Ǹ�����ݵ���ʧ�ܣ�����һ��ɣ�");
			} else {
				JOptionPane.showMessageDialog(null, "�û�ȡ���˲�����");
			}
		} else if (e.getSource() == barChart) {
			// ��ʾ����ͳ��ͼ
			backgroundPanel.remove(chartPanel);
			initChartPanel();
		} else if (e.getSource() == lineChart) {
			backgroundPanel.remove(chartPanel);
			initChartPanel();
		} else if (e.getSource() == pieChart) {
			backgroundPanel.remove(chartPanel);
			initChartPanel();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * ��ȡĳ��ĳ�µ����һ��
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// �������
		cal.set(Calendar.YEAR, year);
		// �����·�
		cal.set(Calendar.MONTH, month - 1);
		// ��ȡĳ���������
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// �����������·ݵ��������
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// ��ʽ������
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());

		return lastDayOfMonth;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.ITEM_STATE_CHANGED) {
			backgroundPanel.remove(chartPanel);
			initChartPanel();
		}
	}

	/**
	 * ���Ƚ軹ͳ�ƣ�����ͳ��ͼ ------ ��ʽ2
	 */
	public void createLineChart2() {
		// ��ȡ���ݼ�
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("����", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("����", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = getLineDataset2();
		chart = ChartFactory.createLineChart("����ͼ",// ͼ����
				"���",// ������
				"����",// ������
				mDataset,// ���ݼ�
				PlotOrientation.VERTICAL, true, // ��ʾͼ��
				true, // ���ñ�׼������
				false);// �Ƿ����ɳ�����

		CategoryPlot mPlot = (CategoryPlot) chart.getPlot();
		mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		mPlot.setRangeGridlinePaint(Color.BLUE);// �����ײ�������
		mPlot.setOutlinePaint(Color.RED);// �߽���
	}

	public CategoryDataset getLineDataset2() {
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		try {
			// �����ݿ��л�ȡ��Ϣ����ͳ�ƣ����㵱ǰָ���·ݵ�ͼ��������
			// ��ȡ��ǰָ������ݡ��·�
			Calendar date = Calendar.getInstance();
			int year = date.get(Calendar.YEAR);// ��ȡ���
			// ���û��ָ������ݡ��·���Ĭ���ǵ�ǰ��ݡ��·�
			if (!selectYear.getSelectedItem().equals("Ĭ�����")) {
				year = Integer.valueOf(selectYear.getSelectedItem() + "");
			}
			String start_time;
			String end_time;
			List<Borrowing> list_borrow;
			List<Renew> list_renew;
			List<Returning> list_return;

			TimeSeries timeseries1 = new TimeSeries("������",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries2 = new TimeSeries("������",
					org.jfree.data.time.Month.class);

			TimeSeries timeseries3 = new TimeSeries("�黹��",
					org.jfree.data.time.Month.class);
			// ����
			start_time = year + "-01-01";
			end_time = this.getLastDayOfMonth(year, 3);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "������", "����");
			mDataset.addValue(list_renew.size(), "������", "����");
			mDataset.addValue(list_return.size(), "�黹��", "����");

			// �ļ�
			start_time = year + "-04-01";
			end_time = this.getLastDayOfMonth(year, 6);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "������", "�ļ�");
			mDataset.addValue(list_renew.size(), "������", "�ļ�");
			mDataset.addValue(list_return.size(), "�黹��", "�ļ�");

			// �＾
			start_time = year + "-07-01";
			end_time = this.getLastDayOfMonth(year, 9);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "������", "�＾");
			mDataset.addValue(list_renew.size(), "������", "�＾");
			mDataset.addValue(list_return.size(), "�黹��", "�＾");

			// ����
			start_time = year + "-10-01";
			end_time = this.getLastDayOfMonth(year, 12);
			list_borrow = borrowingService.findBorrowingByTime(start_time,
					end_time);
			list_renew = renewService.findRenewByTime(start_time, end_time);
			list_return = returningService.findReturningByTime(start_time,
					end_time);
			mDataset.addValue(list_borrow.size(), "������", "����");
			mDataset.addValue(list_renew.size(), "������", "����");
			mDataset.addValue(list_return.size(), "�黹��", "����");

		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return mDataset;
	}

}