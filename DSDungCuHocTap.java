package DUNGCU;

import SPham.GIAOKHOA;
import SPham.TACGIA;
import SPham.TRUYEN;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//import  DUNGCU.DungCuHocTap;
//import DUNGCU.Gom;
//import DUNGCU.Viet;
//import DUNGCU.Thuoc;

public class DSDungCuHocTap implements CHUCNANG, DOCGHIFILE{
    private static DungCuHocTap[] dsdcht = new DungCuHocTap[200];
    private static int soLuong = 0;
    static Scanner sc = new Scanner(System.in);

  public DSDungCuHocTap() {
    }
  public static DungCuHocTap[] getDSDungCuHocTap() {
        return dsdcht;
    }

   public static int getSoLuong() {
        return soLuong;
    }
    @Override
    public void readFromFile(String tenFile) {
        dsdcht = new DungCuHocTap[100]; 
        soLuong = 0; 
     try {
         FileReader fr = new FileReader("dungcu.txt");
         BufferedReader br = new BufferedReader(fr);
             String line;
            while ((line = br.readLine()) != null) {
            if(line.trim()!=null){
            String txt[] = line.split(",");
            String tenSP = txt[0];
            String NSX = txt[1];
            long giaBan = Long.parseLong(txt[2]);
            long giaNhap = Long.parseLong(txt[3]);
            String maDungCu=txt[4];
            String chucNang=txt[5];
            String loaiDungCu=txt[6];
            if (loaiDungCu.equalsIgnoreCase("Gom")) {
                String loaiGom = txt[7];
                String kichCo = txt[8];
             Gom gom =new Gom(tenSP,NSX,giaBan,giaNhap,maDungCu,chucNang,loaiDungCu,loaiGom,kichCo);
                dsdcht[soLuong] = gom;
                soLuong++;
              
            } else if (loaiDungCu.equalsIgnoreCase("Thuoc")) {
                  String kichCoThuoc=txt[7];
                  String loaiThuoc=txt[8];
                  Thuoc thuoc= new Thuoc(tenSP,NSX,giaBan,giaNhap,maDungCu,chucNang,loaiDungCu,kichCoThuoc,loaiThuoc);
                    dsdcht[soLuong] = thuoc;
                    soLuong++;
                 }
            else if (loaiDungCu.equalsIgnoreCase("Viet")) {
                  String mauSac=txt[7];
                  String loaiViet=txt[8];
                  Viet viet= new Viet(tenSP,NSX,giaBan,giaNhap,maDungCu,chucNang,loaiDungCu,mauSac,loaiViet);
                    dsdcht[soLuong] = viet;
                    soLuong++;
                 }
                        }
            }
         br.close();
         fr.close();
         System.out.println("Đọc file thành công!");
 
     } catch (IOException e) {
         System.out.println("Đọc file thất bại!");
         e.printStackTrace();
}
}
    
