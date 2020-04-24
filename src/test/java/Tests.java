import com.tchristofferson.DeleteQueryBuilder;
import com.tchristofferson.SelectQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.Preconditions;

public class Tests {

    @Test
    public void testSelect() {
        String noDistinctAll = new SelectQueryBuilder(false).toString();
        Preconditions.condition(noDistinctAll.equals("SELECT *;"), "select (no distinct all) fails");

        String distinctAll = new SelectQueryBuilder(true).toString();
        Preconditions.condition(distinctAll.equals("SELECT DISTINCT *;"), "select (distinct all) fails");

        String selectWithSpecifiedColumns = new SelectQueryBuilder(false, "uuid", "name").toString();
        Preconditions.condition(selectWithSpecifiedColumns.equals("SELECT uuid, name;"), "select (specified columns) fails");
    }

    @Test
    public void testSelectFrom() {
        String selectAllFromTable = new SelectQueryBuilder(false).from("names").toString();
        Preconditions.condition(selectAllFromTable.equals("SELECT * FROM names;"), "select all from table fails");

        String selectAllFromMultipleTables = new SelectQueryBuilder(false).from("table1", "table2").toString();
        Preconditions.condition(selectAllFromMultipleTables.equals("SELECT * FROM table1, table2;"), "select all from multiple tables fails:");
    }

    @Test
    public void testEqual() {
        String sql = new SelectQueryBuilder(false)
                .from("players")
                .where("uuid")
                .is("uuid")
                .and("players", "uuid")
                .is("uuid")
                .toString();

        Preconditions.condition(sql.equals("SELECT * FROM players WHERE uuid = 'uuid' AND players.uuid = 'uuid';"), "equal test fails");

        sql = new SelectQueryBuilder(false)
                .from("players", "money")
                .where("players", "uuid")
                .is("money", "uuid")
                .toString();

        Preconditions.condition(sql.equals("SELECT * FROM players, money WHERE players.uuid = money.uuid;"), "equal test using columns fails");
    }

    @Test
    public void testDelete() {
        String sql = new DeleteQueryBuilder()
                .from("players")
                .where("uuid")
                .is("uuid-goes-here")
                .toString();

        Preconditions.condition(sql.equals("DELETE FROM players WHERE uuid = 'uuid-goes-here';"), "Delete fails");
    }

}
