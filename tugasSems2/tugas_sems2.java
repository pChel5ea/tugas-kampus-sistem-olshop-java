import java.io.*;
import java.util.*;
public class tugas_sems2{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------------------------");
        System.out.println("<<<++[ SELAMAT DATANG DI K2Shop ]++>>>");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println();
        //login
        while(true){
            System.out.print("ingin masuk atau buat akun? " );
            String login = scanner.nextLine();
            if (login.equals("buat akun")){
                System.out.print("create new username: ");
                String newUsername = scanner.nextLine();
                System.out.print("create new password: ");
                String newPassword = scanner.nextLine();
                
                // Write the user data to a text file
                try {
                    FileWriter writer = new FileWriter("user_data.txt", true); //kalo file blm ada, auto create
                    writer.write(newUsername + "," + newPassword + "\n");
                    writer.close();
                    System.out.println("User data saved successfully.");
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cls();
                } catch (IOException e) {
                    System.out.println("An error occurred while saving the user data.");
                    e.printStackTrace();
                }
            }
            
            System.out.println();
            System.out.println();
            
                // Get user input for username and password
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
    
            // Verify login by reading user data from the text file
            boolean loginSuccessful = verifyLogin(username, password);
    
            if (loginSuccessful) {
                System.out.println("Login successful!");
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cls();
                break;
            } else {
                System.out.println("Invalid username or password. Login failed.");
                System.out.println();
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cls();
            }
        }
        
        
        //list harga barang dan array untuk keranjang harga barang
        String[] barang = {"tas","sepatu","baju","buku","topi"};
        int[] harga = {150000,400000,60000,79000,40000};
        
        String[] keranjbar = new String[20];
        int[] keranjhar = new int[20];
        
        int totalharga = 0;
        String COkeranjbar = "";
        String COlistkeranjbar = "";
        
        int keranj = 0;
        int hargabar = 0;
        
