package com.aaa.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ZkqMain {

    //@Resource
    //@Autowired

    public static void main(String[] args) throws Exception {
        String name = new String();
        String img = new String();
        int  arr[] =  {1,2,3,4,5};

        //ZkqMenuShowServiceImpl zkqMenuShowServiceImpl = new  ZkqMenuShowServiceImpl();
        File file = new File("C:/img/img.xlsx");
        //System.out.println(file.isFile()+"------"+file.exists());
        if(file.isFile() && file.exists()){
            String exle = file.getName().split("\\.")[1];//获取文件后缀名
            Workbook wb = null;
            InputStream is = new FileInputStream(file);
            if("xls".equals(exle)){
                wb = new HSSFWorkbook(is);
            }else if("xlsx".equals(exle)){
                wb = new XSSFWorkbook(is);
            }else{
                System.out.println("该文件类型不支持解析");
            }
            Sheet sheetAt = wb.getSheetAt(0);//这个是读取第几个工作空间，从0开始
            //System.out.println(wb.getNumberOfSheets()); //这个是一共有几个工作空间 返回一个数之值
            //int firstRowNum = sheetAt.getFirstRowNum()+1;//从第0行开始 ，第一行标题不用读
            int lastRowNum = sheetAt.getLastRowNum();//一共有几行
            for (int i=0 ; i <= lastRowNum; i++) {
                Row row = sheetAt.getRow(i);

                //System.out.println(i);
                if(row!=null){
                    int lastCellNum = row.getLastCellNum();
                    for (int j = 0; j < lastCellNum; j++) {
                        //row.getCell(j)获取第几列
                        Cell cell = row.getCell(j);
                        //System.out.println(cell);
                        if (j==0){
                            name = cell.toString();
                        }else if (j==1){
                            img = cell.toString();
                        }else {
                            continue;
                        }
					/*	if(j==5){
							File fileis = new File(cell.toString());
							InputStream sis = new FileInputStream(fileis);
							System.out.println(cell.toString());
							String[] aa=cell.toString().split("/");
							String name=aa[aa.length-1];
							File fileos = new File("c:"+File.separator+"vs"+File.separator,name);
							OutputStream os = new FileOutputStream(fileos);
							byte[] b = new byte[1024];
							System.out.println(sis.read(b));
							while (sis.read(b)>0) {
								os.write(b, 0, b.length);
							}
							os.close();
							sis.close()-x;
						}*/
                    }
                    int index=(int)(Math.random()*arr.length);
                    int rand = arr[index];
                    System.out.println("('"+name+"','img/"+img+"',"+(i+10)+",0,"+rand+"),");
//                    Map map = new HashMap();
//                    map.put("name",name);
//                    map.put("img","img/"+img);
                    //zkqMenuShowServiceImpl.addOnes(map);
                }else{
                    continue;
                }
            }
        }else{
            System.out.println("该文件不存在！");
        }
    }
}
