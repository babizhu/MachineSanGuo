package experiment.DB;

import util.db.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-2
 * Time: 上午10:11
 * To change this template use File | Settings | File Templates.
 */
public class BatchPs{

    public static void main(String[] args){
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;


        String sql1 = "INSERT INTO test1 VALUES (?,?)";
        String sql2 = "INSERT INTO test2 VALUES (?,?)";
        Connection con = DatabaseUtil.INSTANCE.getConnection();

        try {
            con.setAutoCommit(false);

            pst = con.prepareStatement(sql1);
            pst.setString(1, "lk");
            pst.setInt(2, 30);
            pst.executeUpdate();
            pst.addBatch();
//            pst.a
            con.commit();
            pst1 = con.prepareStatement(sql1);
            pst1.setString(1, "lklk");
            pst1.setInt(2, 3030);
//            pst.executeUpdate();
//            pst1.addBatch();

//            pst1.executeBatch();
            con.commit();
//            pst.com
        } catch( SQLException e ) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.INSTANCE.close(null, pst, con);
        }

    }
}
