package hzproj;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
public class oracleparam {
    public static void main(String[] args){
        String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
        String username = "system";
        String password = "Dumb_099";

        int[] counts = {20000,100000};

        try(Connection conn = DriverManager.getConnection(url, username, password)){
            for(int count : counts){
                long startTime = System.currentTimeMillis();
                insertNumber(conn,count);
                long endTime = System.currentTimeMillis();
                System.out.println(count + " satır ekleme süresi: " + (endTime - startTime) + " ms");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void insertNumber(Connection conn, int count)throws SQLException{
        String sql="INSERT INTO random_numbers (number_column) VALUES (?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            Random rand = new Random();
            for (int i = 0; i < count; i++) {
                pstmt.setInt(1, rand.nextInt(1000000)); // 0-999999 arası rastgele sayı ekliyoruz
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }
}
