package com.vat.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.vat.bean.ComputingMiddleResultVO;
import com.vat.bean.ComputingResultVO;
import com.vat.service.PDFService;
import com.vat.util.DataUtil;

public class PDFServiceImpl implements PDFService {

    private final static Logger logger = LoggerFactory.getLogger(PDFServiceImpl.class);

    private static final String reportTitle = "增值税报告";

    private static final String platform = "VAT-COMPUTING信息服务中心";

    private String reportNo = null;

    private String computingDate = null;

    private String userAcount = null;
    
    private String privilegeFlag = null;

    private List<ComputingResultVO> computingResultList;
    
    private List<ComputingMiddleResultVO> computingMiddleResultList;

    private static BaseFont baseFont = null;

    public static void main(String[] args) throws Exception {
	// createPDF();
	PDFServiceImpl pdfutil = new PDFServiceImpl();

	// 输出路径
	String outPath = "D://test.pdf";// DataUtil.createTempPath(".pdf");
	String fontPath = "";

	pdfutil.imageWaterMark(pdfutil.createPDF(fontPath, outPath,"Y"), "D://hand.png");
    }

    /**
     * 创建PDF文档
     * 
     * @return
     * @throws Exception
     * @throws docException
     */
    @Override
    public String createPDF(String fontPath, String outPath,String privilegeFlag) throws Exception {
	this.privilegeFlag = privilegeFlag;
	if (baseFont == null) {
	    String fontName = "simhei.ttf";
	    if (fontPath != null) {
		fontPath = fontPath + fontName;
		try {
		    baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		} catch (Exception e) {
		    logger.error("error", e);
		    throw e;
		}
	    } else {
		throw new Exception("font path is null");
	    }

	}

	// 设置纸张
	Rectangle rect = new Rectangle(PageSize.A4);

	// 创建文档实例
	Document doc = new Document(rect);

	// 设置字体样式
	Font textFont = new Font(baseFont, 11, Font.NORMAL); // 正常
	Font boldFont = new Font(baseFont, 11, Font.NORMAL); // 加粗
	Font firsetTitleFont = new Font(baseFont, 22, Font.NORMAL); // 一级标题
	Font secondTitleFont = new Font(baseFont, 15, Font.NORMAL); // 二级标题
	Font underlineFont = new Font(baseFont, 11, Font.UNDERLINE); // 下划线斜体

	// 手指图片
	// Image hand = Image.getInstance("D:\\hand.png");

	// 创建输出流
	FileOutputStream fileOutputStream = new FileOutputStream(new File(outPath));
	PdfWriter.getInstance(doc, fileOutputStream);

	doc.open();
	doc.newPage();

	// 段落
	Paragraph p1 = new Paragraph();
	// 短语
	Phrase ph1 = new Phrase();
	// 块
	Chunk c1 = new Chunk("VAT-COMPUTING", boldFont);
	Chunk c11 = new Chunk("（增值税报告提供机构）", textFont);
	// 将块添加到短语
	ph1.add(c1);
	ph1.add(c11);
	// 将短语添加到段落
	p1.add(ph1);
	// 将段落添加到短语
	doc.add(p1);

	p1 = new Paragraph();
	ph1 = new Phrase();
	Chunk c2 = new Chunk("报告编号：", boldFont);
	Chunk c22 = new Chunk(reportNo, textFont);
	ph1.add(c2);
	ph1.add(c22);
	p1.add(ph1);
	doc.add(p1);

	p1 = new Paragraph(reportTitle, firsetTitleFont);
	p1.setLeading(50);
	p1.setAlignment(Element.ALIGN_CENTER);
	doc.add(p1);

	p1 = new Paragraph();
	p1.setLeading(20);
	p1.setAlignment(Element.ALIGN_CENTER);
	ph1 = new Phrase();
	Chunk c3 = new Chunk("计算时间：", boldFont);
	Chunk c33 = new Chunk(computingDate, textFont);
	Chunk c4 = new Chunk(leftPad("查询人：", 10), boldFont);
	Chunk c44 = new Chunk(userAcount, textFont);
	ph1.add(c3);
	ph1.add(c33);
	ph1.add(c4);
	ph1.add(c44);
	p1.add(ph1);
	doc.add(p1);

	p1 = new Paragraph("报告说明", secondTitleFont);
	p1.setLeading(50);
	p1.setAlignment(Element.ALIGN_CENTER);
	doc.add(p1);

	p1 = new Paragraph(" ");
	p1.setLeading(30);
	doc.add(p1);

	p1 = new Paragraph();
	ph1 = new Phrase();
	Chunk c5 = new Chunk("1.本报告由", textFont);
	Chunk c6 = new Chunk(platform, underlineFont);
	c6.setSkew(0, 30);
	Chunk c7 = new Chunk(" 出具，依据用户数据计算生成，", textFont);
	Chunk c8 = new Chunk(" 不保证其真实性和准确性，但承诺在信息整合、汇总、展示的全过程中保持客观、中立的地位。", textFont);
	ph1.add(c5);
	ph1.add(c6);
	ph1.add(c7);
	ph1.add(c6);
	ph1.add(c8);
	p1.add(ph1);
	doc.add(p1);

	p1 = new Paragraph("2.更多咨询，请联系客服。", textFont);
	doc.add(p1);

	p1 = new Paragraph("信息概况", secondTitleFont);
	p1.setSpacingBefore(30);
	p1.setSpacingAfter(30);
	p1.setAlignment(Element.ALIGN_CENTER);
	doc.add(p1);

	addVatInfo(doc);
	if("Y".equals(this.privilegeFlag)) {
	    addDetailsVatInfo(doc);
	}
	//addDetails2VatInfo(doc);

	doc.close();
	return outPath;
    }