        while(true){
            System.out.println("mau beli barang?\njual barang?\nlihat isi keranjang?\natau keluar? ");
            System.out.println();
            String BS = scanner.nextLine();

            if (BS.equals("beli")){
                System.out.println();
                int a = 0;
                for (String i: barang){
                    System.out.println(i+"\n harga: "+harga[a]) ;
                    a += 1;
                }
                
                System.out.println();
                boolean harbar = false;
                
                while (true){
                    System.out.print("mau beli apa? ");
                    String pilbar = scanner.nextLine();
                    for (String namabar : barang){
                        if (pilbar.equals(namabar)){
                            harbar = true;
                        }
                    }
                    if(harbar){
                        //mencocokan daftar barang dengan daftar harga
                        int letakharga = Arrays.asList(barang).indexOf(pilbar);
                        if (letakharga >= 0) {
                            hargabar = harga[letakharga];}
                        System.out.print("beli sekarang atau masukan ke keranjang? ");
                        String beli = scanner.nextLine();
                        if (beli.equals("beli sekarang")){
                            buy(pilbar,hargabar);
                            try{
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            cls();
                            break;
                        }
                        else{
                            //memasukan barang ke keranjang 
                            keranjbar[keranj] = pilbar;
                            keranjhar[keranj] = hargabar;
                            keranj++;
                            cls();
                            break;
                        }
                        
                    }
                    else{
                        System.out.println("===< MAAF, BARANG YANG KAMU MAU TIDAK ADA  >===");
                         try{
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            
            else if(BS.equals("keluar")){
                System.out.println();
                System.out.println("-----------------------------" );
                System.out.println("<< KAMU AKAN SEGERA KELUAR  >>");
                System.out.println("-----------------------------");
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            
            else if(BS.equals("jual")){
                System.out.println();
                System.out.print("masukan nama barang yang mau dijual: ");
                String barjual = scanner.nextLine();
                System.out.print("masukan harga dari barang yang mau dijual: ");
                int harjual = scanner.nextInt();scanner.nextLine();
                
                //memasukan barang yang mau dijual ke array barang
                String[] barangbaru = new String[barang.length+1];
                for (int i = 0; i < barang.length; i++) {
                    barangbaru[i] = barang[i];}
                barangbaru[barang.length] = barjual;
                barang = barangbaru;
                
                //memasukan harga barang yang mau dijual ke array harga
                int[] hargabaru = new int[harga.length+1];
                for (int i = 0; i < harga.length; i++) {
                    hargabaru[i] = harga[i];}
                hargabaru[harga.length] = harjual;
                harga = hargabaru;
                cls();
            }
            else if(BS.equals("keranjang")){
                System.out.println();
                System.out.println("===< ISI KERANJANG KAMU ADALAH : >==");
                //String binlistkeranjbar = "";
                for (String listkeranjbar : keranjbar) {                    
                    if (listkeranjbar == null) {
                        continue;
                    }
                    COlistkeranjbar += listkeranjbar + "\n";
                }
                //menampilkan output jika var kosong
                if (COlistkeranjbar.equals("")){
                    System.out.println("===< KERANJANG KAMU MASIH KOSONG  >===");
                    try{
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    cls();
                }
                //ngasih barang apa aja yang ada di keranjang
                else{ 
                    for (String listbarang : keranjbar) {
                        if(listbarang == null){continue;}
                        System.out.println(listbarang);
                    }
                }
                
                for (String listbarangCO : keranjbar) {
                    if (listbarangCO == null) {
                        continue;
                    }
                    COkeranjbar += listbarangCO;
                }
                
                if (!COkeranjbar.equals("")){
                    totalharga= 0;
                    
                    //mentotal harga barang yang ada dikeranjang ke var total harga
                    for (int listharga : keranjhar) {
                        if(listharga == 0){continue;}
                        totalharga += listharga;
                    }

                    System.out.println();
                    System.out.println("<< TOTAL HARGA BELANJA KAMU ADALAH  "+totalharga+" >>");
                    
                    System.out.print("mau bayar barang di keranjang? ");
                    String yesno = scanner.nextLine();
                    
                    if(yesno.equals("iya")|| yesno.equals("ya")){    
                        System.out.println();
                        System.out.print("masukan alamat pengiriman: ");
                        String alamat = scanner.nextLine();
                        System.out.print("pilih cara pembayaran(bank/COD/alfamart/indomaret): ");
                        String carabayar = scanner.nextLine();
                        
                        System.out.println();
                        System.out.println();
                        
                        System.out.println("barang akan dikirim ke alamat "+alamat+" dan harus dibayar melalui "+carabayar );
                        System.out.println("---==<## PESANAN DITERIMA DAN AKAN SEGERA DIPROSES  ##>==---");
                        try{
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //ngosongin keranjang kalo dah dibayar
                        String[] keranjbar_kosong = new String[20];
                        int[] keranjhar_kosong = new int[20];
                        keranjbar = keranjbar_kosong;
                        keranjhar = keranjhar_kosong;
                        COlistkeranjbar = "";
                        COkeranjbar = "";
                        totalharga = 0; //mengembalikan/mengubah nilai var ke 0 agar harga tidak menyampur dan menumpuk
                    }
                    cls();
                }
            }
        }
    }
        
    private static boolean verifyLogin(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String storedUsername = userData[0];
                String storedPassword = userData[1];

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    reader.close();
                    return true; // Login successful
                }
            }
            reader.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred while verifying the login.");
            //e.printStackTrace();
        }
        return false; // Login failed
    }
    
    private static void buy(String barang, int harga){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("masukan alamat pengiriman: ");
        String alamat = scanner.nextLine();
        System.out.print("pilih cara pembayaran(bank/COD/alfamart/indomaret): ");
        String carabayar = scanner.nextLine();
        
        System.out.println();
        System.out.println();
        
        System.out.println("anda memesan "+barang+" dengan harga "+harga+".");
        System.out.println("barang akan dikirim ke alamat "+alamat+" dan harus dibayar melalui "+carabayar );
        System.out.println("---==<## PESANAN DITERIMA DAN AKAN SEGERA DIPROSES  ##>==---");
    }

    private static void cls(){
        Scanner scanner = new Scanner(System.in);
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}