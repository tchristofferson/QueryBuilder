import com.tchristofferson.querybuilder.DeleteQueryBuilder;
import com.tchristofferson.querybuilder.InsertQueryBuilder;
import com.tchristofferson.querybuilder.SelectQueryBuilder;
import com.tchristofferson.querybuilder.UpdateQueryBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void testSelect() {
        String noDistinctAll = new SelectQueryBuilder(false).toString();
        assertEquals("SELECT *;", noDistinctAll);

        String distinctAll = new SelectQueryBuilder(true).toString();
        assertEquals("SELECT DISTINCT *;", distinctAll);

        String selectWithSpecifiedColumns = new SelectQueryBuilder(false, "uuid", "name").toString();
        assertEquals("SELECT uuid, name;", selectWithSpecifiedColumns);
    }

    @Test
    public void testSelectFrom() {
        String selectAllFromTable = new SelectQueryBuilder(false).from("names").toString();
        assertEquals("SELECT * FROM names;", selectAllFromTable);

        String selectAllFromMultipleTables = new SelectQueryBuilder(false).from("table1", "table2").toString();
        assertEquals("SELECT * FROM table1, table2;", selectAllFromMultipleTables);
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

        assertEquals("SELECT * FROM players WHERE uuid = 'uuid' AND players.uuid = 'uuid';", sql);

        sql = new SelectQueryBuilder(false)
                .from("players", "money")
                .where("players", "uuid")
                .is("money", "uuid")
                .toString();

        assertEquals("SELECT * FROM players, money WHERE players.uuid = money.uuid;", sql);
    }

    @Test
    public void testDelete() {
        String sql = new DeleteQueryBuilder()
                .from("players")
                .where("uuid")
                .is("uuid-goes-here")
                .toString();

        assertEquals("DELETE FROM players WHERE uuid = 'uuid-goes-here';", sql);
    }

    @Test
    public void testUpdate() {
        String sql = new UpdateQueryBuilder("players")
                .set("name", "tchristofferson")
                .set("uuid", "uuid")
                .where("uuid")
                    .is(123)
                .toString();

        assertEquals("UPDATE players SET name = 'tchristofferson', uuid = 'uuid' WHERE uuid = 123;", sql);
    }

    @Test
    public void testInsert() {
        String sql = new InsertQueryBuilder("players")
                .withColumns("uuid", "name")
                .withValues("uuid", "tchristofferson")
                .withValues("uuid2", "mchristofferson")
                .toString();

        assertEquals("INSERT INTO players (uuid, name) VALUES ('uuid', 'tchristofferson'), ('uuid2', 'mchristofferson');", sql);
    }
}
