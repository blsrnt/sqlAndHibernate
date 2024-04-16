import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pas = "6488274Ss";
        try {
            Connection connection = DriverManager.getConnection(url, user, pas);
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT pl.course_name, count(pl.subscription_date)/(max(month(pl.subscription_date)) - " +
                    "min(month(pl.subscription_date)) + 1) `avg`" +
                    "FROM PurchaseList pl " +
                    "group by pl.course_name;");
            while (set.next()){
                String avg = String.format("%.2f", Double.valueOf(set.getString("avg")));
                System.out.println(set.getString("course_name") + " - " + avg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