 @Override
 public void writeToFile(String tenFile) {
     try {
         FileWriter fw = new FileWriter("dungcu.txt");
         BufferedWriter bw = new BufferedWriter(fw);
         
         for (int i = 0; i < soLuong; i++) {
             DungCuHocTap dc = dsdcht[i];
             String line = dc.getTenSP() + "," + dc.getNSX() + "," + (long)dc.getGiaBan() + "," + (long)dc.getGiaNhap() + "," + dc.getMaDungCu() +  "," + dc.getLoaiDungCu();
             
             if (dc instanceof Thuoc) {
                 Thuoc thuoc = (Thuoc) dc;
                 line += "," + thuoc.getKichCoThuoc() + "," + thuoc.getLoaiThuoc();
             } else if(dc instanceof Viet){
                 Viet viet = (Viet) dc;
                 line += "," + viet.getMauSac() + "," + viet.getLoaiViet();
             }
               else if(dc instanceof Gom){
                 Gom gom = (Gom) dc;
                 line += "," + gom.getKichCo() + "," + gom.getLoaiGom();
             }
             
             bw.write(line);
             bw.newLine();
          
         }
         
         bw.close();
         fw.close();
         System.out.println("Viet vao file thanh cong");
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 @Override
    public void Them() {
        if (soLuong == dsdcht.length) {
            dsdcht = java.util.Arrays.copyOf(dsdcht, dsdcht.length * 2);
        }

        System.out.println("Chon Loai Dung Cu:");
        System.out.println("1. Thuoc");
        System.out.println("2. Viet");
        System.out.println("3. Gom");
        int choice = sc.nextInt();

        DungCuHocTap dc1;
        switch (choice) {
            case 1:
                dc1 = new Thuoc();
                break;
            case 2:
                dc1 = new Viet();
                break;
            case 3:
                dc1 = new Gom();
                break;
            default:
                System.out.println("Lua chon khong hop le.");
                return; 
        }

        if (dc1 != null) {
           
            dc1.input();
            dsdcht[soLuong] = dc1;
            soLuong++;
            System.out.println("Them dung cu thanh cong.");
        }
    }
    @Override
    public void Sua() {
     System.out.println("Nhap ma dung cu can sua: ");
     String maDC = sc.nextLine();
    int index = -1;
    for (int i = 0; i < dsdcht.length; i++) {
        if (dsdcht[i] != null && dsdcht[i].getMaDungCu().equals(maDC)) {
            index = i;
            break;
        }
    }
 if (index != -1) {
        DungCuHocTap dc2 = dsdcht[index];
        if (dc2 instanceof Gom) {
            Gom gom = new Gom();
            gom.input();
            dsdcht[index] = gom;
        } else if (dc2 instanceof Viet) {
           Viet viet = new Viet();
            viet.input();
            dsdcht[index] = viet;
        }
        else if (dc2 instanceof Thuoc) {
           Thuoc thuoc = new Thuoc();
            thuoc.input();
            dsdcht[index] = thuoc;
        }
        System.out.println("Sua Sach thanh cong.");
    } else {
        System.out.println("Khong tim thay Sach co ma " + maDC);
    }

    }
    @Override
    public void Xoa(){
        System.out.println("Nhap vao ma dung cu can xoa: ");
        String maDungCucanxoa=sc.nextLine();
      
        int index=-1;
        
        for (int i=0; i<soLuong; i++){
            if (dsdcht[i].getMaDungCu().equals(maDungCucanxoa)){
                index=i;
                break;
            } else {
            }
        }
        if (index==-1)
            System.out.println("Không tồn tại ma dung cu");
        else {
            for (int i=index; i<soLuong; i++)
                dsdcht[i]=dsdcht[i+1];
            System.out.println("Đã xóa sách có ID: "+maDungCucanxoa);
            soLuong--;
        }
    }
    @Override
    public void timKiem(){
        System.out.println("Tìm kiếm dung cu theo: ");
        System.out.println("1.ma dung cu");
        System.out.println("2.Ten San Pham");
        System.out.println("3.Khoang Gia Ban");
         System.out.println("4.Loai dung cu");
        System.out.println("0.Thoát");
        int luaChon=-1;
          System.out.println("Vui lòng nhập vào lựa chọn: ");
          luaChon= sc.nextInt();
            sc.nextLine();
        while (luaChon>=0){
            switch(luaChon){
                case 1:
                    while(true){
                        System.out.println("Nhap vao ma dung cu: ");
                        String ID=sc.nextLine();
                       
                        boolean check=false;
                        for (int i=0; i<dsdcht.length; i++){
                            if (dsdcht[i].getMaDungCu().equals(ID)){
                                check=true;
                                break;
                            }


                        }
                        if (check==true){
                            for (int i=0; i<dsdcht.length; i++){
                                if (dsdcht[i].getMaDungCu().equals(ID)){
                                    dsdcht[i].output();
                                    System.out.println("--------------------");
                                }
                            }
                            break;
                        }
                        else
                            System.out.println("Ma dung cu không tồn tại.Vui lòng nhập lại.");
                        }
                    
                    break;
                    
                case 2:{
                   while (true) {
					System.out.println("Nhap ten san pham: ");
					String tenDungCu = sc.nextLine().toUpperCase();
					boolean check = false;
					for (int i = 0; i < dsdcht.length;i++) {
						if (dsdcht[i].getTenSP().toUpperCase().compareTo(tenDungCu) == 0) {
							check = true;
							break;
						}
					}
                                                                                                                    if (check == true) {
						for (int i = 0; i < dsdcht.length; i++) {
							if (dsdcht[i].getTenSP().toUpperCase().equals(tenDungCu) == true) {
                                                                                                                                                            dsdcht[i].output();
                                                                                                                                                             System.out.println("--------------------");
								
						}
                                                                                                                                    }
						break;
					}else {
						System.out.println("Ten dung cu khong ton tai!! Vui long nhap lai");
					}
				break;}
                }
                                
                case 3:{
                                                                                                    while (true) {
					System.out.println("Nhap gia tien thap nhat: ");
					  double giaThapNhat=sc.nextDouble();
                                          
                                                                                                            System.out.println("Nhap gia tien cao nhat: ");
					  double giaCaoNhat=sc.nextDouble();
                                          
					
					boolean check = false;
                                        
					for (int i = 0; i <dsdcht.length;i++) {
						if (dsdcht[i].getGiaBan()>giaThapNhat && dsdcht[i].getGiaBan()<giaCaoNhat ){
							check = true;
							break;
						}
					}
					if (check == true) {
						for (int i = 0; i < dsdcht.length; i++) {
							if (dsdcht[i].getGiaBan()>giaThapNhat &&dsdcht[i].getGiaBan()<giaCaoNhat) {
                                                                                                                                                             dsdcht[i].output();
                                                                                                                                                              System.out.println("--------------------");
								
						}
                                                                                                                         }
						break;
					} else {
						System.out.println("Ten dung cu khong ton tai!! Vui long nhap lai");
					}
				break;
                }
                }
                case 4:{
        System.out.println("Chon loai dung cu can tim:");
        System.out.println("1. Thuoc ");
        System.out.println("2. Viet");
        System.out.println("3. Gom");
        int choice = sc.nextInt();

        
        switch (choice) {
            case 1:{
                boolean check = false;
                      for (int i=0; i< dsdcht.length; i++){
                            if (dsdcht[i].getLoaiDungCu().equals("Thuoc")){
                                check=true;
                                break;
                            }
                        }
                if (check == true) {
                     for (int i=0; i<dsdcht.length; i++){
                                if (dsdcht[i].getLoaiDungCu().equals("Thuoc")){
                                          dsdcht[i].output();
                                          System.out.println("--------------------");     
                                  }
                            }
                    }
                     
                    }
                     break;
            
            case 2:{
                      boolean check = false;
                      for (int i=0; i<dsdcht.length; i++){
                            if (dsdcht[i].getLoaiDungCu().equals("Viet")){
                                check=true;
                                break;
                            }
                        }
                   if (check == true) {
                     for (int i=0; i< dsdcht.length; i++){
                                if (dsdcht[i].getLoaiDungCu().equals("Viet")){
                                          dsdcht[i].output();
                                          System.out.println("--------------------");     
                                  }
                     }
                   }
                     break;    

            }
            case 3:{
                      boolean check = false;
                      for (int i=0; i<dsdcht.length; i++){
                            if (dsdcht[i].getLoaiDungCu().equals("Gom")){
                                check=true;
                                break;
                            }
                        }
                   if (check == true) {
                     for (int i=0; i< dsdcht.length; i++){
                                if (dsdcht[i].getLoaiDungCu().equals("Gom")){
                                          dsdcht[i].output();
                                          System.out.println("--------------------");     
                                  }
                     }
                   }
                     break;    

            }
            default:{
                System.out.println("Lua chon khong hop le.");
                return;
        }
        }
                }
            }
        }
    }
    @Override
        public void hienThiDanhSach() {
         for (int i = 0; i < soLuong; i++) {
        DungCuHocTap dcht = dsdcht[i];
        System.out.println("Sách thứ " + (i+1) + ":");
        System.out.println("Tên SP: " + dcht .getTenSP());
        System.out.println("NSX: " + dcht .getNSX());
        System.out.println("Giá Bán: " + dcht .getGiaBan());
        System.out.println("Giá Nhập: " + dcht .getGiaNhap());
        System.out.println("Mã Sách: " + dcht .getMaDungCu());
        System.out.println("Năm XB: " + dcht .getChucNang());
        System.out.println("Loại Sách: " + dcht .getLoaiDungCu());
        if ( dcht  instanceof  Gom) {
            Gom gom = (Gom) dcht;
            System.out.println("Ngôn Ngữ: " + gom.getKichCo());
            System.out.println("Loại Truyện: " + gom.getLoaiGom());
        } else if (dcht instanceof Viet) {
              Viet viet = (Viet) dcht;
            System.out.println("Lớp: " + viet.getMauSac());
            System.out.println("Môn: " + viet.getLoaiViet());
        }
        else if (dcht instanceof Thuoc) {
              Thuoc thuoc = (Thuoc) dcht;
            System.out.println("Lớp: " + thuoc.getKichCoThuoc());
            System.out.println("Môn: " + thuoc.getLoaiThuoc());
        }
        
        System.out.println();
    }
   }

}
       


        
        