    private void addVatInfo(Document doc) throws Exception {
	// 创建一个有4列的表格
	PdfPTable table = null;

	Font boldFont = new Font(baseFont, 11, Font.NORMAL); // 加粗

	Paragraph p1 = new Paragraph();
	p1.setSpacingBefore(20);
	p1.setSpacingAfter(10);
	Phrase phrase = new Phrase();
	// Chunk c71 = new Chunk(hand, 0, 0);
	Chunk c72 = new Chunk(leftPad("增值税信息", 7), boldFont);
	// ph1.add(c71);
	phrase.add(c72);
	p1.add(phrase);
	doc.add(p1);
	int colNum = 7;
	table = new PdfPTable(colNum);
	table.setTotalWidth(new float[] { 75, 75, 75, 75, 75, 75, 100 }); // 设置列宽
	table.setLockedWidth(true); // 锁定列宽

	table = createCell(table, new String[] { "销售周期", "所属国家", "币种", "含税收入", "净收入", "增值税金额", "计算时间" }, 1, colNum,true);

	String[] dataColArray = null;
	if (CollectionUtils.isNotEmpty(computingResultList)) {
	    for (ComputingResultVO computingResultVO : computingResultList) {

		dataColArray = new String[] { computingResultVO.getPeriod(),
			computingResultVO.getNeedComputingCountry() + " ("
				+ computingResultVO.getNeedComputingCountryCode() + ")",
			computingResultVO.getCurrencyCode(), computingResultVO.getIncludingTaxAmount(),
			computingResultVO.getExcludingTaxAmount(), computingResultVO.getVatAmount(),
			computingResultVO.getCreateDate() };

		table = createCell(table, dataColArray, 1, colNum,false);
	    }
	}

	doc.add(table);
    }

