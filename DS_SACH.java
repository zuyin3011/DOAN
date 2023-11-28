package SPham;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
//import SPham.SANPHAM;
//import SPham.SACH;
//import SPham.TRUYEN;
//import SPham.GIAOKHOA;
//import SPham.TACGIA;

//import QLPhieuNhap.ChiTietPhieuNhap;
public class DS_SACH implements DOCGHIFILE , CHUCNANG {
    private static int soLuong = 0;
    static Scanner sc = new Scanner(System.in);
   private static final int max_sach = 100; 
    private SACH[] dssach;
    public DS_SACH() {
       dssach=new SACH[max_sach];
       soLuong=0;
    }

    public SACH[] getDS_Sach() {
        return dssach;
    }

    public static int getSoLuong() {
        return soLuong;
    }
    
  @Override

public void readFromFile(String tenFile) {
     dssach = new SACH[100]; 
     soLuong = 0; 
    try {
        FileReader fr = new FileReader("SanPham.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
            while ((line = br.readLine()) != null) {
            if(line.trim()!=null){
            String txt[] = line.split(",");
            String tenSP = txt[0];
            String NSX = txt[1];
            long giaBan = Long.parseLong(txt[2]);
            long giaNhap = Long.parseLong(txt[3]);
            String maSach = txt[4];
            TACGIA tacGia = new TACGIA();
            tacGia.setTenTG(txt[5]);
            tacGia.setQueQuan(txt[6]);
            int namXB = Integer.parseInt(txt[7]);
            String loaiSach = txt[8];
          
            if (loaiSach.equalsIgnoreCase("Truyen")) {
                String ngonNgu = txt[9];
                String loaiTruyen = txt[10];
              TRUYEN truyen =new TRUYEN(tenSP,NSX,giaBan,giaNhap,maSach,tacGia,namXB,loaiSach,ngonNgu,loaiTruyen);
                dssach[soLuong] = truyen;
                soLuong++;
              
            } else if (loaiSach.equalsIgnoreCase("giao khoa")) {
                int lop = Integer.parseInt(txt[9]);
                String mon = txt[10];
              GIAOKHOA giaoKhoa= new GIAOKHOA(tenSP,NSX,giaBan,giaNhap,maSach,tacGia,namXB,loaiSach,lop,mon);
                  dssach[soLuong] = giaoKhoa;
            soLuong++;
            }
            }
         
        }
        br.close();
        fr.close();
        System.out.println("Doc file thanh cong");
    } catch (Exception e) {
        e.printStackTrace();
    }
}




    @Override
public void writeToFile(String tenFile) {
    try {
        FileWriter fw = new FileWriter("SanPham.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < soLuong; i++) {
            SACH sach = dssach[i];
            String line = sach.getTenSP() + "," + sach.getNSX() + "," + (long)sach.getGiaBan() + "," +(long) sach.getGiaNhap() + "," + sach.getMaSach() + "," + sach.getTacGia().getTenTG() + "," + sach.getTacGia().getQueQuan() + "," + sach.getNamXB() + "," + sach.getLoaiSach();
            if (sach instanceof TRUYEN) {
                TRUYEN truyen = (TRUYEN) sach;
                line += "," + truyen.getNgonNgu() + "," + truyen.getLoaiTruyen();
            } else if (sach instanceof GIAOKHOA) {
                GIAOKHOA giaoKhoa = (GIAOKHOA) sach;
                line += "," + giaoKhoa.getLop() + "," + giaoKhoa.getMon();
            }
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        fw.close();
        System.out.println("Viet vao file thanh cong");
    } catch (Exception e) {
        e.printStackTrace();
    }
}



     @Override
    public void Them() {
        if (soLuong == dssach.length) {
            dssach = java.util.Arrays.copyOf(dssach, dssach.length * 2);
        }

        System.out.println("Chon Loai Sach:");
        System.out.println("1. Truyen");
        System.out.println("2. Giao khoa");
        int choice = sc.nextInt();

        SACH sach1;
        TACGIA tacGia=new TACGIA();
        switch (choice) {
            case 1:
                sach1 = new TRUYEN();
                break;
            case 2:
                sach1 = new GIAOKHOA();
                break;
            default:
                System.out.println("Lua chon khong hop le.");
                return; 
        }

        if (sach1 != null) {
           
            sach1.input();
            tacGia.input1();
            sach1.setTacGia(tacGia);
            dssach[soLuong] = sach1;
            soLuong++;
            System.out.println("Them Sach thanh cong.");
        }
    }
    
 @Override

   public void Sua() {
    System.out.println("Nhap ma Sach can sua: ");
    String maSach = sc.nextLine();
    TACGIA tacGia=new TACGIA();
    int index = -1;
    for (int i = 0; i < dssach.length; i++) {
        if (dssach[i] != null && dssach[i].getMaSach().equals(maSach)) {
            index = i;
            break;
        }
    }
    if (index != -1) {
        SACH sach2 = dssach[index];
        if (sach2 instanceof TRUYEN) {
            TRUYEN truyen = new TRUYEN();
            truyen.input();
            tacGia.input1();
            truyen.setTacGia(tacGia);
            dssach[index] = truyen;
        } else if (sach2 instanceof GIAOKHOA) {
            GIAOKHOA giaoKhoa = new GIAOKHOA();
            giaoKhoa.input();
            tacGia.input1();
            giaoKhoa.setTacGia(tacGia);
            dssach[index] = giaoKhoa;
        }
        System.out.println("Sua Sach thanh cong.");
    } else {
        System.out.println("Khong tim thay Sach co ma " + maSach);
    }
}




         @Override
public void Xoa() {
  System.out.println("Nhap vao Ma sách cần xóa: ");
        String IDxoa=sc.nextLine();
        
        int index=-1;
        for (int i=0; i<soLuong; i++){
            if (dssach[i].getMaSach().equals(IDxoa)){
                index=i;
                break;
            }
        }
        if (index==-1)
            System.out.println("Không tồn tại ID sách này.");
        else {
            for (int i=index; i<soLuong; i++)
                dssach[i]=dssach[i+1];
            System.out.println("Đã xóa sách có ID: "+IDxoa);
            soLuong--;
        }
}

     @Override
    public void timKiem(){
        System.out.println("Tìm kiếm San Pham theo: ");
        System.out.println("1.ma san pham");
        System.out.println("2.Ten San Pham");
        System.out.println("3.Khoang Gia Ban");
         System.out.println("4.Loai Sach");
        System.out.println("0.Thoát");
        int luaChon=-1;
             String choose = sc.nextLine();
            luaChon = Integer.parseInt(choose);
        while (luaChon>=0){
            
            switch(luaChon){
                case 1:
   while(true){
    System.out.println("Nhap vao ma Sach: ");
    String ID=sc.nextLine();
   boolean check=false;
    for (int i=0; i<dssach.length; i++){
        if (dssach[i] != null && dssach[i].getMaSach().equals(ID)){
            check=true;
            break;
        }
    }
    if (check==true){
        for (int i=0; i<dssach.length; i++){
            if (dssach[i] != null && dssach[i].getMaSach().equals(ID)){
                dssach[i].output();
                System.out.println("--------------------");
            }
        }
        break;
    }
    else{
        System.out.println("Ma Sach không tồn tại.");
        break;}
}


                    break;
                    
                case 2:{
                   while (true) {
					System.out.println("Nhap ten Sach: ");
					String tenSach = sc.nextLine().toUpperCase();
					boolean check = false;
					for (int i = 0; i < dssach.length;i++) {
						if (dssach[i].getTenSP().toUpperCase().compareTo(tenSach) == 0) {
							check = true;
							break;
						}
					}
					if (check == true) {
						for (int i = 0; i < dssach.length; i++) {
							if (dssach[i].getTenSP().toUpperCase().equals(tenSach) == true) {
                                                                                                                                                        dssach[i].output();
                                                                                                                                                         System.out.println("--------------------");
								
						}
						break;
					}} else {
						System.out.println("Ten Sach khong ton tai!! Vui long nhap lai");
					}
				break;}
                }
                                
                case 3:{
                                      while (true) {
					System.out.println("Nhap gia tien thap nhat: ");
					double giaThapNhat=sc.nextLong();
                                                                                                        System.out.println("Nhap gia tien cao nhat: ");
					double giaCaoNhat=sc.nextLong();
					boolean check = false;
					for (int i = 0; i <dssach.length;i++) {
						if (dssach[i].getGiaBan()>giaThapNhat && dssach[i].getGiaBan()<giaCaoNhat ){
							check = true;
							break;
						}
					}
					if (check == true) {
						for (int i = 0; i < dssach.length; i++) {
							if (dssach[i].getGiaBan()>giaThapNhat &&dssach[i].getGiaBan()<giaCaoNhat) {
                                                                                                                                                            dssach[i].output();
                                                                                                                                                             System.out.println("--------------------");
								
						}
						break;
					}
                                                                                                                            } else {
						System.out.println("Ten Sach khong ton tai!! Vui long nhap lai");
                                                                                                                            }
				break;
                }
                                      break;
                }
                case 4:{
        System.out.println("Chon loai sach can tim:");
        System.out.println("1. Truyen");
        System.out.println("2. Giao khoa");
        int choice = sc.nextInt();

        
        switch (choice) {
            case 1:{
                    boolean check = false;
                      for (int i=0; i< dssach.length; i++){
                            if (dssach[i].getLoaiSach().equals("Truyen")){
                                check=true;
                                break;
                            }
                        }
                if (check == true) {
                     for (int i=0; i< DS_SACH.getSoLuong(); i++){
                                if (dssach[i].getLoaiSach().equals("Truyen")){
                                          dssach[i].output();
                                          System.out.println("--------------------");     
                                  }
                     }
                }
                     break;
            }
            case 2:{
                      boolean check = false;
                      for (int i=0; i<dssach.length; i++){
                            if (dssach[i].getLoaiSach().equals("Giao Khoa")){
                                check=true;
                                break;
                            }
                        }
                   if (check == true) {
                     for (int i=0; i< dssach.length; i++){
                                if (dssach[i].getLoaiSach().equals("Giao Khoa")){
                                          dssach[i].output();
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
            break;
        }
        
    }
        @Override
        
         public void hienThiDanhSach() {
    for (int i = 0; i < soLuong; i++) {
        SACH sach = dssach[i];
        System.out.println("Sách thứ " + (i+1) + ":");
        System.out.println("Tên SP: " + sach.getTenSP());
        System.out.println("NSX: " + sach.getNSX());
        System.out.println("Giá Bán: " + sach.getGiaBan());
        System.out.println("Giá Nhập: " + sach.getGiaNhap());
        System.out.println("Mã Sách: " + sach.getMaSach());
        System.out.println("Tên TG: " + sach.getTacGia().getTenTG());
        System.out.println("Quê Quán: " + sach.getTacGia().getQueQuan());
        System.out.println("Năm XB: " + sach.getNamXB());
        System.out.println("Loại Sách: " + sach.getLoaiSach());
        if (sach instanceof TRUYEN) {
            TRUYEN truyen = (TRUYEN) sach;
            System.out.println("Ngôn Ngữ: " + truyen.getNgonNgu());
            System.out.println("Loại Truyện: " + truyen.getLoaiTruyen());
        } else if (sach instanceof GIAOKHOA) {
            GIAOKHOA giaoKhoa = (GIAOKHOA) sach;
            System.out.println("Lớp: " + giaoKhoa.getLop());
            System.out.println("Môn: " + giaoKhoa.getMon());
        }
        System.out.println();
    }



    }
//    @Override
//         public void printFileContents(String filename) {
//    try {
//        FileReader fr = new FileReader("SanPham.txt");
//        BufferedReader br = new BufferedReader(fr);
//
//        String line = "";
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        br.close();
//        fr.close();
//
//    } catch (IOException e) {
//        System.out.println("Không thể đọc file!");
//        e.printStackTrace();
//    }
//}


 }

    


