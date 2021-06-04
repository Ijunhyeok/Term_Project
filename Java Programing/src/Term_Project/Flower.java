package Term_Project;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItem;

public class Flower {
	private static final ArrayList<Flower> Flowerlist = null;
	private String flowerKR;
	private String flowerEN;
	private String flowerLa;
	
	public String getFlowerKR() {
		return flowerKR;
	}

	public void setFlowerKR(String flowerKR) {
		this.flowerKR = flowerKR;
	}

	public String getFlowerEN() {
		return flowerEN;
	}

	public void setFlowerEN(String flowerEN) {
		this.flowerEN = flowerEN;
	}

	public String getFlowerLa() {
		return flowerLa;
	}

	public void setFlowerLa(String flowerLa) {
		this.flowerLa = flowerLa;
	}
	
	@Override
	public String toString() {
		return "Flower [flowerKR=" + flowerKR + ", flowerEN=" + flowerEN + ", flowerLa=" + flowerLa + "]";
	}
	
	public ArrayList<Flower> readexcel(){
		ArrayList<Flower> list = new ArrayList<Flower>();
		
		try {
			FileInputStream fis = new FileInputStream(new File("C:/Users/user/Desktop/Flower.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			XSSFSheet curSheet;
            XSSFCell curCell;
            XSSFRow curRow;
            
            int sheetNumber = workbook.getNumberOfSheets();
            
            while (sheetNumber != 0) {
                sheetNumber--;
 
                curSheet = workbook.getSheetAt(sheetNumber);
                int row = curSheet.getPhysicalNumberOfRows();
                
                for (int i = 1; i < row; i++) {
                    Flower vo = new Flower();
                    curRow = curSheet.getRow(i);
                    
                    vo.setFlowerKR(String.valueOf(curRow.getCell(0)));
                    vo.setFlowerEN(String.valueOf(curRow.getCell(1)));
                    vo.setFlowerLa(String.valueOf(curRow.getCell(2)));
                    
                    Flowerlist.add(vo);
                }
            }
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}return Flowerlist;
	}
	
	public void WriteExcel(ArrayList<Flower> list) {
		 
        String path = "C:/Users/user/Desktop/Flower.xlsx";
        try {
            File file = new File(path);
            FileOutputStream fileout = new FileOutputStream(file); 
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            
            XSSFRow curRow;
            
            int row = list.size();
            for (int i = 0; i < row; i++) {
                curRow = sheet.createRow(i);
                curRow.createCell(0).setCellValue(list.get(i).getFlowerKR());
                curRow.createCell(1).setCellValue(list.get(i).getFlowerEN());
                curRow.createCell(2).setCellValue(list.get(i).getFlowerLa());
            }
            workbook.write(fileout);
            fileout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		Flower transfer = new Flower();
        ArrayList<Flower> list = new ArrayList<Flower>();
        list = transfer.readexcel();
 
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i).toString());
        }
	}
		
}