    private void addDetailsVatInfo(Document doc) throws Exception {
	// 创建一个有4列的表格

	Font boldFont = new Font(baseFont, 11, Font.NORMAL); // 加粗

	Paragraph p1 = new Paragraph();
	p1.setSpacingBefore(20);
	p1.setSpacingAfter(10);
	Phrase phrase = new Phrase();
	// Chunk c71 = new Chunk(hand, 0, 0);
	Chunk c72 = new Chunk(leftPad("增值税详细信息", 5), boldFont);
	// ph1.add(c71);
	phrase.add(c72);
	p1.add(phrase);
	doc.add(p1);
	int colNum = 5;
	PdfPTable table = new PdfPTable(colNum);
	table.setTotalWidth(new float[] { 75, 75, 75, 75, 120 }); // 设置列宽
	table.setLockedWidth(true); // 锁定列宽
	 // 其中1为居中对齐，2为右对齐，3为左对齐
        table.setHorizontalAlignment(1);

	table = createCell(table, new String[] { "销售周期", "计算类型", "币种", "含税收入","计算时间" }, 1, colNum,true);

	String[] dataColArray = null;
	if (CollectionUtils.isNotEmpty(computingMiddleResultList)) {
	    for (ComputingMiddleResultVO computingMiddleResultVO : computingMiddleResultList) {

		dataColArray = new String[] { 
			computingMiddleResultVO.getPeriod(),
			computingMiddleResultVO.getComputingType(),
			computingMiddleResultVO.getCurrencyCode(), 
			computingMiddleResultVO.getAmount(),
			computingMiddleResultVO.getCreateDate() 
			};

		table = createDataCell(table, dataColArray, 1);
	    }
	}

	doc.add(table);
    }
    
    
    private void addDetails2VatInfo(Document doc) throws Exception {
	// 创建一个有4列的表格

	Font boldFont = new Font(baseFont, 11, Font.NORMAL); 

	Paragraph p1 = new Paragraph();
	p1.setSpacingBefore(20);
	p1.setSpacingAfter(10);
	Phrase phrase = new Phrase();
	Chunk c72 = new Chunk(leftPad("增值税详细信息2", 5), boldFont);
	phrase.add(c72);
	p1.add(phrase);
	doc.add(p1);
	int colNum = 5;
	PdfPTable table = new PdfPTable(colNum);
	table.setTotalWidth(new float[] { 75, 75, 75, 75, 120 }); // 设置列宽
	table.setLockedWidth(false); // 锁定列宽
	 // 其中1为居中对齐，2为右对齐，3为左对齐
        table.setHorizontalAlignment(1);
        
        //table.setsetBorderColor(new Color(0, 0, 255));
       // table.setPadding(5);
        //table.setSpacing(5);
        PdfPCell cell = new PdfPCell(new Phrase("sss", boldFont));
      
       cell.setColspan(3);
       table.addCell(cell);


	table = createCell(table, new String[] { "销售周期", "计算类型", "币种", "含税收入","计算时间" }, 1, colNum,true);

	String[] dataColArray = null;
	if (CollectionUtils.isNotEmpty(computingMiddleResultList)) {
	    for (ComputingMiddleResultVO computingMiddleResultVO : computingMiddleResultList) {

		dataColArray = new String[] { 
			computingMiddleResultVO.getPeriod(),
			computingMiddleResultVO.getComputingType(),
			computingMiddleResultVO.getCurrencyCode(), 
			computingMiddleResultVO.getAmount(),
			computingMiddleResultVO.getCreateDate() 
			};

		table = createDataCell(table, dataColArray, 1);
	    }
	}

	doc.add(table);
    }
    
    
    
    /**
     * 创建单元格
     * 
     * @param table
     * @param row
     * @param cols
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private PdfPTable createDataCell(PdfPTable table, String[] colValue, int row)
	    throws DocumentException, IOException {

	Font font = new Font(baseFont, 11, Font.NORMAL);
	int cols = colValue.length;

	for (int i = 0; i < row; i++) {

	    for (int j = 0; j < cols; j++) {

		PdfPCell cell = new PdfPCell();

		if (i == 0 && colValue != null) {
		    cell = new PdfPCell(new Phrase(colValue[j], font)); 
		    
		    /*if(colValue[j] != null && colValue[j].equals("销售周期")) {
			cell.setRowspan(2);
		    }*/
		    
