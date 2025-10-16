import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class writeST_Calendare_SQL {
}

class CreateAndFillDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:D:/MyFiles/Trainings/For_Chorus_Kotlin/person.db";
        Connection conn = null;
        Statement stmt = null;
        // SQL для создания таблицы
        String createTableSQL = "CREATE TABLE IF NOT EXISTS st_calendar ("
                + "\"id\" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "\"date\" TEXT,"
                + "\"id_st\" INTEGER,"
                + "\"data\" TEXT"
                + ");";

        // Подключение к базе данных и создание таблицы
        try  {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            // Создание таблицы
            stmt.execute(createTableSQL);
            System.out.println("Таблица создана.");

            // Заполнение таблицы
            fillTable(stmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            // Закрытие ресурсов
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
                System.out.println("file close");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void fillTable(Statement stmt) throws SQLException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1); // Начало с 2025.01.01

        // Устанавливаем количество дней до 50000
        for (int id = 1; id <= 50000; id++) {
            String date = dateFormat.format(calendar.getTime());
            String insertSQL = String.format("INSERT INTO st_calendar (id, date) VALUES (%d, '%s');", id, date);
            // Выполняем вставку
            stmt.executeUpdate(insertSQL);
            System.out.println(""+insertSQL);
            // Увеличиваем дату на один день
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Выводим последнюю дату
        String lastDate = dateFormat.format(calendar.getTime());
        System.out.println("Последняя дата: " + lastDate);
        System.out.println("Таблица заполнена.");}catch (SQLException e) {
            System.out.println("_"+e+"_  _"+e.getMessage()+"_  _"+e.toString());
        }
        catch (Exception e) {
            System.out.println("_"+e+"_  _"+e.getMessage()+"_  _"+e.toString());
        }
    }
}