		    if (table.getRows().size() == 0) {
			cell.setBorderWidthTop(1);
		    }
		}

		if (row == 1 && cols == 1) { // 只有一行一列
		    cell.setBorderWidthTop(1);
		}

		if (j == 0) {// 设置左边的边框宽度
		    cell.setBorderWidthLeft(1);
		}

		if (j == (cols - 1)) {// 设置右边的边框宽度
		    cell.setBorderWidthRight(1);
		}

		if (i == (row - 1)) {// 设置底部的边框宽度
		    cell.setBorderWidthBottom(1);
		}

		cell.setMinimumHeight(40); // 设置单元格高度
		cell.setUseAscender(true); // 设置可以居中
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		table.addCell(cell);
	    }
	}

	return table;
    }

    /**
     * 创建单元格
     * 
     * @param table
     * @param row
     * @param cols
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private PdfPTable createCell(PdfPTable table, String[] title, int row, int cols,boolean isBackGround)
	    throws DocumentException, IOException {

	Font font = new Font(baseFont, 11, Font.NORMAL);

	for (int i = 0; i < row; i++) {

	    for (int j = 0; j < cols; j++) {

		PdfPCell cell = new PdfPCell();

		if (i == 0 && title != null) {// 设置表头
		    cell = new PdfPCell(new Phrase(title[j], font)); // 这样表头才能居中
		    if (table.getRows().size() == 0) {
			cell.setBorderWidthTop(1);
		    }
		}

		if (row == 1 && cols == 1) { // 只有一行一列
		    cell.setBorderWidthTop(1);
		}

		if (j == 0) {// 设置左边的边框宽度
		    cell.setBorderWidthLeft(1);
		}

		if (j == (cols - 1)) {// 设置右边的边框宽度
		    cell.setBorderWidthRight(1);
		}

		if (i == (row - 1)) {// 设置底部的边框宽度
		    cell.setBorderWidthBottom(1);
		}

		cell.setMinimumHeight(40); // 设置单元格高度
		cell.setUseAscender(true); // 设置可以居中
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		if(isBackGround) {
		 cell.setBackgroundColor(Color.LIGHT_GRAY);
		}

		table.addCell(cell);
	    }
	}

	return table;
    }

    /**
     * 加水印（字符串）
     * 
     * @param inputFile
     *            需要加水印的PDF路径
     * @param outputFile
     *            输出生成PDF的路径
     * @param waterMarkName
     *            水印字符
     */
    public static void stringWaterMark(String inputFile, String waterMarkName) {
	try {
	    String[] spe = DataUtil.separatePath(inputFile);
	    String outputFile = spe[0] + "_WM." + spe[1];

	    PdfReader reader = new PdfReader(inputFile);
	    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

	    // 添加中文字体
	    BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

	    int total = reader.getNumberOfPages() + 1;

	    PdfContentByte under;
	    int j = waterMarkName.length();
	    char c = 0;
	    int rise = 0;
	    // 给每一页加水印
	    for (int i = 1; i < total; i++) {
		rise = 400;
		under = stamper.getUnderContent(i);
		under.beginText();
		under.setFontAndSize(bfChinese, 30);
		under.setTextMatrix(200, 120);
		for (int k = 0; k < j; k++) {
		    under.setTextRise(rise);
		    c = waterMarkName.charAt(k);
		    under.showText(c + "");
		}

		// 添加水印文字
		under.endText();
	    }
	    stamper.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 加水印（图片）
     * 
     * @param inputFile
     *            需要加水印的PDF路径
     * @param outputFile
     *            输出生成PDF的路径
     * @param imageFile
     *            水印图片路径
     */
    @Override
    public void imageWaterMark(String inputFile, String imageFile) {
	try {
	    String[] spe = DataUtil.separatePath(inputFile);
	    String outputFile = spe[0] + "_WM." + spe[1];

	    PdfReader reader = new PdfReader(inputFile);
	    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

	    int total = reader.getNumberOfPages() + 1;

	    Image image = Image.getInstance(imageFile);
	    image.setAbsolutePosition(-100, 0);// 坐标
	    image.scaleAbsolute(800, 1000);// 自定义大小
	    // image.setRotation(-20);//旋转 弧度
	    // image.setRotationDegrees(-45);//旋转 角度
	    // image.scalePercent(50);//依照比例缩放

	    PdfGState gs = new PdfGState();
	    gs.setFillOpacity(0.2f);// 设置透明度为0.2

	    PdfContentByte under;
	    // 给每一页加水印
	    for (int i = 1; i < total; i++) {
		under = stamper.getUnderContent(i);
		under.beginText();
		// 添加水印图片
		under.addImage(image);
		under.setGState(gs);
	    }
	    stamper.close();
	} catch (Exception e) {
	    logger.info("erro", e);
	}
    }

    /**
     * 设置左边距
     * 
     * @param str
     * @param i
     * @return
     */
    public static String leftPad(String str, int i) {
	int addSpaceNo = i - str.length();
	String space = "";
	for (int k = 0; k < addSpaceNo; k++) {
	    space = " " + space;
	}
	;
	String result = space + str;
	return result;
    }

    /**
     * 设置模拟数据
     * 
     * @param list
     * @param num
     */
    public static void add(List<String> list, int num) {
	for (int i = 0; i < num; i++) {
	    list.add("test" + i);
	}
    }

    /**
     * 设置间距
     * 
     * @param tmp
     * @return
     */
    public static String printBlank(int tmp) {
	String space = "";
	for (int m = 0; m < tmp; m++) {
	    space = space + " ";
	}
	return space;
    }

    public String getReportNo() {
	return reportNo;
    }

    public void setReportNo(String reportNo) {
	this.reportNo = reportNo;
    }

    public String getComputingDate() {
	return computingDate;
    }

    public void setComputingDate(String computingDate) {
	this.computingDate = computingDate;
    }

    public String getUserAcount() {
	return userAcount;
    }

    public void setUserAcount(String userAcount) {
	this.userAcount = userAcount;
    }

    public List<ComputingResultVO> getComputingResultList() {
	return computingResultList;
    }

    public void setComputingResultList(List<ComputingResultVO> computingResultList) {
	this.computingResultList = computingResultList;
    }

    public List<ComputingMiddleResultVO> getComputingMiddleResultList() {
        return computingMiddleResultList;
    }

    public void setComputingMiddleResultList(List<ComputingMiddleResultVO> computingMiddleResultList) {
        this.computingMiddleResultList = computingMiddleResultList;
    }

}